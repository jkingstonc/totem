package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kingstonops.totem.TransformComponent;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderSystem extends EntitySystem {

    private static class ZComparator implements Comparator<Entity>{
        private ComponentMapper<TransformComponent> m_pos_mapper;
        public ZComparator(){
            m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
        }
        @Override
        public int compare(Entity o1, Entity o2) {
            TransformComponent p1 = m_pos_mapper.get(o1);
            TransformComponent p2 = m_pos_mapper.get(o2);
            int result = 0;
            if(p1.position.z > p2.position.z)
                result = 1;
            else if(p1.position.z < p2.position.z)
                result = -1;
            return result;
        }
    }

    private ImmutableArray<Entity> m_entities;
    private ArrayList<Entity> m_render_queue;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<RenderComponent> m_render_mapper = ComponentMapper.getFor(RenderComponent.class);

    private Batch m_batch;
    private OrthographicCamera m_camera;

    public static final float UNIT_SIZE = 100f; // pixels per unit
    public static final float PIXEL_TO_UNIT = 1/UNIT_SIZE;

    public static final float pix_to_unit(float pixels){
        return pixels * PIXEL_TO_UNIT;
    }
    public static final float unit_to_pixel(float unit){
        return unit/PIXEL_TO_UNIT;
    }

    public RenderSystem(){
        m_batch = new SpriteBatch();
        m_render_queue = new ArrayList<Entity>();
        float cam_w = Gdx.graphics.getWidth(); //*UNIT_SIZE
        float cam_h = Gdx.graphics.getHeight(); //*UNIT_SIZE
        m_camera = new OrthographicCamera(cam_w, cam_h);
        m_camera.position.set(cam_w/2f, cam_h/2f, 0);
        System.out.println("creating RenderSystem");
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, RenderComponent.class).get());
    }

    @Override
    public void update(float dt){

        ScreenUtils.clear(1, 0, 0, 1);

        for(int i = 0;i<m_entities.size();i++){
            System.out.println("entity!");
            Entity e = m_entities.get(i);
            m_render_queue.add(e);
        }


        m_render_queue.sort(new ZComparator());

        m_camera.update();
        m_batch.setProjectionMatrix(m_camera.combined);
        m_batch.enableBlending();
        m_batch.begin();


        for(int i = 0;i<m_render_queue.size();i++){
            Entity e = m_render_queue.get(i);
            TransformComponent t = m_pos_mapper.get(e);
            RenderComponent r = m_render_mapper.get(e);

            float w = r.texture.getRegionWidth();
            float h = r.texture.getRegionHeight();

            // todo this sets the w and h to always be a unit size
            w = UNIT_SIZE;
            h = UNIT_SIZE;


            float origin_x = Gdx.graphics.getWidth()/2;
            float origin_y = Gdx.graphics.getHeight()/2;

            //System.out.println("x = "+(t.x + origin_x)+",y = "+(t.y + origin_y));

            m_batch.draw(r.texture, t.position.x + origin_x, t.position.y + origin_y, w, h);
        }

        m_batch.end();
        m_render_queue.clear();
    }
}
