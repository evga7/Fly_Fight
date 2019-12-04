package org.androidtown.myapplication.org.org.game;


import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Missile_Enemy extends Missile {
    public Missile_Enemy( int x, int y)  {
        super (AppManager.getInstance( ).getBitmap(R.drawable. missile_2));
        this .setPosition(x, y);
    }
    public void Update( )  {
        m_y += 40;
        m_BoundBox .set( m_x, m_y, m_x + 43, m_y + 43);
        if (m_y > 1900)  state = STATE_OUT;
    }

}
