package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;

import java.util.ArrayList;

public class Dog extends Animal{

    public Dog() {
        super("dog", "dog.png");
    }

    @Override
    public Entity spawn(Totem game) {
        Entity e = super.spawn(game);
        e.getComponent(AIComponent.class).m_ai_provier = new AIComponent.AIProvider.BasicWanderingAIProvider();
        e.getComponent(TransformComponent.class).scale.set(new Vector3(.5f,.5f,1));
        e.getComponent(ColliderComponent.class).m_bounds.set(new Vector2(.1f,.1f));
        return e;
    }
}
