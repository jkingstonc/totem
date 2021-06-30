package com.kingstonops.totem.items;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;

public class InventorySystem extends EntitySystem {

    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<InventoryComponent> m_inventory_mapper = ComponentMapper.getFor(InventoryComponent.class);

    Totem m_game;

    public InventorySystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(InventoryComponent.class).get());
    }


    @Override
    public void update(float dt){
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            InventoryComponent inv = m_inventory_mapper.get(e);


        }
    }
}
