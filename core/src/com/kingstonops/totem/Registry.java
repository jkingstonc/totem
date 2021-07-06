package com.kingstonops.totem;

import java.util.HashMap;

public class Registry<T> {

/*    public interface Initialiser<T>{
        T init();
    }

    private HashMap<String, Initialiser<T>> m_registered = new HashMap<>();*/

    private HashMap<String, T> m_registered = new HashMap<>();

    public void register(String id, T initialiser){
        m_registered.put(id, initialiser);
    }

    public T instantiate(String id){
        return m_registered.get(id);
    }
}
