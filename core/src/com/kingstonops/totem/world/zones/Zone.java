package com.kingstonops.totem.world.zones;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Prefab;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.DoorComponent;

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


        // todo move to this system
        for(int layer=0;layer<tiled_map.getLayers().getCount();layer++){


            Debug.dbg(""+tiled_map.getLayers().get(layer).getClass());

            if(tiled_map.getLayers().get(layer) instanceof TiledMapTileLayer) {

                TiledMapTileLayer current_layer = (TiledMapTileLayer) tiled_map.getLayers().get(layer);
                for (int x = 0; x < current_layer.getWidth(); x++) {
                    for (int y = 0; y < current_layer.getHeight(); y++) {
                        TiledMapTileLayer.Cell cell = current_layer.getCell(x, y);
                        if (cell != null) {
                            String name = "" + cell.getTile().getProperties().get("name");
                            Entity e = Prefab.registry.get(name).spawn(game);
                            e.getComponent(TransformComponent.class).position.set(new Vector3(x, y, layer));
                            entities.add(e);
                        }
                    }
                }
            }else{
                // object layer
                MapLayer current_layer = tiled_map.getLayers().get(layer);
                for (MapObject obj : current_layer.getObjects()) {

                    // todo dynamically get this
                    final float TILE_SIZE = 256f;


                    Vector3 pos = new Vector3(
                            (int)(Float.parseFloat(obj.getProperties().get("x").toString()) / TILE_SIZE),
                            (int)(Float.parseFloat(obj.getProperties().get("y").toString()) / TILE_SIZE),
                            layer);


                    switch(obj.getName()){
                        case "default_spawn":{

                            Entity player = IDComponent.find(game, "player");
                            TransformComponent t = player.getComponent(TransformComponent.class);
                            t.position.set(pos);

                            Entity camera = IDComponent.find(game, "camera");
                            TransformComponent cam_t = camera.getComponent(TransformComponent.class);
                            cam_t.position.set(pos);


                            break;
                        }
                        case "obj_door":{
                            Entity e = Prefab.registry.get("obj_door").spawn(game);
                            e.getComponent(TransformComponent.class).position.set(pos);
                            DoorComponent d = e.getComponent(DoorComponent.class);
                            d.m_to = "zones/"+obj.getProperties().get("zone").toString()+".tmx";


                            // if the door goes somewhere then find where it goes
                            Object spawn = obj.getProperties().get("spawn");
                            if(spawn!=null){
                                // todo
                            }


                            d.m_target = new Vector3(0,0, RenderSystem.PLAYER_LAYER);
                            entities.add(e);
                            break;
                        }
                    }
                }
            }
        }


        /*TiledMapTileLayer layer_0 = (TiledMapTileLayer)tiled_map.getLayers().get(BG_LAYER);
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
        }*/

        tiled_map.dispose();
        return entities;
    }
}
