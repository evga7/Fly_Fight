package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class explosion extends Blackhole {

    public explosion( int x, int y)  {
        super (AppManager.getInstance( ).getBitmap(R.drawable.tx));
        //this .initSpriteData((345/2)-1,350 , 10, 12);//빨간색
        this .initSpriteData(337,350 , 10, 12);//파란색
        this .setPosition(x, y);
    }
    @Override
    public void Update( long GameTime) {
        super .Update(GameTime);
        m_y-=10;
        m_BoundBox .set( m_x, m_y, m_x + 337, m_y + 350);
        if (m_y <0)  state = STATE_OUT;

    }

}
