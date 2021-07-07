package com.kingstonops.totem.world.objects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Prefab;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.world.DoorComponent;
import com.kingstonops.totem.world.guys.InteractionComponent;
import com.kingstonops.totem.world.tiles.Tile;

public class Door extends Tile {

    public Door(){
        super("door", "door.png", false);
    }

    @Override
    public Entity spawn(Totem game) {
        Entity e = super.spawn(game);
        e.getComponent(RenderComponent.class).m_show=false;
        DoorComponent d = new DoorComponent();
        e.add(d);
        InteractionComponent i = new InteractionComponent("use door", new InteractionComponent.InteractionAction() {
            @Override
            public void trigger(Entity player) {
                d.m_should_go = true;
            }
        });
        e.add(i);
        return e;
    }
}
