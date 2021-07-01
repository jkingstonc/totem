package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;
import com.kingstonops.totem.Debug;

public class InteractionComponent implements Component {


    public static abstract class InteractionAction{
        public abstract void trigger();


        public static class HelloInteractionAction extends InteractionAction{
            @Override
            public void trigger(){
                Debug.dgb("hello, world!");
            }
        }
    }
    private String m_interaction_msg = "press e to interact";
    private int m_interaction_key = Input.Keys.E;
    private InteractionAction m_action = new InteractionAction.HelloInteractionAction();

    public InteractionComponent(){}
    public InteractionComponent(String interaction_msg, InteractionAction action){
        m_interaction_msg = interaction_msg;
        m_action = action;
    }
    public String interaction_msg(){
        return m_interaction_msg;
    }

    public int interaction_key(){
        return m_interaction_key;
    }

    public InteractionAction action(){
        return m_action;
    }
}