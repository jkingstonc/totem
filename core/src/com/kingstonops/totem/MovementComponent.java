package com.kingstonops.totem;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MovementComponent implements Component {
    public Vector3 velocity = new Vector3();
    public Vector3 acceleration = new Vector3();
}
