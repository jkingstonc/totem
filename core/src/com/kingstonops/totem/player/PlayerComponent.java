package com.kingstonops.totem.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class PlayerComponent implements Component {
    Vector3 last_selected_tile = new Vector3();

    int m_coins = 150;

    public int coins(){
        return m_coins;
    }
}
