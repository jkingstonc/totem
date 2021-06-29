package com.kingstonops.totem.world.guys;

import com.badlogic.ashley.core.Component;

public class AIComponent implements Component {
    public static class AIProvider{

    }
    public AIComponent(){}
    public AIComponent(AIProvider ai_provider){
        m_ai_provier = ai_provider;
    }
    AIProvider m_ai_provier;
}
