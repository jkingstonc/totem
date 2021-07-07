package com.kingstonops.totem;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;

import java.util.ArrayList;
import java.util.Comparator;

public class IDComponent implements Component {

    private static class TransformComparator implements Comparator<Entity> {
        private ComponentMapper<TransformComponent> m_pos_mapper;
        private Vector3 m_target;
        public TransformComparator(Vector3 target){
            m_target = target;
            m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
        }
        @Override
        public int compare(Entity o1, Entity o2) {
            TransformComponent p1 = m_pos_mapper.get(o1);
            TransformComponent p2 = m_pos_mapper.get(o2);

            
            double p1_dist = Math.sqrt(Math.pow(p1.position.x - m_target.x, 2) + Math.pow(p1.position.y - m_target.y, 2));
            double p2_dist = Math.sqrt(Math.pow(p2.position.x - m_target.x, 2) + Math.pow(p2.position.y - m_target.y, 2));

            int result = 0;
            if(p1_dist > p2_dist)
                result = 1;
            else if(p1_dist < p2_dist)
                result = -1;
            return result;
        }
    }
    private String m_id;

    public IDComponent(String id){
        m_id = id;
    }

    public String id(){
        return m_id;
    }

    public static Entity find(Totem game, String id){
        ImmutableArray<Entity> es = game.engine().getEntitiesFor(Family.all(IDComponent.class).get());
        for(Entity e : es){
            if(e.getComponent(IDComponent.class).m_id.equals(id)){
                return e;
            }
        }
        return null;
    }

    public static Entity find_nearest(Totem game, String id, Vector3 target){
        ImmutableArray<Entity> es = game.engine().getEntitiesFor(Family.all(IDComponent.class).get());
        ArrayList<Entity> hay_entities = new ArrayList<>();
        for(Entity entity : es){
            if(entity.getComponent(IDComponent.class).id().equals(id)){
                hay_entities.add(entity);
            }
        }

        hay_entities.sort(new TransformComparator(target));

        if(hay_entities.size()>0) {
            return hay_entities.get(0);
        }
        return null;
    }
}
