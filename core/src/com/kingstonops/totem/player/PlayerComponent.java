package com.kingstonops.totem.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;

public class PlayerComponent implements Component {


    public Entity m_holding_item = null;

    int m_selected_item = 0;
    Vector3 last_selected_tile = new Vector3();

    int m_coins = 150;

    public int coins(){
        return m_coins;
    }
}
