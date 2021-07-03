package com.kingstonops.totem.items;


import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;

import javax.swing.text.html.parser.Entity;

/*
*
* Item.register(()->{new Pickaxe()});
* Item.instance("pickaxe");
*
* */
public class Pickaxe extends Item{


    public Pickaxe(){
        m_name="pickaxe";
        m_texture="pickaxe.png";
    }

    int health = 100;

    // called when right clicked
    public void on_use(com.kingstonops.totem.Totem game, Vector3 pos){

        Debug.dgb("pickaxe use = " + pos);


    }


}
