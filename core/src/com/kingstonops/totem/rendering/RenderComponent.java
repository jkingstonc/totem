package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {
    public boolean m_show = true;
    public TextureRegion texture;
}
