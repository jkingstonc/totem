package com.kingstonops.totem.items;

import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;

public class AnimalLog extends Item {
    public AnimalLog(){
        super("item_animal_log", "animal_log.png", Rarity.COMMON);
    }

    @Override
    public void on_use(Totem game, Vector3 pos) {

        // view the animal log
        Debug.dbg("--- animal log ---");


        // spawn UI

    }
}
