package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.org.SpriteAnimation;

import java.util.Random;

public class Enemy extends SpriteAnimation {

    public static final int MOVE_PATTERN_1 = 0;
    public static final int MOVE_PATTERN_2 = 1;
    public static final int MOVE_PATTERN_3 = 2;
    public static final int MOVE_PATTERN_4 = 3;
    public static final int MOVE_PATTERN_5 = 4;
    public static final int MOVE_PATTERN_6 = 5;
    Random randEnem = new Random();
    protected int movetype;
    public int hp;
    public float speed;
    Rect m_BoundBox = new Rect();
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    long LastShoot = System.currentTimeMillis( );
    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }

    void Move() {
        int map = randEnem.nextInt(4);
        int map2 = randEnem.nextInt(12);
        if (movetype == MOVE_PATTERN_1) {
            if (m_y <= 600) m_y += speed;
            else m_y += speed * 4;
        } else if (movetype == MOVE_PATTERN_2) {
            if (m_y <= 600) m_y += speed;
            else {
                m_x += speed;
                m_y += speed;
            }
        } else if (movetype == MOVE_PATTERN_3) {
            if (m_y <= 600) m_y += speed;
            else {
                m_x -= speed;
                m_y += speed;
            }
        }
        else if (movetype == MOVE_PATTERN_4) {
            if (m_x <= 600 && mapPosion == false) {
                if (m_x == 600) mapPosion = true;

                else if (m_x != 600) m_x += speed;
            } else if (mapPosion == true) {
                //GraphicObject에있음
                m_x -= speed;
                if (m_x == 0) mapPosion = false;
            }
        }
        else if (movetype == MOVE_PATTERN_5) {
            if (m_x <= 600 && mapPosion == false) {
                if (m_x == 600) mapPosion = true;
                else if (m_x != 600) {
                    m_x += speed;
                }
            } else if (mapPosion == true) {
                m_x -= speed;
                if (m_x == 0) mapPosion = false;
            }
            if (map == 0 && (m_y >= 0 && m_y <= 700)) m_y += speed;
            else if (map == 1 && (m_y <= 700 && m_y > 30)) m_y -= speed;
            else if (map == 2 && (m_y >= 0 && m_y <= 700)) m_y += speed * 2;
            else if (map == 3 && (m_y <= 700 && m_y > 30)) m_y -= speed * 3;

        } else if (movetype == MOVE_PATTERN_6) {
            if (map2 == 0) {
                m_x = 100;
                m_y = 100;
            } else if (map2 == 1) {
                m_x = 300;
                m_y = 100;
            } else if (map2 == 2) {
                m_x = 600;
                m_y = 100;
            } else if (map2 == 3) {
                m_x = 100;
                m_y = 250;
            } else if (map2 == 4) {
                m_x = 300;
                m_y = 250;
            } else if (map2 == 5) {
                m_x = 600;
                m_y = 250;
            } else if (map2 == 6) {
                m_x = 100;
                m_y = 500;
            } else if (map2 == 7) {
                m_x = 300;
                m_y = 500;
            } else if (map2 == 8) {
                m_x = 600;
                m_y = 500;
            } else if (map2 == 9) {
                m_x = 100;
                m_y = 700;
            } else if (map2 == 10) {
                m_x = 300;
                m_y = 700;
            }
        } else if (map2 == 11) {
            m_x = 600;
            m_y = 700;
        }
        if (m_y > 900||m_x >500||m_x<0) state = STATE_OUT;
    }
    public void Attack() {
        if (System.currentTimeMillis() - LastShoot >= 1000){
            LastShoot = System.currentTimeMillis();
            AppManager.getInstance().m_gameState.m_enemmslist.add(
                    new Missile_Enemy(m_x+10, m_y+104));
        }
    }
    public  void BossAttack(){//보스공격
        if(System.currentTimeMillis()-LastShoot >=100){

            LastShoot = System.currentTimeMillis();

            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x+100,m_y+250));

            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x+50,m_y+170));

            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x+250,m_y+300));
            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x+180,m_y+50));
        }
    }
    public void BossUpdate(long GameTime){
        BossAttack();
        Move();
    }
    @Override
    public void Update (long gameTime){
        //super.Update(gameTime);
        Attack();
        Move();
    }

    public void minusHp(){hp-=AppManager.getInstance().m_player.getDamage();}
}

