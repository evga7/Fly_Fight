package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.org.GameView;

public class hiddenbomb extends Blackhole {


    public hiddenbomb( int x, int y)  {

        super (AppManager.getInstance( ).getBitmap(R.drawable.haha));

        this .initSpriteData(500,1000 , 15, 36);
        this .setPosition(x, y);
    }
    @Override
    public void Update( long GameTime) {
        super .Update(GameTime);
        m_y-=7;
        m_BoundBox .set( m_x, m_y, m_x + 500, m_y + 1000);
        if (m_y <0)  state = STATE_OUT;
    }
}
