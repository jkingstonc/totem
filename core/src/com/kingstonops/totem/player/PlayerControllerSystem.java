package com.kingstonops.totem.player;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.input.InputSystem;
import com.kingstonops.totem.items.InventoryComponent;
import com.kingstonops.totem.items.ItemStack;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.ArrayList;

public class PlayerControllerSystem extends EntitySystem {

    private Totem m_game;
    private ImmutableArray<Entity> m_entities;

    private ComponentMapper<TransformComponent> m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> m_vel_mapper = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<PlayerComponent> m_player_mapper = ComponentMapper.getFor(PlayerComponent.class);
    private ComponentMapper<InventoryComponent> m_inventory_mapper = ComponentMapper.getFor(InventoryComponent.class);

    public PlayerControllerSystem(Totem game){
        m_game = game;
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

        InputSystem input = m_game.engine().getSystem(InputSystem.class);

        float accel = v.m_speed;

        if(input.key_held.contains(Input.Keys.SHIFT_LEFT)){
            accel*=2;
        }

        if(input.mouse_up==Input.Buttons.LEFT){
            // do stuff
            RenderSystem r = m_game.engine().getSystem(RenderSystem.class);
            p.last_selected_tile = r.un_project(new Vector3(input.mouse_pos.x, input.mouse_pos.y, 0));
        }

        if(input.key_held.contains(Input.Keys.W)){
            v.acceleration.y=accel;
        }else if(input.key_held.contains(Input.Keys.S)){
            v.acceleration.y=-accel;
        }
        if(input.key_held.contains(Input.Keys.A)){
            v.acceleration.x=-accel;
        }else if(input.key_held.contains(Input.Keys.D)){
            v.acceleration.x=accel;
        }
        if(input.key_up.contains(Input.Keys.P)){
            Debug.DEBUG=!Debug.DEBUG;
        }

        if(input.key_held.contains(Input.Keys.I)){
            inv.show();
        }
        if(input.key_held.contains(Input.Keys.ESCAPE)){
            ImGui.setNextWindowPos(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            ImGui.setNextWindowSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
            ImGui.begin("pause");
            if(ImGui.button("quit")){
                Gdx.app.exit();
            }
            if(ImGui.button("save")){
            }
            if(ImGui.button("load")){
            }
            ImGui.end();
        }

        int update_selected_item = -1;
        if(input.key_up.contains(Input.Keys.NUM_0)){
            p.m_selected_item=0;
            update_selected_item=0;
        }else if(input.key_up.contains(Input.Keys.NUM_1)){
            p.m_selected_item=1;
            update_selected_item=1;
        }else if(input.key_up.contains(Input.Keys.NUM_2)){
            p.m_selected_item=2;
            update_selected_item=2;
        }else if(input.key_up.contains(Input.Keys.NUM_3)){
            p.m_selected_item=3;
            update_selected_item=3;
        }else if(input.key_up.contains(Input.Keys.NUM_4)){
            p.m_selected_item=4;
            update_selected_item=4;
        }else if(input.key_up.contains(Input.Keys.NUM_5)){
            p.m_selected_item=5;
            update_selected_item=5;
        }else if(input.key_up.contains(Input.Keys.NUM_6)){
            p.m_selected_item=6;
            update_selected_item=6;
        }else if(input.key_up.contains(Input.Keys.NUM_7)){
            p.m_selected_item=7;
            update_selected_item=7;
        }else if(input.key_up.contains(Input.Keys.NUM_8)){
            p.m_selected_item=8;
            update_selected_item=8;
        }else if(input.key_up.contains(Input.Keys.NUM_9)){
            p.m_selected_item=9;
            update_selected_item=9;
        }

        if(input.scroll_up){
            p.m_selected_item++;
            if(p.m_selected_item>9){
                p.m_selected_item=0;
            }
            update_selected_item=p.m_selected_item;
        }else if(input.scroll_down){
            p.m_selected_item--;
            if(p.m_selected_item<0){
                p.m_selected_item=9;
            }
            update_selected_item=p.m_selected_item;
        }

        if(update_selected_item>-1){
            if(update_selected_item>inv.m_items.size()-1){
                update_selected_item=0;
                p.m_selected_item=update_selected_item;
            }else if(update_selected_item<0){
                update_selected_item=inv.m_items.size()-1;
                p.m_selected_item=update_selected_item;
            }
            p.m_holding_item.getComponent(RenderComponent.class).texture=new TextureRegion(RenderSystem.get(inv.m_items.get(update_selected_item).items().get(0).m_texture));
        }

        if(input.mouse_up== Input.Buttons.LEFT){
            // use the first item
            inv.m_items.get(p.m_selected_item).items().get(0).on_use(m_game, p.last_selected_tile);
        }

        Vector3 item_pos = new Vector3(t.position);
        item_pos.x+=.5;
        item_pos.y+=.5;
        item_pos.z++;
        p.m_holding_item.getComponent(TransformComponent.class).position.set(item_pos);

        if(Debug.DEBUG) {
            ImGui.begin("player info");
            ImGui.text("coins "+p.coins());
            ImGui.text("pos " + t.position);
            ImGui.text("selected tile " + p.last_selected_tile);
            ArrayList<String> stack_names = new ArrayList<>();
            for (int i = 0; i < inv.m_items.size(); i++) {
                ItemStack stack = inv.m_items.get(i);
                stack_names.add(stack.items().get(0).m_name + " x " + stack.items().size());
            }
            ImGui.listBox("inventory", new ImInt(0), (String[]) stack_names.toArray(new String[0]));
            ImGui.end();
        }
    }
}
