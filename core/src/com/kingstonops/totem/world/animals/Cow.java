package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;

import java.util.ArrayList;

public class Cow extends Animal{
    public Cow() {
        super("cow", "cow.png");
    }

    public static class CowAIProvider extends AIComponent.AIProvider.BasicWanderingAIProvider{

        boolean m_hungry = true;
        @Override
        public void process(Totem game, Entity e) {
            if(m_hungry){
                // look for hay
                // find the nearest hay block by checking IDComponents


                Entity nearest_hay = IDComponent.find_nearest(game, "hay", e.getComponent(TransformComponent.class).position);


                if(nearest_hay!=null) {
                    // now go to the nearest hay

                    MovementComponent cow_m = e.getComponent(MovementComponent.class);
                    TransformComponent cow_t = e.getComponent(TransformComponent.class);
                    TransformComponent hay_t = nearest_hay.getComponent(TransformComponent.class);

                    Vector3 v = new Vector3();
                    v.x = hay_t.position.x - cow_t.position.x;
                    v.y = hay_t.position.y - cow_t.position.y;
                    v = v.nor();


                    cow_m.acceleration.x = cow_m.m_speed * v.x;
                    cow_m.acceleration.y = cow_m.m_speed * v.y;

                }

            }else{
                // just wander
                super.process(game, e);
            }
        }
    }

    @Override
    public Entity spawn(Totem game, Vector3 pos) {
        Entity e = super.spawn(game, pos);
        e.getComponent(AIComponent.class).m_ai_provier = new CowAIProvider();
        return e;
    }
}
