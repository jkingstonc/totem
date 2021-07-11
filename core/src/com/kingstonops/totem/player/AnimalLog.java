package com.kingstonops.totem.player;

import com.kingstonops.totem.Registry;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalLog {

    public static class AnimalStats{


        public static Registry<AnimalStats> registry = new Registry<>();

        public static void register_all(){
            registry.register("animal_cow", new AnimalStats("animal_cow", Rarity.COMMON, new ArrayList<String>(){{add("tile_grass");}}));
            registry.register("animal_dog", new AnimalStats("animal_dog", Rarity.COMMON, new ArrayList<>()));
            registry.register("animal_cat", new AnimalStats("animal_cat", Rarity.COMMON, new ArrayList<>()));
            registry.register("animal_rabbit", new AnimalStats("animal_rabbit", Rarity.COMMON, new ArrayList<>()));

            registry.register("animal_lynx", new AnimalStats("animal_lynx", Rarity.RARE, new ArrayList<>()));

            registry.register("animal_golden_badger", new AnimalStats("animal_golden_badger", Rarity.LEDGENDARY, new ArrayList<>()));

        }

        public enum Rarity{
            COMMON,
            RARE,
            LEDGENDARY
        }

        public String m_name;
        public Rarity m_rarity;
        // what entities this animal can spawn on
        public ArrayList<String> m_spawns_on = new ArrayList<>();

        public AnimalStats(String name, Rarity rarity, ArrayList<String> spawns_on){
            m_name = name;
            m_rarity = rarity;
            m_spawns_on = spawns_on;
        }
    }

}
