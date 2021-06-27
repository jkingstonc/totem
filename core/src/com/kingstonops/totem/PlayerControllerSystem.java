package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class PlayerControllerSystem extends EntitySystem {

    private Engine m_engine;

    public PlayerControllerSystem(Engine engine){
        m_engine =engine;
    }

    @Override
    public void update(float dt){

    }
}
