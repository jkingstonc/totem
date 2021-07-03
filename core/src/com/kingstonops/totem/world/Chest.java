package com.kingstonops.totem.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.items.InventoryComponent;
import com.kingstonops.totem.items.Item;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.guys.InteractionComponent;

public class Chest {
    public static Entity create(Engine engine, Vector3 pos, String texture){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position.set(pos);
        p.scale.set(new Vector3(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f), 1));
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(texture));
        e.add(r);

        InteractionComponent i = new InteractionComponent("press e to loot", new InteractionComponent.InteractionAction() {
            @Override
            public void trigger(Entity e) {

                InventoryComponent i = e.getComponent(InventoryComponent.class);
                assert i!=null;
                i.put(Item.registry.instantiate("rusty_gear"));
            }
        });
        e.add(i);
        ColliderComponent c = new ColliderComponent();
        c.m_dynamic = false;
        c.m_solid = true;
        c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));
        e.add(c);
        return e;
    }
}
