package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Missile_Player extends Missile {
    public static int m_getMspeed=3;
    public Missile_Player (int x, int y)  {
        super (AppManager.getInstance( ).getBitmap(R.drawable.missile_1));
        this.setPosition(x, y);
    }
    public int getspeed() {
        return m_getMspeed;
    }  //미사일 속도 리턴
    public void addspeed(){
        m_getMspeed+=1;
    } // 미사일 속도 증가
    public void minusspeed(){
        m_getMspeed-=1;
    } // 미사일 속도 증가
    public void Update(long gameTime)  {
        m_y-=m_getMspeed;

        m_BoundBox.left = m_x;
        m_BoundBox.top = m_y;
        m_BoundBox.right = m_x + 43;
        m_BoundBox.bottom = m_y + 43;


        if (m_y < 0) {
            state = STATE_OUT;

        }


    }
}
