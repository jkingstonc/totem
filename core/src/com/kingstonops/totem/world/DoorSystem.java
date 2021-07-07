package com.kingstonops.totem.world;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;

public class DoorSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_transform_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<ColliderComponent> m_collider_mapper = ComponentMapper.getFor(ColliderComponent.class);
    private ComponentMapper<DoorComponent> m_door_mapper = ComponentMapper.getFor(DoorComponent.class);

    Totem m_game;

    public DoorSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, ColliderComponent.class, DoorComponent.class).get());
    }

    @Override
    public void update(float dt){
        for(int i = 0;i<m_entities.size();i++){
            Entity e1 = m_entities.get(i);
            TransformComponent t = m_transform_mapper.get(e1);
            ColliderComponent c = m_collider_mapper.get(e1);
            DoorComponent d = m_door_mapper.get(e1);

            // check if the door is clolliding with an entity

            if(c.m_colliding_with.size()>0) {
                Entity player = c.m_colliding_with.get(0);


                // go into the new world!
                TransformComponent player_transform = player.getComponent(TransformComponent.class);
                assert player_transform!=null;

                if(d.m_should_go) {
                    d.m_should_go = false;
                    player_transform.position.x = d.target().x;
                    player_transform.position.y = d.target().y;

                    m_game.engine().getSystem(WorldSystem.class).to_zone(d.to());
                }
            }
        }
    }
}
