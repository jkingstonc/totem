package com.kingstonops.totem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    private Totem m_game;
    private Texture m_tex;

    private Entity m_player;

    public GameScreen(Totem game){
        m_game = game;
        m_tex = new Texture("badlogic.jpg");


        m_player = new Entity();
        m_player.add(new PositionComponent());
        m_player.add(new VelocityComponent());
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float dt){
        ScreenUtils.clear(1, 0, 0, 1);
        m_game.batch().begin();

        m_game.batch().draw(m_tex, 0, 0);
        m_game.batch().end();
    }


}
