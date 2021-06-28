package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.kingstonops.totem.input.InputSystem;

public class PlayerControllerSystem extends EntitySystem {


    private Engine m_engine;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> m_vel_mapper = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<PlayerComponent> m_player_mapper = ComponentMapper.getFor(PlayerComponent.class);

    public PlayerControllerSystem(Engine engine){
        m_engine = engine;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, MovementComponent.class, PlayerComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i = 0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            TransformComponent t = m_pos_mapper.get(e);
            MovementComponent v = m_vel_mapper.get(e);
            PlayerComponent p = m_player_mapper.get(e);

            InputSystem input = m_engine.getSystem(InputSystem.class);

            if(input.key_held== Input.Keys.W){
                v.acceleration.y=100;
            }else if(input.key_held== Input.Keys.S){
                v.acceleration.y=-100;
            }
            if(input.key_held== Input.Keys.A){
                v.acceleration.x=-100;
            }else if(input.key_held== Input.Keys.D){
                v.acceleration.x=100;
            }
        }
    }
}
