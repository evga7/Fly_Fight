package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.org.GameView;
import org.androidtown.myapplication.org.GraphicObject;
import org.androidtown.myapplication.org.IState;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Delayed;

public class GameState implements IState {

    Random randEnem = new Random();
    public Missile_Player m_missileplayer;
    private BackGround m_background;
    //public Player m_player;
    private Enemy m_enemy;
    private int Score;
    int bomb_flag = 0;
    private boolean Trace = false;
    public static boolean Trace2 = true;
    //private hiddenbomb hid;
    private GraphicObject m_keypad;
    private int cnt = 0;
    private Bitmap bombbnt;
    int sel_c_int;
    int bom_cnt = 3;
    private Boss m_boss;
    private Boss2 m_boss2;
    private Boss3 m_boss3;
    private  int BarHp = 960;
    public int Bosscnt =0;

    ArrayList<Missile_Player> m_pmslist = new ArrayList<Missile_Player>();
    ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>();
    ArrayList<Missile> m_enemmslist = new ArrayList<Missile>();

    ArrayList<Missile_TOT> AAA = new ArrayList<Missile_TOT>();

    ArrayList<Blackhole> m_black = new ArrayList<Blackhole>();
    ArrayList<BB> bomb2 = new ArrayList<BB>();
    ArrayList<explosion> bomb3 = new ArrayList<explosion>();
    ArrayList<hiddenbomb> bomb4 = new ArrayList<hiddenbomb>();

    long LastRegenEnemy = System.currentTimeMillis();
    long LastShoot = System.currentTimeMillis();

    @Override
    public void Destroy() {
    }

    @Override

    public void Init() {
        //AppManager.getInstance().m_shop.STATE_now
        m_missileplayer=new Missile_Player(0,0);
        //m_player = new Player(AppManager.getInstance( ).getBitmap(R.drawable .player));
        m_boss = new Boss();
        m_boss2 = new Boss2();
        m_boss3 = new Boss3();
        m_keypad = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.keypad));
        bombbnt = AppManager.getInstance().getBitmap(R.drawable.bombbnt);
        m_background = new BackGround(0);
        sel_c_int = AppManager.getInstance().m_selectState.sel_C_int;//뱅기 번호

    }

    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        canvas.drawBitmap(bombbnt, 900, 1400, null);
        for (Missile_Player pms : m_pmslist) {
            pms.Draw(canvas);
        }
        for (Missile_TOT A : AAA) {
            A.Draw(canvas);
        }

        for (Blackhole bll : m_black) {
            bll.Draw(canvas);
        }
        for (BB B : bomb2) {
            B.Draw(canvas);
        }//폭탄2
        for (explosion ex : bomb3) {
            ex.Draw(canvas);
        }//폭탄3
        for (hiddenbomb hid : bomb4) {
            hid.Draw(canvas);
        }//폭탄4

        for (Missile enemms : m_enemmslist) {
            enemms.Draw(canvas);
        }
        for (Enemy enem : m_enemlist) {
            enem.Draw(canvas);
        }
        AppManager.getInstance().m_player.Draw(canvas);
        //hid.Draw( canvas);
        //m_keypad .Draw(canvas);
        Paint p = new Paint();
        p.setTextSize(30);
        p.setColor(Color.BLACK);
        for(int i=0;i<AppManager.getInstance().m_player.getLife();i++) {
            // canvas.drawText("남은 목숨 :" + String.valueOf(m_player.getLife()), 0, 20, p);
            //canvas.drawText("남은 목숨 :", 0, 20, p);
            canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.heart), i*30, 50, null);
        }
        for(int i=0;i<bom_cnt;i++){
            canvas.drawBitmap(AppManager.getInstance().getBitmap(R.drawable.bombbnt), i*30,80, null);
        }
        if(Bosscnt>5)
            m_boss.Draw(canvas);
//        if(Bosscnt>5)
//            m_boss2.Draw(canvas);
//        if(Bosscnt>5)
//            m_boss3.Draw(canvas);
        if (Bosscnt>5) {

            Paint Bar = new Paint();
            Bar.setColor(Color.RED);
            canvas.drawRect(0, 0, 960, 50, Bar);

            Paint Bar2 = new Paint();
            Bar2.setColor(Color.GREEN);
            canvas.drawRect(0, 0, BarHp, 50, Bar2);
        }
    }

    public void Bomb1() {
        if (System.currentTimeMillis() - LastShoot >= 1000) {
            LastShoot = System.currentTimeMillis();
            for (int i = 1; i < 9; i++) { //필사기1
                AppManager.getInstance().m_gameState.m_pmslist.add(new Missile_Player(0 + 10 * i * i, 1400)); //미사일 발사 위치
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                }
            }
        }

    }

    public void Bomb2(int x, int y) { //블랙홀
        BB a = null;
        a = new BB(x, y);
        bomb2.add(a);
    }

    public void Bomb3() {
        if (System.currentTimeMillis() - LastShoot >= 1000) {
            LastShoot = System.currentTimeMillis();
            if (!Trace) {
                AppManager.getInstance().m_gameState.AAA.add(new Missile_TOT(100, 1200));
                //Trace = true;
            }
        }
    }
    public void Bomb3_1(int x, int y){
        explosion a = null;
        a = new explosion(x, y);
        bomb3.add(a);
    }

    public void Bomb4(int x, int y) { //히든 폭탄
        hiddenbomb H = null;

        H = new hiddenbomb(x, y);//번개

        bomb4.add(H);
    }


    public void MakeEnemy() {
        if (System.currentTimeMillis() - LastRegenEnemy >= 1000) {
            LastRegenEnemy = System.currentTimeMillis();
            int enemtype = randEnem.nextInt(3);
            Enemy enem = null;
            if (enemtype == 0) enem = new Enemy_1();
            else if (enemtype == 1) enem = new Enemy_2();
            else if (enemtype == 2) enem = new Enemy_3();

            enem.setPosition(randEnem.nextInt(280), -60);
            enem.movetype = randEnem.nextInt(3);
            Bosscnt++;
            m_enemlist.add(enem);
        }
    }

    @Override
    public void Update() {
        if(BarHp<0){               //추가
            AppManager.getInstance().m_gameView.ChangeGameState(new Shop());
        }
        long GameTime = System.currentTimeMillis();
        AppManager.getInstance().m_player.Update(GameTime);
        m_background.Update(GameTime);
        if(Bosscnt>5)
        m_boss.BossUpdate(GameTime);
        m_boss.Update(GameTime);
//        m_boss2.BossUpdate(GameTime);
//        m_boss2.Update(GameTime);
//        m_boss3.BossUpdate(GameTime);
//        m_boss3.Update(GameTime);
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            Missile_Player pms = m_pmslist.get(i);
            pms.Update(GameTime);
            if (pms.state == Missile.STATE_OUT)
                m_pmslist.remove(i);
        }

        for (int i = AAA.size() - 1; i >= 0; i--) {
            Missile_TOT A = AAA.get(i);
            A.Update(GameTime);
            if (A.state == Missile.STATE_OUT) {
                AAA.remove(i);

            }
        }

        for (int i = m_black.size() - 1; i >= 0; i--) {
            Blackhole bll = m_black.get(i);
            bll.Update(GameTime);
            if (bll.state == Blackhole.STATE_OUT) {
                m_black.remove(i);
                //m_player.addScore();
            }
        }

        for (int i = bomb2.size() - 1; i >= 0; i--) {
            BB B = bomb2.get(i);
            B.Update(GameTime);
            if (B.state == Blackhole.STATE_OUT) {
                bomb2.remove(i);
                BarHp -=9900;
            }
        }

        for (int i = bomb3.size() - 1; i >= 0; i--) {
            explosion ex = bomb3.get(i);
            ex.Update(GameTime);
            if (ex.state == Blackhole.STATE_OUT) {
                bomb3.remove(i);
            }
        }

        for (int i = bomb4.size() - 1; i >= 0; i--) {
            hiddenbomb hid = bomb4.get(i);
            hid.Update(GameTime);
            if (hid.state == Blackhole.STATE_OUT) {
                bomb4.remove(i);
            }
        }

        for (int i = m_enemlist.size() - 1; i >= 0; i--) {
            Enemy enem = m_enemlist.get(i);
            enem.Update(GameTime);
            if (enem.state == Enemy.STATE_OUT) {
                m_enemlist.remove(i);
                AppManager.getInstance().m_player.addscore();
            }
        }
        for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
            Missile enemms = m_enemmslist.get(i);
            enemms.Update();

            if (enemms.state == Missile.STATE_OUT) {
                m_enemmslist.remove(i);
            }
        }

        if (Missile.trace3 == true && bom_cnt<=3) {//폭탄 3발동

            Bomb3_1(100, 400);
            Bomb3_1(300,400);
            Bomb3_1(100,800);
            Bomb3_1(300,800);
            Bomb3_1(100,1200);
            Bomb3_1(300,1200);
            Bomb3_1(500,800);
            Bomb3_1(500,1200);
            Bomb3_1(500,400);

            Missile.trace3 = false;
        }


        MakeEnemy();
        //hid.Update(GameTime);//히든
        CheckCollision();
    }

    public GameState() {
        AppManager.getInstance().m_gameState = this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int x = AppManager.getInstance().m_player.getX();
        int y = AppManager.getInstance().m_player.getY();
//        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) // 왼쪽
//            m_player.setPosition( x-10, y );
//        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) // 오른쪽
//            m_player.setPosition( x+10, y );
//        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) // 위
//            m_player.setPosition( x, y-10 );
//        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) // 아래
//            m_player.setPosition( x, y+10 );
         if (keyCode == KeyEvent.KEYCODE_SPACE) // 스페이스 바를 눌렀을 때
        AAA.add(new Missile_TOT(x + 20, y + 104));
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX(0);
        int y = (int) event.getY(0);
        int action = event.getAction();
        Log.e("dd", "좌표" + x + " " + y);

        if (x > 900 && x < 1050 && y > 1400 && y < 1500) {
            if(action == MotionEvent.ACTION_DOWN){

                if (sel_c_int == 0 && bom_cnt > 0) {
                    Bomb1();//미사일여러개
                    bom_cnt--;
                    Log.e("onTouchEvent", String.valueOf(bom_cnt));
                }
                if (sel_c_int == 1 && bom_cnt > 0) {
                    Bomb2(200,1200);//폭탄2
                    bom_cnt--;
                    Log.e("onTouchEvent", String.valueOf(bom_cnt));
                }
                if (sel_c_int == 2 && bom_cnt >0) {
                    Bomb3();//폭탄3
                    bom_cnt--;
                    Log.e("onTouchEvent", String.valueOf(bom_cnt));
                }
                if (sel_c_int == 3 && bom_cnt > 0) {

                    Bomb4(0,1200);//히든
                    Bomb4(400,1200);//히든
                    bom_cnt--;
                    Log.e("onTouchEvent", String.valueOf(bom_cnt));
                }

            }
        } else if (x >= 0 && x <= 1050 && y < 1600) {
            AppManager.getInstance().m_player.setPosition(x - 81, y - 161);
        }


//        if(!Trace) {
//            AppManager.getInstance().m_gameState.AAA.add(new Missile_TOT(10, 1000));
//            Trace=true;
//        }

        if (System.currentTimeMillis() - LastShoot >= 1000) {
            LastShoot = System.currentTimeMillis();
            //MakeBoB(x-20,y+104);//필사기2

            AppManager.getInstance().m_gameState.m_pmslist.add(new Missile_Player(x+10, y+104));

//                for (int i=1;i<7;i++){ //필사기1
//                    AppManager.getInstance().m_gameState.m_pmslist.add(new Missile_Player(x+10*i*i, y+104)); //미사일 발사 위치
//                    try{ Thread.sleep(50);}catch(Exception e){}
//                }
//

            /*
            for (int i=1;i<7;i++){
                AppManager.getInstance().m_gameState.m_pmslist.add(new Missile_Player(x+10*i*i, y+104)); //미사일 발사 위치
                SystemClock.sleep(50);
            }
            */
        }
        return true;
    }

    public void CheckCollision() {
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        m_pmslist.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {
                    m_pmslist.remove(i);
                    Enemy en = m_enemlist.get(j);    //추가
                    en.minusHp();                       //추가
                    if(en.hp<=0) {
                        m_enemlist.remove(j);   //추가
                        AppManager.getInstance().m_player.addscore();
                    }
                    return;
                }
            }
        }

        for (int i = bomb2.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        bomb2.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {
                    //bomb2.remove(i);
                    AppManager.getInstance().m_player.addscore();
                    m_enemlist.remove(j);
                    return;
                }
            }
        }

        for (int i = bomb3.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        bomb3.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {
                    //bomb3.remove(i);
                    AppManager.getInstance().m_player.addscore();
                    m_enemlist.remove(j);
                    return;
                }
            }
        }

        for (int i = bomb4.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        bomb4.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {
                    //bomb3.remove(i);
                    AppManager.getInstance().m_player.addscore();
                    m_enemlist.remove(j);
                    return;
                }
            }
        }

        for (int i = m_enemlist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(
                    AppManager.getInstance().m_player.m_BoundBox,
                    m_enemlist.get(i).m_BoundBox)) {
                m_enemlist.remove(i);
                AppManager.getInstance().m_player.destroyPlayer();
                if (AppManager.getInstance().m_player.getLife() <= 0) System.exit(0);
            }
        }
        for (int i = m_enemmslist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(
                    AppManager.getInstance().m_player.m_BoundBox,
                    m_enemmslist.get(i).m_BoundBox)) {
                m_enemmslist.remove(i);
                AppManager.getInstance().m_player.destroyPlayer();
                if (AppManager.getInstance().m_player.getLife() <= 0) System.exit(0);
            }
        }
        for(int i = m_pmslist.size()-1;i>=0;i--){
            if(CollisionManager.CheckBoxToBoxWhenBoss(m_pmslist.get(i).m_BoundBox,m_boss.m_BoundBox))
            {
                m_pmslist.remove(i);
                BarHp -= 50;

            }
        }
        for(int i = m_pmslist.size()-1;i>=0;i--){
            if(CollisionManager.CheckBoxToBoxWhenBoss(m_pmslist.get(i).m_BoundBox,m_boss2.m_BoundBox))
            {
                m_pmslist.remove(i);
                BarHp -= 50;

            }
        }
        for(int i = m_pmslist.size()-1;i>=0;i--){
            if(CollisionManager.CheckBoxToBoxWhenBoss(m_pmslist.get(i).m_BoundBox,m_boss3.m_BoundBox))
            {
                m_pmslist.remove(i);
                BarHp -= 50;

            }
        }

    }
}
