package com.kingstonops.totem.world.guys;

import com.badlogic.gdx.utils.Array;
import com.kingstonops.totem.Debug;

import java.util.HashMap;
import java.util.Map;

public class Speech {
    public static abstract class SpeechPart{

        public abstract void trigger();

        public static class Single extends SpeechPart{
            String m_speech;

            @Override
            public void trigger(){
                Debug.dgb(m_speech);
            }
        }

        public static class Input extends SpeechPart{
            String m_speech;
            String m_input;


            @Override
            public void trigger(){
                Debug.dgb(m_speech);
                Debug.dgb("... input pls");
            }
        }

        public static class ChoiceSpeech extends SpeechPart{
            HashMap<String, SpeechPart> m_speeches = new HashMap<>();


            @Override
            public void trigger(){
                for(Map.Entry<String, SpeechPart> entry : m_speeches.entrySet()){
                    Debug.dgb(entry.getKey());
                }
            }
        }


    }
    // depending on the dialouge path, this will be updated
    SpeechPart m_active_part;
    // this is the root speech part
    SpeechPart m_root_part;
}
