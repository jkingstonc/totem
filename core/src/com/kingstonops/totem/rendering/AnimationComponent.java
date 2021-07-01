package com.kingstonops.totem.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AnimationComponent implements Component {
    HashMap<String, Animation<TextureRegion>> anims;
}
