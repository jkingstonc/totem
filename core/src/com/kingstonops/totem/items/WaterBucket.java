package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.*;
import com.kingstonops.totem.physics.TransformComponent;

public class WaterBucket extends Item{
    public WaterBucket(){
        super("water_bucket_item", "water_bucket.png", Rarity.COMMON);
    }

    @Override
    public void on_use(Totem game, Vector3 pos) {

        Entity e = Utils.raycast_entity(game, pos);
        if(e!=null){

            // check if item is a water tank
            if(e.getComponent(IDComponent.class).id().equals("obj_water_tank_empty")){


                Entity new_e = Prefab.registry.instantiate("obj_water_tank_full").spawn(game);
                new_e.getComponent(TransformComponent.class).position.set(e.getComponent(TransformComponent.class).position);
                Debug.dbg("found water tank!");
                game.engine().removeEntity(e);

            }

        }
    }
}
