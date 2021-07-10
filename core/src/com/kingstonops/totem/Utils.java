package com.kingstonops.totem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.physics.TransformComponent;

import java.util.ArrayList;


public class Utils {
    public static float lerp(float from, float to, float amount){
        return from + amount * (to-from);
    }

    public static class Tuple<T, E>{
        public T m_first;
        public E m_second;

        public Tuple(){}
        public Tuple(T f, E s){
            m_first = f;
            m_second = s;
        }
    }

    public static Entity raycast_entity(Totem game, Vector3 world_pos){

        ArrayList<Entity> shortlist = new ArrayList<>();

        for(Entity e : game.engine().getEntities()){
            TransformComponent t = e.getComponent(TransformComponent.class);
            if(t!=null){
                if(
                    t.position.x + t.scale.x/2 > world_pos.x
                    && t.position.x - t.scale.x/2 < world_pos.x
                    && t.position.y + t.scale.y/2 > world_pos.y
                    && t.position.y - t.scale.y/2 < world_pos.y
                ){
                    shortlist.add(e);
                }

            }
        }


        if(shortlist.size()>0){
            Debug.dbg("shortlist = "+shortlist.size());
            shortlist.sort(new TransformComponent.ZComparator());
            return shortlist.get(shortlist.size()-1);
        }

        return null;
    }
}
