package com.kingstonops.totem;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {
    public Vector3 position = new Vector3();
    public float r = 0.0f;
}
