package com.kingstonops.totem.input;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class InputSystem extends EntitySystem implements InputProcessor {
    private ImmutableArray<Entity> m_entities;


    // todo key_down should be an array of keys
    public Vector2 mouse_pos = new Vector2();
    public int mouse_down = -1;
    public int mouse_up = -1;
    public int mouse_held = -1;

    public ArrayList<Integer> key_down = new ArrayList<>();
    public ArrayList<Integer> key_up = new ArrayList<>();
    public ArrayList<Integer> key_held = new ArrayList<>();

    @Override
    public void addedToEngine(Engine engine) {
    }

    @Override
    public void update(float dt){
        mouse_up=-1;
        mouse_down=-1;
        key_up.clear();
        key_down.clear();
    }

    @Override
    public boolean keyDown(int keycode) {
        key_down.add(keycode);
        key_held.add(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(key_held.contains(keycode)){
            key_held.remove(new Integer(keycode));
        }
        key_up.add(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouse_down = button;
        mouse_pos.x = screenX;
        mouse_pos.y = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouse_up = button;
        mouse_pos.x = screenX;
        mouse_pos.y = screenY;
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
