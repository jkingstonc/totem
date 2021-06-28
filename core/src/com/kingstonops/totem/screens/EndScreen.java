package com.kingstonops.totem.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kingstonops.totem.Totem;

public class EndScreen extends ScreenAdapter {

    private Totem m_game;

    public EndScreen(Totem game){
        m_game = game;
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float dt){
        ScreenUtils.clear(1, 0, 0, 1);
        m_game.batch().begin();
        m_game.font().draw(m_game.batch(), "end screen!", Gdx.graphics.getWidth()*.5f, Gdx.graphics.getWidth()*.5f);
        m_game.batch().end();
    }


}
