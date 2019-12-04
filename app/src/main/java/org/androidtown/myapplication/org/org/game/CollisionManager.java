package org.androidtown.myapplication.org.org.game;

import android.graphics.Rect;

public class CollisionManager {
    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2) {
        if (_rt1.right > _rt2.left &&
                _rt1.left < _rt2.right &&
                _rt1.top < _rt2.bottom
                )
            return true;
        return false;
    }
    public static  boolean CheckBoxToBoxWhenBoss(Rect _rt1, Rect _rt2) {
        if (_rt1.left<_rt2.right&&_rt1.right >_rt2.left&&_rt1.top<_rt2.bottom)
        {
            return true;
        }

        return false;
    }
}