package com.kingstonops.totem.world.zones;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.world.animals.Animal;
import com.kingstonops.totem.world.tiles.Tile;

import java.util.ArrayList;

public class Zone {

    public static int BG_LAYER = 0;
    public static int DECOR_LAYER = 1;
    public static int NPC_LAYER = 2;
    public static int ANIMAL_LAYER = 3;
    public static int PLAYER_LAYER = 4;

    public static ArrayList<Entity> from_file(Totem game, String file){
        TiledMap tiled_map = new TmxMapLoader().load(file);
        assert tiled_map != null;

        ArrayList<com.badlogic.ashley.core.Entity> entities = new ArrayList<>();

        TiledMapTileLayer layer_0 = (TiledMapTileLayer)tiled_map.getLayers().get(BG_LAYER);
        for(int x=0;x<layer_0.getWidth();x++){
            for(int y=0;y<layer_0.getHeight();y++){
                TiledMapTileLayer.Cell cell = layer_0.getCell(x,y);
                if(cell!=null) {
                    String name = "" + cell.getTile().getProperties().get("name");
                    entities.add(Tile.registry.instantiate(name).spawn(game, new Vector3(x, y, BG_LAYER)));
                }
            }
        }

        TiledMapTileLayer decor_layer = (TiledMapTileLayer)tiled_map.getLayers().get(DECOR_LAYER);
        for(int x=0;x<decor_layer.getWidth();x++){
            for(int y=0;y<decor_layer.getHeight();y++){
                TiledMapTileLayer.Cell cell = decor_layer.getCell(x,y);
                if(cell!=null) {
                    String name = "" + cell.getTile().getProperties().get("name");
                    entities.add(Tile.registry.instantiate(name).spawn(game, new Vector3(x, y, DECOR_LAYER)));
                }
            }
        }

        TiledMapTileLayer animal_layer = (TiledMapTileLayer)tiled_map.getLayers().get(ANIMAL_LAYER);
        for(int x=0;x<animal_layer.getWidth();x++){
            for(int y=0;y<animal_layer.getHeight();y++){
                TiledMapTileLayer.Cell cell = animal_layer.getCell(x,y);
                if(cell!=null){
                    String name = "" + cell.getTile().getProperties().get("name");
                    entities.add(Animal.registry.instantiate(name).spawn(game, new Vector3(x, y, ANIMAL_LAYER)));
                }
            }
        }

        tiled_map.dispose();
        return entities;
    }
}
