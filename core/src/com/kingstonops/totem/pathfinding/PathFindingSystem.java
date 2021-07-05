package com.kingstonops.totem.pathfinding;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Totem;

import java.util.ArrayList;

public class PathFindingSystem extends EntitySystem {

    // https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2
    private static class PathNode{
        public Vector3 m_pos;
    }



    private ImmutableArray<Entity> m_entities;

    //private ComponentMapper<InventoryComponent> m_inventory_mapper = ComponentMapper.getFor(InventoryComponent.class);

    Totem m_game;

    public PathFindingSystem(Totem game){
        m_game = game;
    }



    private void create_map(){
        
    }

    public ArrayList<Vector3> find_path(Vector3 start, Vector3 end){
        return null;
    }

    @Override
    public void addedToEngine(Engine engine) {
        //m_entities = engine.getEntitiesFor(Family.all(InventoryComponent.class).get());
    }


    @Override
    public void update(float dt){
/*        for(int i =0;i<m_entities.size();i++){
            Entity e = m_entities.get(i);
            InventoryComponent inv = m_inventory_mapper.get(e);
        }*/
    }
}
