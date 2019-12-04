package org.androidtown.myapplication.org.org.game;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Boss2 extends Enemy {
    public Boss2() {
           super(AppManager.getInstance().getBitmap(R.drawable.boss));
        this.BossinitSpriteData(500,500);
    speed = 8.0f;
    movetype = Enemy.MOVE_PATTERN_4;
}
    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x,m_y,m_x+400,m_y+400);
    }
}

