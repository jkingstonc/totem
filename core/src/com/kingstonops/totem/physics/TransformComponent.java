package com.kingstonops.totem.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.rendering.RenderSystem;

public class TransformComponent implements Component {
    public Vector3 position = new Vector3();
    public Vector3 scale = new Vector3(RenderSystem.unit_to_pixel(1), RenderSystem.unit_to_pixel(1), RenderSystem.unit_to_pixel(1));
    public float r = 0.0f;
}
