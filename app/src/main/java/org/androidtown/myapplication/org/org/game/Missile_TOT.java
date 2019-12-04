package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Missile_TOT extends Missile {


        public Missile_TOT (int x, int y)  {
            super (AppManager.getInstance( ).getBitmap(R.drawable.raptor));
            this.setPosition(x, y);
        }

        public void Update(long gameTime)  {
            m_y-=30;

            m_BoundBox.left = m_x;
            m_BoundBox.top = m_y;
            m_BoundBox.right = m_x + 43;
            m_BoundBox.bottom = m_y + 43;

            if (m_y < -600)
            {
                state = STATE_OUT;
                trace3 =true;
            }

        }

}
