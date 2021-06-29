package com.kingstonops.totem.world;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.world.zones.Zone;

public class House extends Zone {
    public House(Totem game){

        Tile.create(game.engine(), new Vector3(-3, -3, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-2, -3, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-1, -3, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(0, -3, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(0, -2, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(0, -1, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(0, 0, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-3, 0, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-2, 0, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-3, 0, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-3, -1, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-3, -2, 0), "brick.png", true);
        Tile.create(game.engine(), new Vector3(-3, -3, 0), "brick.png", true);


    }
}
