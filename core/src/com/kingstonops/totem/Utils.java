package com.kingstonops.totem;

public class Utils {
    public static float lerp(float from, float to, float amount){
        return from + amount * (to-from);
    }


}
