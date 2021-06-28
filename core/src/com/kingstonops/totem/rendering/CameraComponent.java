package com.kingstonops.totem.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraComponent {
    public OrthographicCamera cam;
    public boolean follow_target;
    public Vector3 target;
}
