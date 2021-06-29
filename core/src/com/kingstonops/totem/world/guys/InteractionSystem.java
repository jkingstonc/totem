package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.PlayerComponent;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.input.InputSystem;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.guys.AIComponent;
import com.kingstonops.totem.world.zones.ZoneComponent;
import imgui.ImGui;

public class InteractionSystem extends EntitySystem {


    private Entity m_active_zone;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<InteractionComponent> m_interaction_mapper = ComponentMapper.getFor(InteractionComponent.class);
    private ComponentMapper<ColliderComponent> m_collider_mapper = ComponentMapper.getFor(ColliderComponent.class);



    Totem m_game;

    public InteractionSystem(Totem game){
        m_game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(InteractionComponent.class, ColliderComponent.class).get());
    }


    private Entity previous_player_ineracting_with;
    private Entity player_interacting_with;

    @Override
    public void update(float dt){

        player_interacting_with=null;


        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            InteractionComponent interaction = m_interaction_mapper.get(e);
            ColliderComponent c = m_collider_mapper.get(e);


            for(int j=0;j<c.m_colliding_with.size();j++){
                Entity other_e = c.m_colliding_with.get(j);
                PlayerComponent p = other_e.getComponent(PlayerComponent.class);
                if(p!=null){
                    player_interacting_with = e;
                }
            }
        }

        if(player_interacting_with!=null && player_interacting_with!=previous_player_ineracting_with){
            InteractionComponent i = player_interacting_with.getComponent(InteractionComponent.class);
            Debug.dgb(i.interaction_msg());
        }

        if(player_interacting_with!=null){
            InputSystem input = m_game.engine().getSystem(InputSystem.class);
            InteractionComponent i = player_interacting_with.getComponent(InteractionComponent.class);
            if(input.key_up.contains(i.interaction_key())){
                i.action().trigger();
            }
        }

        previous_player_ineracting_with=player_interacting_with;

    }
}
