package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.input.InputSystem;
import com.kingstonops.totem.items.InventoryComponent;
import com.kingstonops.totem.items.Item;
import com.kingstonops.totem.items.ItemStack;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.Tile;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.ArrayList;

public class PlayerControllerSystem extends EntitySystem {

    private final float SPEED = 250f;

    private Engine m_engine;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> m_vel_mapper = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<PlayerComponent> m_player_mapper = ComponentMapper.getFor(PlayerComponent.class);
    private ComponentMapper<InventoryComponent> m_inventory_mapper = ComponentMapper.getFor(InventoryComponent.class);

    public PlayerControllerSystem(Engine engine){
        m_engine = engine;
    }

    @Override
    public void addedToEngine(Engine engine) {
        m_entities = engine.getEntitiesFor(Family.all(TransformComponent.class, MovementComponent.class, PlayerComponent.class, InventoryComponent.class).get());
    }

    @Override
    public void update(float dt){


        Entity e = m_entities.get(0);
        TransformComponent t = m_pos_mapper.get(e);
        MovementComponent v = m_vel_mapper.get(e);
        PlayerComponent p = m_player_mapper.get(e);
        InventoryComponent inv = m_inventory_mapper.get(e);

        InputSystem input = m_engine.getSystem(InputSystem.class);

        if(input.mouse_up==Input.Buttons.LEFT){
            // do stuff
            RenderSystem r = m_engine.getSystem(RenderSystem.class);
            p.last_selected_tile = r.un_project(new Vector3(input.mouse_pos.x, input.mouse_pos.y, 0));
            Tile.create(m_engine, new Vector3(p.last_selected_tile.x, p.last_selected_tile.y, 1), "guy.png");
        }

        if(input.key_held.contains(Input.Keys.W)){
            v.acceleration.y=SPEED;
        }else if(input.key_held.contains(Input.Keys.S)){
            v.acceleration.y=-SPEED;
        }
        if(input.key_held.contains(Input.Keys.A)){
            v.acceleration.x=-SPEED;
        }else if(input.key_held.contains(Input.Keys.D)){
            v.acceleration.x=SPEED;
        }
        if(input.key_up.contains(Input.Keys.P)){
            Debug.DEBUG=!Debug.DEBUG;
        }

        if(Debug.DEBUG) {
            ImGui.begin("player info");
            ImGui.text("player pos " + t.position);
            ImGui.text("selected tile " + p.last_selected_tile);
            ArrayList<String> stack_names = new ArrayList<>();
            for (int i = 0; i < inv.m_items.size(); i++) {
                ItemStack stack = inv.m_items.get(i);
                stack_names.add(stack.descriptor().name() + " x " + stack.items().size());
            }
            ImGui.listBox("inventory", new ImInt(0), (String[]) stack_names.toArray(new String[0]));
            ImGui.end();
        }
    }
}
