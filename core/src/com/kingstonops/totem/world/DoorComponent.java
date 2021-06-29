package com.kingstonops.totem.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class DoorComponent implements Component{
    public static Entity create(Engine engine, Vector3 pos){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position = new Vector3(
                RenderSystem.unit_to_pixel(pos.x),
                RenderSystem.unit_to_pixel(pos.y),
                pos.z);
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture("door.jpg"));
        e.add(r);

        ColliderComponent c = new ColliderComponent();
        c.m_dynamic = false;
        c.m_solid = false;
        c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));

        DoorComponent d = new DoorComponent();
        e.add(d);

        e.add(c);
        return e;
    }
}