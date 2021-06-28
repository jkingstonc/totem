package com.kingstonops.totem.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/*
* An item
* */
public class Item {

    public static class ItemDescriptor{
        private int m_id = 0;
        private String m_name;
        private TextureRegion m_icon;
        private Placeable m_placeable;
        public int id(){
            return m_id;
        }
        public String name(){
            return m_name;
        }

        public ItemDescriptor(String name){
            m_name=name;
        }
    }

    public static Item instance(String name){
        return new Item(item_registry.get(name));
    }

    public static void register(ItemDescriptor descriptor){
        descriptor.m_id=item_registry.size();
        item_registry.put(descriptor.m_name, descriptor);
    }
    /*
    * e.g. health, etc
    * */
    public static class ItemProperty{

    }

    public static HashMap<String, ItemDescriptor> item_registry = new HashMap<>();

    private ItemDescriptor m_descriptor;
    private ItemProperty m_properties;

    public ItemProperty properties(){
        return m_properties;
    }

    public ItemDescriptor descriptor(){
        return m_descriptor;
    }

    public Item(ItemDescriptor descriptor){
        m_descriptor = descriptor;
    }

    public void place(){
        if(m_descriptor.m_placeable!=null)
            m_descriptor.m_placeable.place();
    }


}