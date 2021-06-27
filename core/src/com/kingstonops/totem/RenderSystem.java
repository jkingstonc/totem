package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import javafx.geometry.Pos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class RenderSystem extends EntitySystem {

    private static class ZComparator implements Comparator<Entity>{
        private ComponentMapper<PositionComponent> m_pos_mapper;
        public ZComparator(){
            m_pos_mapper = ComponentMapper.getFor(PositionComponent.class);
        }
        @Override
        public int compare(Entity o1, Entity o2) {
            PositionComponent p1 = m_pos_mapper.get(o1);
            PositionComponent p2 = m_pos_mapper.get(o2);
            int result = 0;
            if(p1.z > p2.z)
                result = 1;
            else if(p1.z < p2.z)
                result = -1;
            return result;
        }
    }

    private ImmutableArray<Entity> m_entities;
    private ArrayList<Entity> m_render_queue;

    private ComponentMapper<PositionComponent> m_pos_mapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RenderComponent> m_render_mapper = ComponentMapper.getFor(RenderComponent.class);

    private Batch m_batch;

    public RenderSystem(){
        m_batch = new SpriteBatch();
        m_render_queue = new ArrayList<Entity>();
        System.out.println("creating RenderSystem");
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class).get());
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
        m_batch.begin();

        for(int i = 0;i<m_render_queue.size();i++){
            Entity e = m_render_queue.get(i);
            PositionComponent p = m_pos_mapper.get(e);
            RenderComponent r = m_render_mapper.get(e);

            m_batch.draw(r.texture, p.x, p.y);
        }

        m_batch.end();
        m_render_queue.clear();
    }
}
