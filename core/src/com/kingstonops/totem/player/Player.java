package com.kingstonops.totem.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.IDComponent;
import com.kingstonops.totem.Prefab;
import com.kingstonops.totem.Totem;
import com.kingstonops.totem.items.InventoryComponent;
import com.kingstonops.totem.items.Item;
import com.kingstonops.totem.physics.ColliderComponent;
import com.kingstonops.totem.physics.MovementComponent;
import com.kingstonops.totem.physics.TransformComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.WorldSystem;

public class Player extends Prefab {

    @Override
    public Entity spawn(Totem game) {
        Entity e;
        e = WorldSystem.entity(game);

        e.add(new IDComponent("player"));


        ColliderComponent c = new ColliderComponent();
        c.m_solid = true;
        c.m_dynamic = true;
        c.m_bounds = new Vector2(.5f, .25f);
        e.add(c);


        InventoryComponent i = new InventoryComponent();
        i.put(Item.registry.get("animal_log").spawn(game));
        i.put(Item.registry.get("water_tank").spawn(game));
        i.put(Item.registry.get("water_bucket").spawn(game));
        i.put(Item.registry.get("lead").spawn(game));
        i.put(Item.registry.get("spawn_cow").spawn(game));
        i.put(Item.registry.get("spawn_hay").spawn(game));
        e.add(i);

        TransformComponent t = new TransformComponent();
        t.position = new Vector3(
                0,
                0,
                RenderSystem.PLAYER_LAYER
        );
        t.scale = new Vector3(1, 2, 1);
        e.add(t);
        MovementComponent v = new MovementComponent();
        e.add(v);
        e.add(new PlayerComponent());
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture("guy.png"));
        e.add(r);


        // add the item the player is holding

        Entity holding_item = WorldSystem.entity(game);
        TransformComponent i_t = new TransformComponent();
        i_t.position = new Vector3(
                0,
                0,
                RenderSystem.PLAYER_LAYER+1
        );
        i_t.scale = new Vector3(
                .5f,
                .5f,
                0
        );
        holding_item.add(i_t);
        RenderComponent i_r = new RenderComponent();
        i_r.texture=new TextureRegion(RenderSystem.get(i.m_items.get(0).items().get(0).m_texture));
        holding_item.add(i_r);
        e.getComponent(PlayerComponent.class).m_holding_item = holding_item;
        return e;
    }

}
