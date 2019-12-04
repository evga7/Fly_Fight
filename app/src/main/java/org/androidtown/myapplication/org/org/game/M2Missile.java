package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class M2Missile extends Blackhole {

    public M2Missile( int x, int y)  {

        super (AppManager.getInstance( ).getBitmap(R.drawable.m2));

        this .initSpriteData(70,200 , 20, 36);
        this .setPosition(x, y);
    }

    @Override
    public void Update( long GameTime) {
        super .Update(GameTime);
        m_y-=10;
        m_BoundBox .set( m_x, m_y, m_x + 43, m_y + 43);
        if (m_y <500)  state = STATE_OUT;
    }

}
