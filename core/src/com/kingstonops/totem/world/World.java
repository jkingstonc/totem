package com.kingstonops.totem.world;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.*;
import com.kingstonops.totem.world.tiles.Tile;

public class World {

    private static final int WIDTH = 20;
    private static final int HEIGHT = WIDTH;

    private Totem m_game;




    public World(Totem game){
        m_game = game;
        for(int x =-WIDTH/2;x<WIDTH/2;x++){
            for(int y =-HEIGHT/2;y<HEIGHT/2;y++){
                Tile.create(game.engine(), new Vector3(x, y, 0), "grass.png", false);
            }
        }

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
