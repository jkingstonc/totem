package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;
import imgui.ImGui;

public class DialougeSystem extends EntitySystem {


    private Entity m_active_zone;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<DialougeComponent> m_dialouge_mapper = ComponentMapper.getFor(DialougeComponent.class);

    Totem m_game;

    public DialougeSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(DialougeComponent.class).get());
    }

    private DialougeComponent m_current_dialouge;

    @Override
    public void update(float dt){
        m_current_dialouge=null;
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            DialougeComponent d = m_dialouge_mapper.get(e);
            if(d.is_in_conversation()){
                m_current_dialouge = d;
            }
        }

        if(m_current_dialouge!=null){
            m_current_dialouge.process();
        }
    }
}
