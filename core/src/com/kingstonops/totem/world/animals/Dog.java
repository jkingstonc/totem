package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;

import java.util.ArrayList;

public class Dog extends Animal{



    public Dog() {
        super("dog", "dog.png");
    }

    public static class DogAIProvider extends AIComponent.AIProvider{

        @Override
        public void process(Totem game, Entity e) {

            // look for hay
            // find the nearest hay block by checking IDComponents



            Entity player = IDComponent.find_nearest(game, "player", e.getComponent(TransformComponent.class).position);


            if(player!=null) {
                ColliderComponent dog_collider = e.getComponent(ColliderComponent.class);
                if(!dog_collider.m_colliding_with.contains(player)){

                    // go to the player

                    MovementComponent cow_m = e.getComponent(MovementComponent.class);
                    TransformComponent cow_t = e.getComponent(TransformComponent.class);
                    TransformComponent hay_t = player.getComponent(TransformComponent.class);

                    Vector3 v = new Vector3();
                    v.x = hay_t.position.x - cow_t.position.x;
                    v.y = hay_t.position.y - cow_t.position.y;
                    v = v.nor();


                    cow_m.acceleration.x = cow_m.m_speed * v.x;
                    cow_m.acceleration.y = cow_m.m_speed * v.y;
                }

            }
        }
    }

    @Override
    public Entity spawn(Totem game) {
        Entity e = super.spawn(game);
        e.getComponent(AIComponent.class).m_ai_provier = new DogAIProvider();
        e.getComponent(TransformComponent.class).scale.set(new Vector3(.5f,.5f,1));
        e.getComponent(ColliderComponent.class).m_bounds.set(new Vector2(.1f,.1f));
        return e;
    }
}
