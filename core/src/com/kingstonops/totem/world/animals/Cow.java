package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.world.guys.AIComponent;

public class Cow extends Animal{
    public Cow() {
        super("cow", "cow.png");
    }

    public static class CowAIProvider extends AIComponent.AIProvider{

        boolean m_hungry = false;
        @Override
        public void process(Entity e) {
            if(m_hungry){
                // look for hay
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
