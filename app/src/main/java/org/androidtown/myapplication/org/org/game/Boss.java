package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;

public class Boss extends Enemy {
    public Boss() {
        super(AppManager.getInstance().getBitmap(R.drawable.boss));
        this.BossinitSpriteData(500,500);
        speed = 8.0f;
        movetype = Enemy.MOVE_PATTERN_5;
    }
    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x,m_y,m_x+400,m_y+400);
    }
}
