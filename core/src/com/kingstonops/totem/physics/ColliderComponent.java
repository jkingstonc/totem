package com.kingstonops.totem.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kingstonops.totem.rendering.RenderSystem;

import java.util.ArrayList;

public class ColliderComponent implements Component {


    public ArrayList<Entity> m_colliding_with = new ArrayList<>();
    // for now we are not using Box2D
    public Vector2 m_bounds;
    public boolean m_solid = false;
    public boolean m_dynamic = false;
}
