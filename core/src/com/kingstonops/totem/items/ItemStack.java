package com.kingstonops.totem.items;

import java.util.ArrayList;

public class ItemStack {

    private ArrayList<Item> m_items = new ArrayList<>();

    public ItemStack(){}

    public ItemStack(Item item){
        m_items.add(item);
    }

    public ArrayList<Item> items(){
        return m_items;
    }

    public void put(Item item){
        m_items.add(item);
    }

    public boolean can_put(Item item){
        return m_items.get(0).m_name.equals(item.m_name);
    }

}
