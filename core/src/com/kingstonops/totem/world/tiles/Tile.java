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


    public Entity spawn(Totem game){
        Entity e = game.engine().createEntity();
        game.engine().addEntity(e);
        e.add(new IDComponent(m_name));
        TransformComponent p = new TransformComponent();
        p.position.set(new Vector3(0,0,0));
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(m_texture));
        e.add(r);
        ColliderComponent c = new ColliderComponent();
        c.m_dynamic = false;
        c.m_solid = m_solid;
        c.m_bounds = new Vector2(.5f, .5f);
        e.add(c);
        return e;
    }
}
