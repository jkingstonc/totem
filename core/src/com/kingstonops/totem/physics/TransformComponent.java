package com.kingstonops.totem.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.rendering.RenderSystem;

import java.util.Comparator;

public class TransformComponent implements Component {

    public static class ZComparator implements Comparator<Entity> {
        private ComponentMapper<TransformComponent> m_pos_mapper;
        public ZComparator(){
            m_pos_mapper = ComponentMapper.getFor(TransformComponent.class);
        }
        @Override
        public int compare(Entity o1, Entity o2) {
            TransformComponent p1 = m_pos_mapper.get(o1);
            TransformComponent p2 = m_pos_mapper.get(o2);
            int result = 0;
            if(p1.position.z > p2.position.z)
                result = 1;
            else if(p1.position.z < p2.position.z)
                result = -1;
            return result;
        }
    }
    public Vector3 position = new Vector3();
    public Vector3 scale = new Vector3(1,1,1);
    public float r = 0.0f;
}
