package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.Utils;

import java.util.ArrayList;

public class CameraSystem extends EntitySystem {


    private ImmutableArray<Entity> m_entities;
    private ArrayList<Entity> m_render_queue;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<CameraComponent> m_camera_mapper = ComponentMapper.getFor(CameraComponent.class);

    public CameraSystem(){
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, CameraComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            TransformComponent t = m_pos_mapper.get(e);
            CameraComponent c = m_camera_mapper.get(e);

            if(c.follow_target){

                final float LERP = .5f;
                float x = Utils.lerp(t.position.x, c.target.x, LERP);
                float y = Utils.lerp(t.position.y, c.target.y, LERP);

                t.position.x = x;
                t.position.y = y;

                float cam_w = Gdx.graphics.getWidth();
                float cam_h = Gdx.graphics.getHeight();
                c.cam.position.set(cam_w/2f + t.position.x, cam_h/2f + t.position.y, 0);

            }
        }
    }
}
