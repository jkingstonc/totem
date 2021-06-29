package com.kingstonops.totem.physics;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {

    public static float ACCEL_DAMPER = 0.75f;

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
            v.velocity.set(0,0,0);
            // add acceleration
            v.velocity.x+=v.acceleration.x;
            v.velocity.y+=v.acceleration.y;
            //v.velocity.add(v.acceleration.mulAdd(new Vector3(1, 1, 1), dt));

            // add to position
            //p.x+=v.v_x;
            // p.y+=v.v_y;
            dt = 0.01f;
            p.position.x += (v.velocity.x*dt);
            p.position.y += (v.velocity.y*dt);



            // slow down acceleration
            float THRESH = 0.00001f;
            if(v.acceleration.x>THRESH || v.acceleration.x<-THRESH)
                v.acceleration.x*=(ACCEL_DAMPER);
            else
                v.acceleration.x=0;
            if(v.acceleration.y>THRESH || v.acceleration.y<-THRESH)
                v.acceleration.y*=(ACCEL_DAMPER);
            else
                v.acceleration.y=0;



        }
    }
}
