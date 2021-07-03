package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Registry;

import java.util.HashMap;

/*
* An item
* */
public class Item implements Component {

    public static Registry<Item> registry = new Registry<>();

    public String m_name;
    public String m_texture;

    public Item(){}

    public void on_use(com.kingstonops.totem.Totem game, Vector3 pos){}
}