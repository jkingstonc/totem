package com.kingstonops.totem.world;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;

public class WorldSystem extends EntitySystem {

    public void to_zone(String name){

        Debug.dgb("to zone "+name);

        // first remove the entities of the previous zone
        for(int i = 0;i<m_entities.size();i++){
            Debug.dgb("removing entity in previous zone!");
            Entity e = m_entities.get(i);
            ZoneComponent z = m_zone_mapper.get(e);
            Debug.dgb("z = "+z);
            for(int j=0;j<z.entities().size();j++){
                m_game.engine().removeEntity(z.entities().get(j));
            }
            m_game.engine().removeEntity(e);
        }

        Entity e = m_game.engine().createEntity();
        ZoneComponent zone = new ZoneComponent(ZoneComponent.get(name));
        e.add(zone);
        m_game.engine().addEntity(e);

        zone.descriptor().setup().setup(zone);
    }

    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<ZoneComponent> m_zone_mapper = ComponentMapper.getFor(ZoneComponent.class);


    Totem m_game;

    public WorldSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(ZoneComponent.class).get());
    }

    @Override
    public void update(float dt){

    }
}