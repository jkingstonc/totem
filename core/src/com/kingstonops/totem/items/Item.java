package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Registry;
import com.kingstonops.totem.Totem;

import java.util.HashMap;

/*
* An item
* */
public class Item implements Component {

    // todo we should move to this system which is more flexible
    public static class Properties{}
    public interface Use{
        void use(Totem game);
    }
    public static class Descriptor{
        int m_id = -1;
        String m_name;
        String m_texture;
        Properties m_props;
        Use m_use;
    }

    // new Item.Descriptor("test_item", "test.png", null, ()->{})
    // new Item.Descriptor("picaxe", "pic.png", {int health}

    public enum Rarity{
        COMMON,
        UNCOMMON,
        RARE,
        SUPER_RARE,
        LEGENDARY
    }

    public static Registry<Item> registry = new Registry<>();

    public Properties m_properties;
    public Use m_use;


    public String m_name;
    public String m_texture;
    public Rarity m_rarity;

    public Item(String name, String texture, Rarity rarity){
        m_name=name;
        m_texture=texture;
        m_rarity = rarity;
    }
    public Item(){}

    public void on_use(com.kingstonops.totem.Totem game, Vector3 pos){}
}