package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.org.GameView;
import org.androidtown.myapplication.org.GraphicObject;
import org.androidtown.myapplication.org.IState;
import org.androidtown.myapplication.R;

public class Shop extends GraphicObject implements IState {
    int x, y;
    public static final int STATE_A = 0;
    public static final int STATE_B = 1;  //스테이지1
    public static final int STATE_C = 2; //스테이지2
    public static final int STATE_D = 3; //스테이지3
    public int STATE_now = STATE_A;
    Bitmap m_bitmap;
    Bitmap m_BackGroundImage;
    Bitmap m_plus;
    Bitmap m_Exit;
    Bitmap m_minus;
    private static int savenum = AppManager.getInstance().m_player.getscore();
    private static int savelife = AppManager.getInstance().m_player.getLife();
    private static int savedamage = AppManager.getInstance().m_player.getDamage();
    private static int savespeed = AppManager.getInstance().m_gameState.m_missileplayer.getspeed();



    public Shop() {
        super(null);
        m_BackGroundImage = BitmapFactory.decodeResource(AppManager.getInstance().m_gameView.getResources(),
                R.drawable.storebackground, null);

        m_bitmap = Bitmap.createScaledBitmap(m_BackGroundImage, AppManager.getInstance().m_gameView.d_x(),
                AppManager.getInstance().m_gameView.d_y(), true);

        m_plus = AppManager.getInstance().getBitmap(R.drawable.plus_image);
        m_minus = AppManager.getInstance().getBitmap(R.drawable.minus_image);
        m_Exit = AppManager.getInstance().getBitmap(R.drawable.exit_image);
    }

    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, 0, 0, null);
        canvas.drawBitmap(m_plus, 950, 900, null);
        canvas.drawBitmap(m_plus, 950, 700, null);
        canvas.drawBitmap(m_plus, 950, 500, null);
        canvas.drawBitmap(m_Exit, 500, 1100, null);

        canvas.drawBitmap(m_minus, 80, 900, null);
        canvas.drawBitmap(m_minus, 80, 700, null);
        canvas.drawBitmap(m_minus, 80, 500, null);
    }

    @Override
    public void Init() {

        //m_player = new Player(AppManager.getInstance( ).getBitmap(R.drawable .player));
        //m_Mspeed = new Missile_Player(0,0);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        if (STATE_now == STATE_B) {
            AppManager.getInstance().m_gameView.ChangeGameState(new GameState());
            AppManager.getInstance().m_player= AppManager.getInstance().m_selectState.drawC(AppManager.getInstance().m_selectState.sel_C_int);
        }
    }

    @Override
    public void Render(Canvas canvas) {
        this.Draw(canvas);

        Paint point0 = new Paint();
        point0.setTextSize(90);
        point0.setColor(Color.WHITE);
        canvas.drawText("능력치 '+1' -> 점수 '-1'", 200, 100, point0);

        Paint point = new Paint();
        point.setTextSize(150);
        point.setColor(Color.RED);
        canvas.drawText("점수 :" + String.valueOf(AppManager.getInstance().m_player.getscore()), 350, 300, point);

        Paint point1 = new Paint();
        point1.setTextSize(50);
        point1.setColor(Color.WHITE);
        canvas.drawText("미사일 속도 :" + String.valueOf(AppManager.getInstance().m_gameState.m_missileplayer.getspeed()), 250, 530, point1);

        Paint point2 = new Paint();
        point2.setTextSize(50);
        point2.setColor(Color.WHITE);
        canvas.drawText("미사일 데미지 :" + String.valueOf(AppManager.getInstance().m_player.getDamage()), 250, 730, point2);

        Paint point3 = new Paint();
        point3.setTextSize(50);
        point3.setColor(Color.WHITE);
        canvas.drawText("생명력 :" + String.valueOf(AppManager.getInstance().m_player.getLife()), 250, 930, point3);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("알림","눌림"+x+y);

        Rect box_card = new Rect(948, 496, 1023, 571);
        Rect box_card1 = new Rect(948, 696, 1023, 771);
        Rect box_card2 = new Rect(948, 896, 1023, 971);

        Rect box_card4 = new Rect(73, 496, 148, 571);
        Rect box_card5 = new Rect(73, 696, 148, 771);
        Rect box_card6 = new Rect(73, 896, 148, 971);

        Rect box_card3 = new Rect(497, 1099, 605, 1149);
        if (AppManager.getInstance().m_player.getscore() > 0) {
            if (box_card.contains(x, y)) {
                AppManager.getInstance().m_player.minusscore();
                AppManager.getInstance().m_gameState.m_missileplayer.addspeed();
            } else if (box_card1.contains(x, y)) {
                AppManager.getInstance().m_player.minusscore();
                AppManager.getInstance().m_player.addDamage();
            } else if (box_card2.contains(x, y)) {
                if(AppManager.getInstance().m_player.getLife()<10) {
                    AppManager.getInstance().m_player.minusscore();
                    AppManager.getInstance().m_player.addLife();
                }
            }
        }
       if(AppManager.getInstance().m_player.getscore() < savenum) {
            if (box_card4.contains(x, y)) {
                if(AppManager.getInstance().m_gameState.m_missileplayer.getspeed() >= savespeed) {
                    AppManager.getInstance().m_player.addscore();
                    AppManager.getInstance().m_gameState.m_missileplayer.minusspeed();
                }
            } else if (box_card5.contains(x, y)) {
                if(AppManager.getInstance().m_player.getDamage() >= savedamage) {
                    AppManager.getInstance().m_player.addscore();
                    AppManager.getInstance().m_player.minusDamage();
                }
            } else if (box_card6.contains(x, y)) {
                if(AppManager.getInstance().m_player.getLife() >= savelife||AppManager.getInstance().m_player.getLife() < 10) {
                 {
                     AppManager.getInstance().m_player.addscore();
                     AppManager.getInstance().m_player.destroyPlayer();
                    }
                }
            }
        }
            if (box_card3.contains(x, y)) {  //EXIT 눌렀을때
               // if(STATE_now==STATE_A)
                STATE_now = STATE_B;
               // else if(STATE_now==STATE_B)
                //    STATE_now=STATE_C;
            }

            return true;
    }
}
