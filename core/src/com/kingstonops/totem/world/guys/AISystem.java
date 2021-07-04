package com.kingstonops.totem.world;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;
import imgui.ImGui;

public class AISystem extends EntitySystem {


    private Entity m_active_zone;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<AIComponent> m_ai_mapper = ComponentMapper.getFor(AIComponent.class);


    Totem m_game;

    public AISystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(AIComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            AIComponent ai = m_ai_mapper.get(e);
            ai.provider().process(m_game, e);
        }
    }
}
