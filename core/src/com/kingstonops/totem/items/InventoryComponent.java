package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Component;

import java.util.ArrayList;

public class InventoryComponent implements Component {
    public ArrayList<ItemStack> m_items = new ArrayList<>();



    public boolean put(Item item){
/*        for(int i=0;i<m_items.size();i++){
            if(m_items.get(i).can_put(item)){
                m_items.get(i).put(item);
                return true;
            }
        }*/
        m_items.add(new ItemStack(item));
        return true;
    }

}