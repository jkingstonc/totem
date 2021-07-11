package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Prefab;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.player.AnimalLog;
import com.kingstonops.totem.world.WorldSystem;
import com.kingstonops.totem.world.zones.ZoneComponent;

import java.util.*;

// primarily used to spawn/despawn animals
public class AnimalSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    //private ComponentMapper<DoorComponent> m_door_mapper = ComponentMapper.getFor(DoorComponent.class);


    Random r;
    Totem m_game;

    public AnimalSystem(Totem game){
        m_game = game;
        r = new Random(System.currentTimeMillis());
    }

    @Override
    public void addedToEngine(Engine engine) {
        //m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, ColliderComponent.class, DoorComponent.class).get());
    }




    @Override
    public void update(float dt){

        final double ANIMAL_SPAWN_RATE = 0.005f; // try to spawn with this probability


        if(r.nextDouble()<ANIMAL_SPAWN_RATE){

            // for each tile in the zone, check if we can spawn an animal

            // TODO make this concurrent
            List<Entity> zone_entities = m_game.engine().getSystem(WorldSystem.class).m_active_zone.getComponent(ZoneComponent.class).entities();

            for (Iterator<Entity> it = zone_entities.iterator(); it.hasNext();) {

                Entity e = it.next();

                // check if we can spawn an animal on here

                IDComponent id = e.getComponent(IDComponent.class);

                // for each known animal, check if it can spawn on the entity
                if(id!=null) {
                    for (Map.Entry<String, AnimalLog.AnimalStats> animal : AnimalLog.AnimalStats.registry.enumerate().entrySet()) {


                        if (animal.getValue().m_spawns_on.contains(id.id())) {

                            if(r.nextDouble()<0.0005) {

                                TransformComponent e_transform = e.getComponent(TransformComponent.class);

                                // spawn the animal

                                Entity animal_entity = Prefab.registry.get(animal.getValue().m_name).spawn(m_game);
                                animal_entity.getComponent(TransformComponent.class).position.x = e_transform.position.x;
                                animal_entity.getComponent(TransformComponent.class).position.y = e_transform.position.y;

                                Debug.dbg("spawned animal!");
                                return;
                            }

                        }

                    }
                }
            }
        }

    }
}
