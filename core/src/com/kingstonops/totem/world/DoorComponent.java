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
import com.kingstonops.totem.world.guys.InteractionComponent;

public class DoorComponent implements Component{
    public static Entity create(Engine engine, Vector3 pos, String to, Vector3 target){
        Entity e = engine.createEntity();
        engine.addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position = pos;
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture("door.jpg"));
        e.add(r);

        ColliderComponent c = new ColliderComponent();
        c.m_dynamic = false;
        c.m_solid = false;
        c.m_bounds = new Vector2(RenderSystem.unit_to_pixel(.5f), RenderSystem.unit_to_pixel(.5f));



        DoorComponent d = new DoorComponent(to, target);
        e.add(d);

        InteractionComponent i = new InteractionComponent("to "+to, new InteractionComponent.InteractionAction() {
            @Override
            public void trigger(Entity e) {
                d.m_should_go = true;
            }
        });
        e.add(i);

        e.add(c);
        return e;
    }
    public boolean m_should_go = false;
    private Vector3 m_target;
    private String m_to;
    public String to(){
        return m_to;
    }
    public DoorComponent(String to, Vector3 target){
        m_to=to;m_target=target;
    }
    public Vector3 target(){
        return m_target;
    }
}