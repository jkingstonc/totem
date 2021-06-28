package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraComponent implements Component {
    public CameraComponent(){
        float cam_w = Gdx.graphics.getWidth();
        float cam_h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(cam_w, cam_h);
        cam.position.set(cam_w/2f, cam_h/2f, 0);
    }
    public OrthographicCamera cam;
    public boolean follow_target;
    public Vector3 target;
}
