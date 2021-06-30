package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.kingstonops.totem.Debug;
import imgui.ImGui;

import java.util.HashMap;
import java.util.Map;

public class DialougeComponent implements Component {

    public static abstract class DialougePart {

        public static HashMap<String, DialougePart> dialouge_registry = new HashMap<>();

        public static void register(DialougePart dialouge){
            dialouge_registry.put(dialouge.name(), dialouge);
        }
        public static DialougePart get(String name){
            return dialouge_registry.get(name);
        }

        public DialougePart(){}


        public String name(){
            return m_name;
        }

        protected DialougeComponent m_dialouge;
        protected DialougeTrigger m_on_trigger;
        protected String m_name;

        // todo remove this as multiple DialougeParts may be being used at the same time
        public void set_dialouge(DialougeComponent d){
            m_dialouge = d;
        }
        public DialougeTrigger on_trigger(){
            return m_on_trigger;
        }

        public static interface DialougeTrigger{
            void trigger();
        }


        public abstract void trigger();
        public void process(){

        }

        public static class Single extends DialougePart {
            String m_speech;
            DialougePart m_prev;
            DialougePart m_next;

            public Single(){
            }
            public Single(String name, String speech, DialougeTrigger trigger){
                m_name = name;
                m_speech = speech;
                m_on_trigger = trigger;
            }
            public DialougePart set_next(DialougePart next){
                m_next=next;
                return this;
            }
            @Override
            public void trigger(){
                m_dialouge.set_active(this);
                Debug.dgb(m_speech);
                if(m_on_trigger!=null)
                    m_on_trigger.trigger();
            }

            private void next(){
                if(m_next!=null){
                    m_next.set_dialouge(m_dialouge);
                    m_next.trigger();
                }else{
                    m_dialouge.set_active(m_dialouge.m_root_part);
                    m_dialouge.close();
                }
            }

            @Override
            public void process(){
                ImGui.begin("dialouge");
                ImGui.text(m_speech);
                if(ImGui.button("next")){
                    this.next();
                }
                if(ImGui.button("close")){
                    this.m_dialouge.close();
                }
                ImGui.end();
            }
        }

        /*public static class Input extends DialougePart {
            String m_speech;
            String m_input;


            @Override
            public void trigger(){
                Debug.dgb(m_speech);
                Debug.dgb("... input pls");

            }
        }*/

        /*public static class ChoiceDialouge extends DialougePart {
            HashMap<String, DialougePart> m_speeches = new HashMap<>();


            @Override
            public void trigger(){
                for(Map.Entry<String, DialougePart> entry : m_speeches.entrySet()){
                    Debug.dgb(entry.getKey());
                }
            }

            @Override
            public void process(){
                ImGui.begin("dialouge");
                for(Map.Entry<String, DialougePart> entry : m_speeches.entrySet()){
                    if(ImGui.button(entry.getKey())){
                        entry.getValue().trigger();
                    }
                }
                ImGui.end();
            }

            public ChoiceDialouge add_choice(String dialouge, DialougePart to){
                m_speeches.put(dialouge, to);
                return this;
            }
        }*/


    }
    // depending on the dialouge path, this will be updated
    private DialougePart m_active_part;
    // this is the root speech part
    private DialougePart m_root_part;

    // if this is currently being shown now
    private boolean m_is_in_conversation = false;

    public DialougeComponent(){}
    // todo we should have a method call call dialouge where we can just start any registered dialouge...
    public DialougeComponent(DialougePart part){
        m_root_part=part;
        m_active_part=part;
        part.set_dialouge(this);
    }

    public void start_dialouge(String name){
        DialougePart dialouge_part = DialougePart.get(name);
        if(dialouge_part!=null){
            m_root_part=dialouge_part;
            m_active_part=dialouge_part;
            dialouge_part.set_dialouge(this);
            trigger_active();
        }
    }

    public boolean is_in_conversation(){
        return m_is_in_conversation;
    }
    public void close(){
        m_is_in_conversation=false;
    }

    public void trigger_active(){
        m_active_part.trigger();
        m_is_in_conversation = true;
    }

    public void process(){
        m_active_part.process();
    }
    public void exit(){
        m_is_in_conversation = false;
    }



    public DialougeComponent set_root(DialougePart root_part){
        m_root_part=root_part;
        m_active_part=root_part;
        return this;
    }

    public DialougeComponent set_active(DialougePart active){
        m_active_part = active;
        return this;
    }

    public DialougePart active(){
        return m_active_part;
    }
}
