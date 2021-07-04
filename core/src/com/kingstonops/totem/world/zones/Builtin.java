package com.kingstonops.totem.world.zones;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.Chest;
import com.kingstonops.totem.world.DoorComponent;
import com.kingstonops.totem.world.animals.Animal;
import com.kingstonops.totem.world.tiles.Tile;
import com.kingstonops.totem.world.Tree;
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

            zone.entities().add(NPC.create(game.engine(),new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(2), 5), "mum.png", new AIComponent.AIProvider.BasicStationaryAIProvider(), "basic_greeting_part_0"));
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

            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(3), RenderSystem.unit_to_pixel(0), 1), "starting_house_downstairs", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(5))));
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


            zone.entities().add(Animal.registry.instantiate("cow").spawn(game, new Vector3(0,-12,1)));
            zone.entities().add(NPC.create(game.engine(),new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-10), 5), "enemy.png", new AIComponent.AIProvider.BasicWanderingAIProvider(), "basic_greeting_part_0"));

            final int WIDTH = 50, HEIGHT = 50;
            for(int x =-WIDTH/2;x<WIDTH/2;x++) {
                for (int y = -HEIGHT / 2; y < HEIGHT / 2; y++) {
                    if(x%4==0 && y%4==0) {
                        zone.entities().add(Tree.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), RenderSystem.DECOR_LAYER), "tree.png", true));
                    }
                    /*if(x%10==0 && y%10==0) {
                        zone.entities().add(NPC.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 5), "enemy.png", new AIComponent.AIProvider.BasicWanderingAIProvider(), "basic_greeting_part_0"));
                    }*/

                    zone.entities().add(Tile.registry.instantiate("grass").spawn(game, new Vector3(x, y, 0)));
                }

            }

            for(int x =10;x<15;x++) {
                for (int y = 10; y<15; y++) {
                    zone.entities().add(Tile.registry.instantiate("water").spawn(game, new Vector3(x, y, 0)));
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

            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(3), RenderSystem.unit_to_pixel(-15), 1), "starting_shop", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), 5)));
            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(-3), RenderSystem.unit_to_pixel(-15), 1), "starting_cave", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), 5)));

        }));

        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("starting_shop", (zone)->{
            final int WIDTH = 10, HEIGHT = 10;

            zone.entities().add(NPC.create(game.engine(),new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(2), 5), "enemy.png", new AIComponent.AIProvider.BasicStationaryAIProvider(), "basic_shop_part_0"));

            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-5), 1), "starting_town", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-8), 5)));
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

        ZoneComponent.register(new ZoneComponent.ZoneDescriptor("starting_cave", (zone)->{
            zone.entities().add(NPC.create(game.engine(),
                    new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(0), 5), "enemy.png", new AIComponent.AIProvider.BasicStationaryAIProvider(), "basic_battle_part_0"));
            zone.entities().add(DoorComponent.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-5), 1), "starting_town", new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(-8), 5)));
            final int WIDTH = 5, HEIGHT = 5;
            for(int x=-WIDTH;x<=WIDTH;x++){
                for(int y=-HEIGHT;y<=HEIGHT;y++){
                    zone.entities().add(Tile.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(x), RenderSystem.unit_to_pixel(y), 0), "rock.png", false));
                }
            }
            zone.entities().add(Chest.create(game.engine(), new Vector3(RenderSystem.unit_to_pixel(0), RenderSystem.unit_to_pixel(3), RenderSystem.DECOR_LAYER), "chest.png"));
        }));
    }
}
