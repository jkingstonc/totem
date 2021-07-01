package com.kingstonops.totem.items;

import java.util.ArrayList;

public class ItemStack {

    private ArrayList<Item> m_items = new ArrayList<>();
    private Item.ItemDescriptor m_item_descriptor;

    public ItemStack(){}

    public ItemStack(Item item){
        m_items.add(item);
        m_item_descriptor = item.descriptor();
    }

    public ArrayList<Item> items(){
        return m_items;
    }

    public Item.ItemDescriptor descriptor(){
        return m_item_descriptor;
    }

    public void put(Item item){
        m_items.add(item);
    }

    public boolean can_put(Item item){
        return m_item_descriptor.id()==item.descriptor().id();
    }

}
