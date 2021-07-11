package com.kingstonops.totem.items;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.Prefab;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.world.WorldSystem;
import com.kingstonops.totem.world.zones.ZoneComponent;

public class WaterTank extends Item{
    public WaterTank(){
        super("water_tank_empty", "water_tank_empty.png", Rarity.COMMON);
    }

    @Override
    public void on_use(Totem game, Vector3 pos) {
        super.on_use(game, pos);
        ZoneComponent z = game.engine().getSystem(WorldSystem.class).m_active_zone.getComponent(ZoneComponent.class);
        Entity e = Prefab.registry.get("obj_water_tank_empty").spawn(game);
        e.getComponent(TransformComponent.class).position.set(pos);
        z.entities().add(e);
    }
}
