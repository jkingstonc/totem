package com.kingstonops.totem.world.weather;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;

public class WeatherSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    //private ComponentMapper<DoorComponent> m_door_mapper = ComponentMapper.getFor(DoorComponent.class);

    Totem m_game;

    public WeatherSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        //m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, ColliderComponent.class, DoorComponent.class).get());
    }


    public Weather m_weather = null;

    @Override
    public void update(float dt){
        /*for(int i = 0;i<m_entities.size();i++){
            Entity e1 = m_entities.get(i);

        }*/

        if(m_weather!=null){

        }
    }
}
