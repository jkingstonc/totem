package com.kingstonops.totem.items;

import com.kingstonops.totem.Registry;

import java.util.ArrayList;
import java.util.HashMap;


/*
*
*
* E.g. Rusty Gear = Totem of Rust + Old Gear
*
* */
public class Recipe {
    public ArrayList<String> m_requirements;
    public ArrayList<String> m_results;

    public static Registry<Recipe> registry = new Registry<>();

    public String m_name;


    public Recipe(String name, ArrayList<String> requirements, ArrayList<String> results){
        m_name = name;
        m_requirements = requirements;
        m_results = results;
    }
}
