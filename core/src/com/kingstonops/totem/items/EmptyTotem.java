package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Debug;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.player.PlayerComponent;

public class EmptyTotem extends Item{
    public static class SpeedTotem extends EmptyTotem {
        public SpeedTotem(){
            m_name="speed_totem";
            m_texture="speed_totem.png";
        }
        @Override
        public void on_use(Totem game, Vector3 pos){

            Debug.dgb("speeeeeed!!!");

            ImmutableArray<Entity> entities = game.engine().getEntitiesFor(Family.all(PlayerComponent.class).get());
            Entity p = entities.first();

            MovementComponent m = p.getComponent(MovementComponent.class);
            m.m_speed*=2;
        }
    }
}
