package com.kingstonops.totem.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.*;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class World {

    private static final int WIDTH = 20;
    private static final int HEIGHT = WIDTH;

    private Totem m_game;

    public World(Totem game){
        m_game = game;
        for(int x =-WIDTH/2;x<WIDTH/2;x++){
            for(int y =-HEIGHT/2;y<HEIGHT/2;y++){
                Tile.create(game.engine(), new Vector3(x, y, 0), "grass.png");
            }
        }

    }



}
