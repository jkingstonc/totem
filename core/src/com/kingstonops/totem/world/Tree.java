package com.kingstonops.totem.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class Tree {
    public static Entity create(Engine engine, Vector3 pos, String texture, boolean solid){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position.set(pos);
        e.add(p);
        p.scale.set(new Vector3(RenderSystem.unit_to_pixel(1), RenderSystem.unit_to_pixel(2), RenderSystem.unit_to_pixel(1)));
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(texture));
        e.add(r);

        if(solid){
            ColliderComponent c = new ColliderComponent();
            c.m_dynamic = false;
            c.m_solid = true;
            c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));
            e.add(c);
        }
        return e;
    }
}
