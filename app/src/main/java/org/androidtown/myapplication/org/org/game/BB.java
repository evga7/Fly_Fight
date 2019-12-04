package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class BB extends Blackhole {

    public BB( int x, int y)  {
        super (AppManager.getInstance( ).getBitmap(R.drawable. blackholebomb));
        this .initSpriteData(345,350 , 5, 8);
        this .setPosition(x, y);
    }
    @Override
    public void Update( long GameTime) {
        super .Update(GameTime);
        m_y-=20;
        m_BoundBox .set( m_x, m_y, m_x + 345, m_y + 350);
        if (m_y <0)  state = STATE_OUT;
    }
}
