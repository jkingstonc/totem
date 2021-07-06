package com.kingstonops.totem;

import com.badlogic.ashley.core.Entity;

public abstract class Prefab {

    public interface PrefabSpawn{
        Entity spawn(Totem game);
    }

    public static Registry<PrefabSpawn> registry = new Registry<>();

    /*
    *
    * Prefab.registry.register("tile_grass", (g)->new Tile("grass"))
     * Prefab.registry.register("tile_grass", new Tile())
     *
     * Prefab.registry.instantiate("tile_grass")
     * Prefab.registry.instantiate("tile_grass").spawn(game);
     *
    * */

    public abstract Entity spawn(Totem game);

}
