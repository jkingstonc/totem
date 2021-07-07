package com.kingstonops.totem.physics;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;

/*
* Hand made collision system. Note this is not perfect, here are some improvements
*
* todo currently assumes that both the bounding boxes are the same size
* todo uses current position to check for collision, rather than where it will be in the future
*  (to fix this use + t.velocity)
*
* */
public class ColliderSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_transform_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<ColliderComponent> m_collider_mapper = ComponentMapper.getFor(ColliderComponent.class);


    public ColliderSystem(){
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, ColliderComponent.class).get());
    }

    @Override
    public void update(float dt){

        for(int i = 0;i<m_entities.size();i++) {
            Entity e = m_entities.get(i);
            ColliderComponent c = m_collider_mapper.get(e);
            c.m_colliding_with.clear();
        }

        for(int i = 0;i<m_entities.size();i++){
            Entity e1 = m_entities.get(i);
            TransformComponent t1 = m_transform_mapper.get(e1);
            ColliderComponent c1 = m_collider_mapper.get(e1);

            MovementComponent m1 = e1.getComponent(MovementComponent.class);



            // if we are moving then check our collision with the other entities
            if(m1!=null && c1.m_dynamic){
                Vector3 pos1 = new Vector3(t1.position.x + m1.velocity.x, t1.position.y+m1.velocity.y,0);
                for(int j = 0;j<m_entities.size();j++){
                    Entity e2 = m_entities.get(j);
                    TransformComponent t2 = m_transform_mapper.get(e2);
                    ColliderComponent c2 = m_collider_mapper.get(e2);

                    Vector3 pos2 = new Vector3(t2.position);

                    if(e1!=e2){

                        // i think we should deal with velocity here instead
                        Vector2 overlap = new Vector2(
                                (pos2.x - pos1.x),
                                (pos2.y - pos1.y)
                        );

                        if(
                                (pos1.x - c1.m_bounds.x)<(t2.position.x + c2.m_bounds.x)
                                        &&(pos1.x + c1.m_bounds.x)>(pos2.x - c2.m_bounds.x)
                                        &&(pos1.y - c1.m_bounds.y)<(pos2.y + c2.m_bounds.y)
                                        &&(pos1.y + c1.m_bounds.y)>(pos2.y - c2.m_bounds.y)
                        ){
                            if(!c1.m_colliding_with.contains(e2)) c1.m_colliding_with.add(e2);
                            if(!c2.m_colliding_with.contains(e1)) c2.m_colliding_with.add(e1);

                            c1.m_colliding_with.add(e2);
                            c2.m_colliding_with.add(e1);

                            if(c1.m_solid&&c2.m_solid && c1.m_dynamic){


                                // resolve whichever overlap is smaller
                                // todo this doesn't account for bounding box sizes
                                if(Math.abs(overlap.x) > Math.abs(overlap.y)){
                                    // resolve x
                                    // e2 is left of e1 so we need to move e2 down
                                    if(overlap.x<0){
                                        float diff = (pos2.x + c2.m_bounds.x) - (pos1.x - c1.m_bounds.x);
                                        t1.position.x += diff;
                                        //m1.acceleration.x = 0;
                                    }
                                    // e2 is right of e1
                                    else if(overlap.x>=0){
                                        float diff = (pos1.x + c1.m_bounds.x) - (pos2.x - c2.m_bounds.x);
                                        t1.position.x -= diff;
                                        //m1.acceleration.x = 0;
                                    }
                                }else {
                                    // resole y
                                    // e2 is below e1 so we need to move e2 down
                                    if(overlap.y<0){
                                        float diff = (pos2.y + c2.m_bounds.y) - (pos1.y - c1.m_bounds.y);
                                        t1.position.y += diff;
                                        //m1.acceleration.y = 0;
                                    }
                                    // e2 is above e1
                                    else if(overlap.y>=0){
                                        float diff = (pos1.y + c1.m_bounds.y) - (pos2.y - c2.m_bounds.y);
                                        t1.position.y -= diff;
                                        //m1.acceleration.y = 0;
                                    }
                                }



                            }
                        }

                    }
                }
            }

            /*for(int j = 0;j<m_entities.size();j++){
                Entity e2 = m_entities.get(j);
                TransformComponent t2 = m_transform_mapper.get(e2);
                ColliderComponent c2 = m_collider_mapper.get(e2);
                if(e1!=e2){

                    // i think we should deal with velocity here instead
                    Vector2 overlap = new Vector2(
                            (t2.position.x - t1.position.x),
                            (t2.position.y - t1.position.y)
                    );

                    if(
                    (t1.position.x - c1.m_bounds.x)<(t2.position.x + c2.m_bounds.x)
                    &&(t1.position.x + c1.m_bounds.x)>(t2.position.x - c2.m_bounds.x)
                    &&(t1.position.y - c1.m_bounds.y)<(t2.position.y + c2.m_bounds.y)
                    &&(t1.position.y + c1.m_bounds.y)>(t2.position.y - c2.m_bounds.y)
                    ){
                        if(!c1.m_colliding_with.contains(e2)) c1.m_colliding_with.add(e2);
                        if(!c2.m_colliding_with.contains(e1)) c2.m_colliding_with.add(e1);
                        if(c1.m_solid&&c2.m_solid && c2.m_dynamic){

                            // resolve whichever overlap is smaller
                            // todo this doesn't account for bounding box sizes
                            if(Math.abs(overlap.x) > Math.abs(overlap.y)){
                                // resolve x
                                // e2 is left of e1 so we need to move e2 down
                                if(overlap.x<0){
                                    float diff = (t2.position.x + c2.m_bounds.x) - (t1.position.x - c1.m_bounds.x);
                                    t2.position.x -= diff;
                                }
                                // e2 is right of e1
                                else if(overlap.x>=0){
                                    float diff = (t1.position.x + c1.m_bounds.x) - (t2.position.x - c2.m_bounds.x);
                                    t2.position.x += diff;
                                }
                            }else {
                                // resole y
                                // e2 is below e1 so we need to move e2 down
                                if(overlap.y<0){
                                    float diff = (t2.position.y + c2.m_bounds.y) - (t1.position.y - c1.m_bounds.y);
                                    t2.position.y -= diff;
                                }
                                // e2 is above e1
                                else if(overlap.y>=0){
                                    float diff = (t1.position.y + c1.m_bounds.y) - (t2.position.y - c2.m_bounds.y);
                                    t2.position.y += diff;
                                }
                            }



                        }
                    }

                }
            }*/
        }
    }
}
