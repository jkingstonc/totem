package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;

public class AnimationSystem extends EntitySystem {

    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<AnimationComponent> m_animations_mapper = ComponentMapper.getFor(AnimationComponent.class);

    Totem m_game;

    public AnimationSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(AnimationComponent.class).get());
    }


    @Override
    public void update(float dt){
        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            AnimationComponent a = m_animations_mapper.get(e);


        }
    }
}
