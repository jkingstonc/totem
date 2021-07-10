package com.kingstonops.totem.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;


public class Pet {

    public static float PET_FOLLOW_DIST = 1f;

    public static class PetAIProvider extends AIComponent.AIProvider{

        @Override
        public void process(Totem game, Entity e) {

            // look for hay
            // find the nearest hay block by checking IDComponents

            Entity player = IDComponent.find_nearest(game, "player", e.getComponent(TransformComponent.class).position);


            if(player!=null) {
                ColliderComponent dog_collider = e.getComponent(ColliderComponent.class);
                if(!dog_collider.m_colliding_with.contains(player)){

                    // go to the player

                    MovementComponent dog_m = e.getComponent(MovementComponent.class);
                    TransformComponent dog_t = e.getComponent(TransformComponent.class);
                    TransformComponent player_t = player.getComponent(TransformComponent.class);

                    Vector3 v = new Vector3();
                    v.x = player_t.position.x - dog_t.position.x;
                    v.y = player_t.position.y - dog_t.position.y;

                    double dist = Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y, 2));
                    if(dist > PET_FOLLOW_DIST){

                        v = v.nor();

                        dog_m.acceleration.x = dog_m.m_speed * v.x;
                        dog_m.acceleration.y = dog_m.m_speed * v.y;
                    }
                }

            }
        }
    }
}
