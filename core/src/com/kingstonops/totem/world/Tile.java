package com.kingstonops.totem.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class Tile {
    public static Entity create(Engine engine, Vector3 pos, String texture){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position = new Vector3(
                RenderSystem.unit_to_pixel(pos.x),
                RenderSystem.unit_to_pixel(pos.y),
                pos.z);
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture(texture));
        e.add(r);
        return e;
    }
}
