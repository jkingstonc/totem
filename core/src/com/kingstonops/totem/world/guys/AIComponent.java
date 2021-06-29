package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;

import java.util.Random;

public class AIComponent implements Component {
    public static abstract class AIProvider{
        public static class BasicStationaryAIProvider extends AIProvider{
            @Override
            public void process(Entity e){}
        }
        public static class BasicWanderingAIProvider extends AIProvider{

            public BasicWanderingAIProvider(){
                m_target_pos = new Vector3(RenderSystem.unit_to_pixel(5f),RenderSystem.unit_to_pixel(5f),0);
            }

            // the target position to walk to
            public Vector3 m_target_pos;

            @Override
            public void process(Entity e){
                Random r = new Random();

                if(r.nextInt(500)==1){
                    m_target_pos = new Vector3(RenderSystem.unit_to_pixel(10-r.nextInt(20)),RenderSystem.unit_to_pixel(10-r.nextInt(20)),0);
                }
                TransformComponent t = e.getComponent(TransformComponent.class);
                MovementComponent m = e.getComponent(MovementComponent.class);
                Vector3 diff = new Vector3(m_target_pos).sub(t.position).nor();
                m.acceleration.x = diff.x*100f;
                m.acceleration.y = diff.y*100f;
            }
        }

        public abstract void process(Entity e);

    }
    public AIComponent(){}
    public AIComponent(AIProvider ai_provider){
        m_ai_provier = ai_provider;
    }
    AIProvider m_ai_provier;

    public AIProvider provider(){
        return m_ai_provier;
    }
}
