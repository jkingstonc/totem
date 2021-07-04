package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.guys.AIComponent;

public class Chicken extends Animal{
    public Chicken() {
        super("chicken", "chicken.png");
    }

    public static class ChickenAIProvider extends AIComponent.AIProvider.BasicWanderingAIProvider{

        boolean m_hungry = false;
        @Override
        public void process(Entity e) {
            if(m_hungry){
                // look for hay
                // find the nearest hay block


            }else{
                // just wander
                super.process(e);
            }
        }
    }

    @Override
    public Entity spawn(Totem game, Vector3 pos) {
        Entity e = super.spawn(game, pos);
        e.getComponent(AIComponent.class).m_ai_provier = new ChickenAIProvider();
        TransformComponent t = e.getComponent(TransformComponent.class);
        t.scale.x = RenderSystem.unit_to_pixel(.5f);
        t.scale.y = RenderSystem.unit_to_pixel(.5f);
        return e;
    }
}