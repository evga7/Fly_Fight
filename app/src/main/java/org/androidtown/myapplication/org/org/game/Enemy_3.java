package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Enemy_3 extends Enemy {

    public static int m_HP=10;

    public Enemy_3( )  {
        super (AppManager.getInstance( ).getBitmap(R.drawable. enemy3));
        this .initSpriteData(163,312 , 15, 6);
        hp = m_HP;
        speed = 4.0f;
        movetype = Enemy.MOVE_PATTERN_2;
    }
    @Override
    public void Update( long GameTime)  {
        super .Update(GameTime);
        m_BoundBox .set( m_x, m_y, m_x + 130, m_y + 200);
    }
}