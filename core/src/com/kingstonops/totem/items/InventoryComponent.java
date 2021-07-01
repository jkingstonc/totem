package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.kingstonops.totem.Totem;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryComponent implements Component {



    public static HashMap<String, Item.ItemDescriptor> item_registry = new HashMap<>();

    public static void register_all(Totem game){
        register(new Item.ItemDescriptor("basic_health_potion"));
        register(new Item.ItemDescriptor("basic_totem"));
        register(new Item.ItemDescriptor("pickaxe"));
        register(new Item.ItemDescriptor("shovel"));
        register(new Item.ItemDescriptor("axe"));
    }
    public static void register(Item.ItemDescriptor descriptor){
        descriptor.m_id = item_registry.size();
        item_registry.put(descriptor.name(), descriptor);
    }
    public static Item.ItemDescriptor get(String name){
        return item_registry.get(name);
    }

    public void show(){
        ImGui.setNextWindowPos(0,0);
        ImGui.setNextWindowSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        ImGui.begin("inventory");
        ArrayList<String> stack_names = new ArrayList<>();
        for (int i = 0; i < m_items.size(); i++) {
            ItemStack stack = m_items.get(i);
            stack_names.add(stack.descriptor().name() + " x " + stack.items().size());
        }
        ImGui.listBox("items", new ImInt(0), (String[]) stack_names.toArray(new String[0]));
        ImGui.end();
    }




    public ArrayList<ItemStack> m_items = new ArrayList<>();



    public boolean put(Item item){
        for(int i=0;i<m_items.size();i++){
            if(m_items.get(i).can_put(item)){
                m_items.get(i).put(item);
                return true;
            }
        }
        m_items.add(new ItemStack(item));
        return true;
    }

}