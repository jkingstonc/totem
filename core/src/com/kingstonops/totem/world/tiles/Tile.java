package com.kingstonops.totem.world.tiles;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Registry;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class Tile {


    /*
    * Tile.registry.register("grass", ()->new Tile("grass", "grass.png", false)))
    * */
    public static Registry<Tile> registry = new Registry<>();


    private String m_name;
    private String m_texture;
    private boolean m_solid = false;

    public Tile(){}
    public Tile(String name, String texture, boolean solid){
        m_name = name;
        m_texture = texture;
        m_solid = solid;
    }

    public static Entity create(Engine engine, Vector3 pos, String texture, boolean solid){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position.set(pos);
        e.add(p);
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

    public Entity spawn(Totem game, Vector3 pos){
        Entity e = game.engine().createEntity();
        game.engine().addEntity(e);
        e.add(new IDComponent(m_name));
        TransformComponent p = new TransformComponent();
        Vector3 pp = new Vector3(pos);
        pp.x = RenderSystem.unit_to_pixel(pos.x);
        pp.y = RenderSystem.unit_to_pixel(pos.y);
        p.position.set(pp);
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(m_texture));
        e.add(r);

        if(m_solid){
            ColliderComponent c = new ColliderComponent();
            c.m_dynamic = false;
            c.m_solid = true;
            c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));
            e.add(c);
        }
        return e;
    }
}
