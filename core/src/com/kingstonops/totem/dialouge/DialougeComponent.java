package com.kingstonops.totem.dialouge;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.Utils;
import com.kingstonops.totem.world.WorldSystem;
import imgui.ImGui;

import java.util.HashMap;
import java.util.Map;

public class DialougeComponent implements Component {



    public static void register_all(Totem game){
        // todo we should actually register each part seperately then we can just reference the other parts...
        DialougeComponent.DialougePart.register(
                new DialougeComponent.DialougePart.Single("basic_greeting_part_0", "hello there!", null)
                        .set_next(new DialougeComponent.DialougePart.Single("basic_greeting_part_1", "have a good day :)", null)));
        DialougeComponent.DialougePart.register(
                new DialougeComponent.DialougePart.Single("basic_battle_part_0", "so you have chosen death...", ()->{
                    game.engine().getSystem(WorldSystem.class).to_zone("battle_zone");
                }));

        DialougeComponent.DialougePart.register(new DialougeComponent.DialougePart.Single("basic_shop_thanks_0", "thank you for shopping!", null));
        DialougeComponent.DialougePart.register(
                new DialougeComponent.DialougePart.Choice("basic_shop_part_0", "what would you like?", new HashMap<String, Utils.Tuple<DialougeComponent.DialougePart.DialougeTrigger, String>>(){{
                    put("healing potion", new Utils.Tuple<>(null, "basic_shop_thanks_0"));
                    put("mana potion", new Utils.Tuple<>(null, "basic_shop_thanks_0"));
                }})
        );

    }
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
            public Single(String speech, DialougeTrigger trigger){
                m_speech = speech;
                m_on_trigger = trigger;
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
                Debug.dbg(m_speech);
                if(m_on_trigger!=null)
                    m_on_trigger.trigger();
            }

            private void next(){
                if(m_next!=null){
                    m_next.set_dialouge(m_dialouge);
                    m_dialouge.set_active(m_next);
                    m_dialouge.show_active();
                }else{
                    m_dialouge.set_active(m_dialouge.m_root_part);
                    m_dialouge.close();
                }
            }

            private void prev(){
                if(m_prev!=null){
                    m_prev.set_dialouge(m_dialouge);
                    m_dialouge.set_active(m_prev);
                    m_dialouge.show_active();
                }
            }

            @Override
            public void process(){
                ImGui.setNextWindowPos(0,(Gdx.graphics.getHeight()/4) * 3);
                ImGui.setNextWindowSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/4);
                // todo make below the npc name
                ImGui.begin("dialouge");
                ImGui.text(m_speech);
                if(ImGui.button("prev")){
                    this.prev();
                }
                if(ImGui.button("next")){
                    if(m_on_trigger!=null){
                        m_on_trigger.trigger();
                    }
                    this.next();
                }
                if(ImGui.button("close")){
                    if(m_on_trigger!=null){
                        m_on_trigger.trigger();
                    }
                    this.m_dialouge.close();
                }
                ImGui.end();
            }
        }

        public static class Choice extends DialougePart {



            String m_speech;
            DialougePart m_prev;
            HashMap<String, Utils.Tuple<DialougeTrigger, String>> m_choices;

            public Choice(){
            }
            public Choice(String name, String speech, HashMap<String, Utils.Tuple<DialougeTrigger, String>> choices){
                m_name = name;
                m_speech = speech;
                m_choices = choices;
            }


            @Override
            public void trigger(){
                m_dialouge.set_active(this);
                Debug.dbg(m_speech);
                if(m_on_trigger!=null)
                    m_on_trigger.trigger();
            }

            @Override
            public void process(){
                ImGui.setNextWindowPos(0,(Gdx.graphics.getHeight()/4) * 3);
                ImGui.setNextWindowSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/4);
                ImGui.begin("dialouge");
                ImGui.text(m_speech);
                for(Map.Entry<String, Utils.Tuple<DialougeTrigger, String>> choice : m_choices.entrySet()){
                    if(ImGui.button(choice.getKey())){
                        if(choice.getValue().m_first !=null){
                            choice.getValue().m_first.trigger();
                        }
                        DialougePart next_part = DialougePart.get(choice.getValue().m_second);
                        next_part.set_dialouge(m_dialouge);
                        m_dialouge.set_active(next_part);
                        m_dialouge.show_active();
                    }
                }
                if(ImGui.button("close")){
                    if(m_on_trigger!=null){
                        m_on_trigger.trigger();
                    }
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
            show_active();
        }
    }

    public boolean is_in_conversation(){
        return m_is_in_conversation;
    }
    public void close(){
        m_is_in_conversation=false;
    }

    public void show_active(){
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
