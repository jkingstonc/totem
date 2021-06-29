package com.kingstonops.totem.world;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;

public class DoorSystem extends EntitySystem {
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_transform_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<ColliderComponent> m_collider_mapper = ComponentMapper.getFor(ColliderComponent.class);
    private ComponentMapper<DoorComponent> m_door_mapper = ComponentMapper.getFor(DoorComponent.class);


    public DoorSystem(){
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
            System.out.println("door colliding with "+c.m_colliding_with.size());

            if(c.m_colliding_with.size()>0) {
                Entity player = c.m_colliding_with.get(0);


                // go into the new world!
                player.getComponent(TransformComponent.class).position.x = RenderSystem.unit_to_pixel(5f);
            }
        }
    }
}
