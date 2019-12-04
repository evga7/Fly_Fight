package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.org.IState;

import java.util.Random;

public class SelectState implements IState {
    public float sel_x,sel_y;
    private RanC ranc;
    private Bitmap[] m_Plist=new Bitmap[5];
    private Bitmap[] m_Plist2=new Bitmap[4];
    private int flag=0;
    public int sel_C_int=0;
    private Bitmap sel;
    private int touchcnt=0;
    public static int aaa=0;
    Random ran=new Random();
    public SelectState() {
        AppManager.getInstance().m_selectState=this;
    }
    @Override
    public void Init() {
        m_Plist[0]=AppManager.getInstance( ).getBitmap(R.drawable.av8c);
        m_Plist[1]=AppManager.getInstance( ).getBitmap(R.drawable.f4c);
        m_Plist[2]=AppManager.getInstance( ).getBitmap(R.drawable.f18c);
        m_Plist[3]=AppManager.getInstance( ).getBitmap(R.drawable.mul);
        m_Plist[4]=AppManager.getInstance( ).getBitmap(R.drawable.f22c);
        m_Plist2[0]=AppManager.getInstance( ).getBitmap(R.drawable.av8cc);
        m_Plist2[1]=AppManager.getInstance( ).getBitmap(R.drawable.f4cc);
        m_Plist2[2]=AppManager.getInstance( ).getBitmap(R.drawable.f18cc);
        m_Plist2[3]=AppManager.getInstance( ).getBitmap(R.drawable.f22cc);
        sel=AppManager.getInstance( ).getBitmap(R.drawable.sel);
        ranc = new RanC(AppManager.getInstance().getBitmap(R.drawable.ranccc));
        ranc.setPosition(200,300);
    }
    @Override
    public void Destroy() {
    }
    @Override
    public void Update() {
        long gameTime = System.currentTimeMillis( );
        ranc.Update(gameTime);

    }
    @Override
    public void Render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(sel,200,1300,null);
        for (int i=0;i<4;i++)
        {
            canvas.drawBitmap(m_Plist[i],200*(i+1),1100,null);
        }
        if (sel_x>200&&sel_x<250&&sel_y>1100&&sel_y<1200)
        {
            if (flag==0) {
                touchcnt=0;
                canvas.drawBitmap(m_Plist2[0], 200, 300, null);
                sel_C_int = 0;
            }
            else
                canvas.drawBitmap(m_Plist2[sel_C_int],200,300,null);
        }
        else if (sel_x>400&&sel_x<490&&sel_y>1100&&sel_y<1200)
        {
            if (flag==0) {
                touchcnt=0;
                canvas.drawBitmap(m_Plist2[1], 200, 300, null);
                sel_C_int = 1;
            }
            else
                canvas.drawBitmap(m_Plist2[sel_C_int],200,300,null);
        }
        else if (sel_x>600&&sel_x<690&&sel_y>1100&&sel_y<1200)
        {
            if (flag==0) {
                touchcnt=0;
                canvas.drawBitmap(m_Plist2[2], 200, 300, null);
                sel_C_int = 2;
            }
            else
                canvas.drawBitmap(m_Plist2[sel_C_int],200,300,null);
        }
        else if (sel_x>800&&sel_x<890&&sel_y>1100&&sel_y<1200)
        {
            if (touchcnt<2) {
                ranc.Draw(canvas);
            }
            if (flag==0&&touchcnt==2) {
                sel_C_int=ran.nextInt(4);
                flag=1;
            }
            if (flag==1)
                canvas.drawBitmap(m_Plist2[sel_C_int],200,300,null);
        }
        else if (sel_x>200&&sel_x<370&&sel_y>1300&&sel_y<1500)
        {
            AppManager.getInstance().m_gameView.ChangeGameState(new GameState());
            AppManager.getInstance().m_player=drawC(sel_C_int);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sel_x=event.getX();
        sel_y=event.getY();
        int action=event.getAction();
        Log.e("터치","텟"+sel_x+"||"+sel_y);
        if (sel_x>800&&sel_x<890&&sel_y>1100&&sel_y<1200)
        {
            if (action==event.ACTION_DOWN)
                touchcnt++;
        }
        return false;
    }
    public Player drawC(int n) {
        if (n == 0)
            return new Player(AppManager.getInstance().getBitmap(R.drawable.av8ccc));
        else if (n == 1)
            return new Player(AppManager.getInstance().getBitmap(R.drawable.f4ccc));
        else if (n == 2)
            return new Player(AppManager.getInstance().getBitmap(R.drawable.f18ccc));
        else
            return new Player(AppManager.getInstance().getBitmap(R.drawable.f22ccc));
    }
}
