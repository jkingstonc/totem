package com.kingstonops.totem;

public class Utils {
    public static float lerp(float from, float to, float amount){
        return from + amount * (to-from);
    }

    public static class Tuple<T, E>{
        public T m_first;
        public E m_second;

        public Tuple(){}
        public Tuple(T f, E s){
            m_first = f;
            m_second = s;
        }
    }
}
