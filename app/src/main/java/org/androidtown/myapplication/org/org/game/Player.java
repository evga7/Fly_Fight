package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;


import org.androidtown.myapplication.org.SpriteAnimation;

public class Player extends SpriteAnimation {
    public static int m_Life = 3;     //추가
    public static int m_score=0;        //추가
    public static int m_damage=10;      //추가
    Rect m_BoundBox = new Rect( );
    public Player(Bitmap bitmap) {
        super(bitmap);
        this.initSpriteData(220,260,1,1);
        this.setPosition(300,1000);
    }
    public int getLife( )  {
        return m_Life;
    }
    public void addLife( )  {
        m_Life++;
    }
    public void destroyPlayer( )  {
        m_Life--;
    }

    public int getscore()  //기본 스코어
    {    return m_score; }
    public void setscore(int _score)
    {
        m_score= _score;
    }
    public void addscore(){ m_score+=1;} //스코어 증가
    public void minusscore(){m_score-=1;} // 스코어 감소
    public int getDamage(){return m_damage;} //기본 데미지
    public void addDamage(){m_damage+=1;} //데미지 증가
    public void minusDamage(){m_damage-=1;} //데미지 감소

    @Override
    public void Update (long gameTime)  {
        super .Update(gameTime);
//        if (bMove)  {
//            this .m_x += _dirX;
//            this .m_y += _dirY;
//        }
        m_BoundBox .set( m_x, m_y, m_x + 125, m_y + 300 );
    }
}
