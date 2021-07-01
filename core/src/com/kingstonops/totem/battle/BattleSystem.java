package com.kingstonops.totem.battle;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;

public class BattleSystem extends EntitySystem {

    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<BattleComponent> m_battle_mapper = ComponentMapper.getFor(BattleComponent.class);

    Totem m_game;

    public BattleSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(BattleComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            BattleComponent b = m_battle_mapper.get(e);


        }
    }
}
