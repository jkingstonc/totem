package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.kingstonops.totem.Totem;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryComponent implements Component {

    public void show(){
        ImGui.setNextWindowPos(0,0);
        ImGui.setNextWindowSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        ImGui.begin("inventory");
        ArrayList<String> stack_names = new ArrayList<>();
        for (int i = 0; i < m_items.size(); i++) {
            ItemStack stack = m_items.get(i);
            stack_names.add(stack.items().get(0).m_name + " x " + stack.items().size());
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