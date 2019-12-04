package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.androidtown.myapplication.org.GraphicObject;

public class Missile extends GraphicObject{
    Rect m_BoundBox = new Rect( );
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    public static boolean trace3=false;


    public Missile(Bitmap bitmap)  { super (bitmap); }
    public void Update(){

        if(m_y<50) state= STATE_OUT;
    }
}
