package com.kingstonops.totem;

import com.badlogic.ashley.core.Component;

public class IDComponent implements Component {
    private String m_id;

    public IDComponent(String id){
        m_id = id;
    }

    public String id(){
        return m_id;
    }
}
