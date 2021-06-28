package com.kingstonops.totem.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kingstonops.totem.*;
import com.kingstonops.totem.rendering.CameraComponent;
import com.kingstonops.totem.rendering.RenderComponent;
import com.kingstonops.totem.rendering.RenderSystem;
import com.kingstonops.totem.world.World;

public class GameScreen extends ScreenAdapter {

    private Totem m_game;
    private Texture m_tex;
    private boolean m_initialised = false;

    private Entity m_player;

    public GameScreen(Totem game){
        m_game = game;
        m_tex = new Texture("badlogic.jpg");
    }

    private void init(){
        System.out.println("init");
        m_player = m_game.engine().createEntity();
        m_game.engine().addEntity(m_player);
        TransformComponent t = new TransformComponent();
        t.position = new Vector3(
            RenderSystem.unit_to_pixel(0),
            RenderSystem.unit_to_pixel(0),
            1
        );
        m_player.add(t);
        MovementComponent v = new MovementComponent();
        m_player.add(v);
        m_player.add(new PlayerComponent());
        RenderComponent r = new RenderComponent();
        r.texture = new TextureRegion(new Texture("badlogic.jpg"));
        m_player.add(r);

        // create a world
        new World(m_game);

        // set the camera target

        ImmutableArray<Entity> entities = m_game.engine().getEntitiesFor(Family.one(CameraComponent.class).get());
        Entity cam_e = entities.get(0);
        CameraComponent cam = cam_e.getComponent(CameraComponent.class);
        System.out.println("camera = "+cam);
        cam.target = t.position;
        cam.follow_target=true;

        m_initialised = true;
    }


    private void update(float dt){
        m_game.engine().update(dt);
        Gdx.graphics.setTitle("fps "+1/dt);
    }

    @Override
    public void show(){

    }

    @Override
    public void dispose(){
        m_tex.dispose();
    }

    @Override
    public void render(float dt){
        if(m_initialised){
            update(dt);
        }else{
            init();
        }
    }


}
