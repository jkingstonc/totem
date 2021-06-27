package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<PositionComponent> m_pos_mapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> m_vel_mapper = ComponentMapper.getFor(VelocityComponent.class);

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i = 0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            PositionComponent p = m_pos_mapper.get(e);
            VelocityComponent v = m_vel_mapper.get(e);
            p.x+=v.x;
            p.y+=v.y;
        }
    }
}
