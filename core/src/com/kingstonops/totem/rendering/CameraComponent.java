package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraComponent implements Component {
    public static CameraComponent getInstance(Engine engine){
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.one(CameraComponent.class).get());
        Entity cam_e = entities.get(0);
        return cam_e.getComponent(CameraComponent.class);
    }

    public void resize(int w, int h){
        cam.viewportWidth = w;
        cam.viewportHeight = h;
    }

    public CameraComponent(){
        float cam_w = Gdx.graphics.getWidth();
        float cam_h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(cam_w, cam_h);
        resize(1920,1080);
        cam.position.set(cam_w/2f, cam_h/2f, 0);
    }
    public OrthographicCamera cam;
    public boolean follow_target;
    public Vector3 target;
}
