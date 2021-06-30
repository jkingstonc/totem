package com.kingstonops.totem.world.zones;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.DoorComponent;
import com.kingstonops.totem.world.Tile;
import com.kingstonops.totem.world.guys.AIComponent;
import com.kingstonops.totem.world.guys.NPC;

public class Builtin {
    public static void setup_builtin_zones(Totem game){

        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("battle_zone", (zone) ->{
            final int HOUSE_WIDTH = 15, HOUSE_HEIGHT = 15;
            for(int x=-HOUSE_WIDTH;x<=HOUSE_WIDTH;x++){
                for(int y=-HOUSE_HEIGHT;y<=HOUSE_HEIGHT;y++){
                    zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "lava.jpg", false));
                }
            }
        }));
        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("starting_house_downstairs", (zone)->{
            final int WIDTH = 10, HEIGHT = 10;

            zone.entities().add(NPC.create(game.engine(),new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(2), 5), "mum.jpg", new AIComponent.AIProvider.BasicStationaryAIProvider(), "basic_greeting_part_0"));
            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-5), 1), "starting_town", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-8), 5)));
            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(-3), RenderSystem.unit_to_pixel(0), 1), "starting_house_upstairs", new Vector3(RenderSystem.unit_to_pixel(-1), RenderSystem.unit_to_pixel(0), 5)));
            final int HOUSE_WIDTH = 5, HOUSE_HEIGHT = 5;
            for(int x=-HOUSE_WIDTH;x<=HOUSE_WIDTH;x++){
                for(int y=-HOUSE_HEIGHT;y<=HOUSE_HEIGHT;y++){
                    if(x==0 && y==-HOUSE_HEIGHT){
                        //zone.entities().add(DoorComponent.create(game.engine(), new Vector3(x, x, 0), "starting_town"));
                    }
                    else if((x==-HOUSE_WIDTH || x==HOUSE_WIDTH) || (y==-HOUSE_HEIGHT || y==HOUSE_HEIGHT)){
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "brick.png", true));
                    }else{
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "wood.png", false));
                    }
                }
            }
        }));
        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("starting_house_upstairs", (zone)->{
            final int WIDTH = 10, HEIGHT = 10;

            zone.entities().add(
                    DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(3), RenderSystem.unit_to_pixel(0), 1), "starting_house_downstairs", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(5))));
            final int HOUSE_WIDTH = 5, HOUSE_HEIGHT = 5;
            for(int x=-HOUSE_WIDTH;x<=HOUSE_WIDTH;x++){
                for(int y=-HOUSE_HEIGHT;y<=HOUSE_HEIGHT;y++){
                    if((x==-HOUSE_WIDTH || x==HOUSE_WIDTH) || (y==-HOUSE_HEIGHT || y==HOUSE_HEIGHT)){
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "brick.png", true));
                    }else{
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "wood.png", false));
                    }
                }
            }
        }));
        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("starting_town", (zone)->{

            zone.entities().add(NPC.create(game.engine(),
                    new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-10), 5), "enemy.png", new AIComponent.AIProvider.BasicStationaryAIProvider(), "basic_battle_part_0"));

            final int WIDTH = 30, HEIGHT = 30;
            for(int x =-WIDTH/2;x<WIDTH/2;x++) {
                for (int y = -HEIGHT / 2; y < HEIGHT / 2; y++) {
                    zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "grass.png", false));
                }
            }
            final int HOUSE_WIDTH=5, HOUSE_HEIGHT=5;
            for(int x=-HOUSE_WIDTH;x<=HOUSE_WIDTH;x++){
                for(int y=-HOUSE_HEIGHT;y<=HOUSE_HEIGHT;y++){
                    if(x==0 && y==-HOUSE_HEIGHT) {
                        //zone.entities().add(DoorComponent.create(game.engine(), new Vector3(x, x, 0), "starting_town"));
                    }else if((x==-HOUSE_WIDTH || x==HOUSE_WIDTH) || (y==-HOUSE_HEIGHT || y==HOUSE_HEIGHT)){
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "brick.png", true));
                    }else{
                        zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "roof.jpg", false));
                    }
                }
            }
            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-5),1), "starting_house_downstairs", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), 5)));


        }));
    }
}
