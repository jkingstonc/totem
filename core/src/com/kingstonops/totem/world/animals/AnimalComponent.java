package com.kingstonops.totem.world.animals;

import com.badlogic.ashley.core.Component;

public class AnimalComponent implements Component {

    // the item the player holds this can be lured by
    public String m_lured_by;

    // the entity ID this can spawn on
    public String m_spawns_on;

}
