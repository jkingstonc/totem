package com.kingstonops.totem.world.tiles;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class Tree extends Tile{
    public Tree(){
        super("tree", "tree.png", true);
    }

    @Override
    public Entity spawn(Totem game) {
        Entity e = super.spawn(game);
        e.getComponent(TransformComponent.class).scale.set(new Vector3(1,2,1));
        return e;
    }
}
