package com.kingstonops.totem.world;

import com.kingstonops.totem.Totem;

import java.util.HashMap;

public class Object {

    public static class ObjectDescriptor{
        public int m_id;
        public String m_name;
        public ObjectDescriptor m_setup;

        public static interface ObjectDescriptorSetup{
            public void setup();
        }
    }

    public static void register_all(Totem game){

    }
    /*
    *
    * e.g. get("bed")
    * */

    public static HashMap<String, ObjectDescriptor> object_registry = new HashMap<String, ObjectDescriptor>();

    public static void register(ObjectDescriptor descriptor){
        descriptor.m_id = object_registry.size();
        object_registry.put(descriptor.m_name, descriptor);
    }

    public static ObjectDescriptor get(String name){
        return object_registry.get(name);
    }
}
