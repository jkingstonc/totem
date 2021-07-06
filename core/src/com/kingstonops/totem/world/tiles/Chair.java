package com.kingstonops.totem.world.tiles;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.world.guys.InteractionComponent;

public class Chair extends Tile{
    public Chair(){
        super("chair", "chair.jpg", true);
    }

    @Override
    public Entity spawn(Totem game) {
        Entity e = super.spawn(game);
        InteractionComponent i = new InteractionComponent("press e to sit down", new InteractionComponent.InteractionAction() {
            @Override
            public void trigger(Entity e) {

            }
        });
        e.add(i);
        return e;
    }
}
