package com.kingstonops.totem;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputSystem extends EntitySystem implements InputProcessor {
    private ImmutableArray<Entity> m_entities;


    public int key_up = -1;
    public int key_held = -1;

    @Override
    public void addedToEngine(Engine engine) {
    }

    @Override
    public void update(float dt){
        key_up=-1;
        key_held=-1;
    }

    @Override
    public boolean keyDown(int keycode) {
        key_held=keycode;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        key_up = keycode;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
