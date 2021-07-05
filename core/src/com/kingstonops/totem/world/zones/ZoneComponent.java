package com.kingstonops.totem.world.zones;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.kingstonops.totem.Totem;

import java.util.ArrayList;
import java.util.HashMap;

public class ZoneComponent implements Component {


    public static void from_file(String path){
    }


    public static void register_all(Totem game){
        Builtin.setup_builtin_zones(game);
    }

    public static class ZoneDescriptor{

        public static interface ZoneDescriptorSetup{
            void setup(ZoneComponent zone);
        }

        public ZoneDescriptor(String name, ZoneDescriptorSetup setup){
            m_name=name;
            m_setup=setup;
        }

        private int m_id;
        private String m_name;
        private boolean m_persistant; // is this zone persistant?
        private ZoneDescriptorSetup m_setup;

        public void set_id(int id){
            m_id = id;
        }
        public int id(){
            return m_id;
        }
        public String name(){
            return m_name;
        }
        public ZoneDescriptorSetup setup(){
            return m_setup;
        }
        public boolean persistant(){return m_persistant;}
    }

    private static HashMap<String, ZoneDescriptor> zone_descriptors = new HashMap<>();

    public static void register(ZoneDescriptor descriptor){
        descriptor.set_id(zone_descriptors.size());
        zone_descriptors.put(descriptor.name(), descriptor);
    }

    public static ZoneDescriptor get(String name){
        return zone_descriptors.get(name);
    }

    private ZoneDescriptor m_descriptor;
    private ArrayList<Entity> m_entities = new ArrayList<>();

    public String m_name = "default";

    public ZoneComponent(ZoneDescriptor descriptor){
        m_descriptor = descriptor;
    }

    public ZoneComponent(ArrayList<Entity> entities){
        m_entities = entities;
    }

    public ZoneDescriptor descriptor(){return m_descriptor;}
    public ArrayList<Entity> entities(){
        return m_entities;
    }
}
