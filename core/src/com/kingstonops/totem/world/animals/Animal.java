package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Registry;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.AnimationComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.guys.AIComponent;

public class Animal {
    public static Registry<Animal> registry = new Registry<>();

    private String m_name;
    private String m_texture;

    public Animal(){}
    public Animal(String name, String texture){
        m_name = name;
        m_texture = texture;
    }

    public Entity spawn(Totem game){
        Entity e = game.engine().createEntity();
        game.engine().addEntity(e);
        TransformComponent p = new TransformComponent();
        p.position.set(new Vector3(0,0,0));
        e.add(p);
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(RenderSystem.get(m_texture));
        e.add(r);

        e.add(new MovementComponent());
        AIComponent ai = new AIComponent(new AIComponent.AIProvider.BasicWanderingAIProvider());
        e.add(ai);

        ColliderComponent c = new ColliderComponent();
        c.m_dynamic = true;
        c.m_solid = true;
        c.m_bounds = new Vector2(.5f,.5f);
        e.add(c);

        e.add(new AnimalComponent());
        return e;
    }
}
