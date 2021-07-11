package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.dialouge.DialougeComponent;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.WorldSystem;

public class NPC {
    public static Entity create(Totem game, Vector3 pos, String texture, AIComponent.AIProvider ai_provider, String dialouge_part){
        Entity e = WorldSystem.entity(game);

        e.add(new MovementComponent());

        TransformComponent t = new TransformComponent();
        t.position.set(pos);
        e.add(t);


        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(texture));
        e.add(r);

        ColliderComponent c = new ColliderComponent();
        c.m_solid = true;
        c.m_dynamic = true;
        c.m_bounds = new Vector2(.5f, .5f);
        e.add(c);


        AIComponent ai = new AIComponent(ai_provider);
        e.add(ai);

        DialougeComponent dialouge = new DialougeComponent();
        e.add(dialouge);



        InteractionComponent i = new InteractionComponent("press e to talk", new InteractionComponent.InteractionAction() {
            @Override
            public void trigger(Entity e) {
                dialouge.start_dialouge(dialouge_part);
            }
        });
        e.add(i);

        return e;
    }
}
