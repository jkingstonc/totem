package com.kingstonops.totem;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class PlayerComponent implements Component {
    Vector3 last_selected_tile = new Vector3();
}
