package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> m_vel_mapper = ComponentMapper.getFor(MovementComponent.class);

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, MovementComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i = 0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            TransformComponent p = m_pos_mapper.get(e);
            MovementComponent v = m_vel_mapper.get(e);
            v.v_x=0;
            v.v_y=0;

            // add acceleration
            v.v_x+=v.a_x*dt;
            v.v_y+=v.a_y*dt;

            // add to velocity
            p.x+=v.v_x;
            p.y+=v.v_y;



            // slow down acceleration
            float THRESH = 0.00001f;
            float DAMPER = .95f;
            if(v.a_x>THRESH || v.a_x<-THRESH)
                v.a_x*=(DAMPER);
            else
                v.a_x=0;
            if(v.a_y>THRESH || v.a_y<-THRESH)
                v.a_y*=(DAMPER);
            else
                v.a_y=0;



        }
    }
}
