package com.kingstonops.totem.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
*
*
* E.g. Rusty Gear = Totem of Rust + Old Gear
*
* */
public class RecipeDescriptor {
    public ArrayList<String> requirements;
    public ArrayList<String> result;

    public static HashMap<String, RecipeDescriptor> recipes =  new HashMap<>();

    public static void register_all(){
        register(new RecipeDescriptor("rusty_gear"));
    }

    public static void register(RecipeDescriptor recipe){
        recipe.m_id=recipes.size();
        recipes.put(recipe.m_name, recipe);
    }

    public int m_id = -1;
    public String m_name;


    public RecipeDescriptor(String name){
        m_name = name;
    }
}
