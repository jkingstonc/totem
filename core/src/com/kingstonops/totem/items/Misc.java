package com.kingstonops.totem.items;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;

public class Misc {
    public static class RustyGear extends Item{
        public RustyGear(){
            super("rusty_gear", "rusty_gear.jpg", Rarity.LEGENDARY);
        }
        @Override
        public void on_use(Totem game, Vector3 pos) {
            super.on_use(game, pos);


        }
    }
}
