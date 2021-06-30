package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class NPC {
    public static Entity create(Engine engine, String texture, AIComponent.AIProvider ai_provider, String dialouge_part){
        Entity e = engine.createEntity();


        e.add(new MovementComponent());

        TransformComponent t = new TransformComponent();
        t.position.z = 3;
        t.position.x = 0;
        t.position.y = 0;
        e.add(t);


        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture(texture));
        e.add(r);

        ColliderComponent c = new ColliderComponent();
        c.m_solid = true;
        c.m_dynamic = true;
        c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));
        e.add(c);


        AIComponent ai = new AIComponent(ai_provider);
        e.add(ai);

        DialougeComponent dialouge = new DialougeComponent();
        e.add(dialouge);



        InteractionComponent i = new InteractionComponent(new InteractionComponent.InteractionAction() {
            @Override
            public void trigger() {
                dialouge.start_dialouge(dialouge_part);
            }
        });
        e.add(i);

        engine.addEntity(e);
        return e;
    }
}
