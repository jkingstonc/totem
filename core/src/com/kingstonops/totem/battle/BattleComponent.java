package com.kingstonops.totem.battle;

import com.badlogic.ashley.core.Component;

/*
* Battles are turn based strategic fights.
* Each turn, the player can use a totem
* */
public class BattleComponent implements Component {
    private int m_turn = 0;
}
