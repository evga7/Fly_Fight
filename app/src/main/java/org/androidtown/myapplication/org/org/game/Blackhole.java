package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.androidtown.myapplication.org.SpriteAnimation;

public class Blackhole extends SpriteAnimation {

    Rect m_BoundBox = new Rect( );

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;


    public Blackhole(Bitmap bitmap) {
        super(bitmap);
        //this.initSpriteData(163,312,10,6);
        //this.setPosition(200,500);
    }

    @Override
    public void Update (long gameTime)  {
        super .Update(gameTime);
        //m_BoundBox .set( m_x, m_y, m_x + 125, m_y + 300 );
    }
}
