package org.androidtown.myapplication.org;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.androidtown.myapplication.org.org.game.GameState;
import org.androidtown.myapplication.org.org.game.SelectState;

//import org.androidtown.myapplication.org.game.GameState;
//import org.androidtown.myapplication.org.game.Player;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameViewThread m_thread;
    private IState m_state;
    private GraphicObject m_Image;
    private SpriteAnimation m_spr;
    public int d_x,d_y;
    // private Player m_player;
    // private Enemy m_enemy;
    Display display;

    public GameView(Context context) {
        super(context);
        setFocusable(true);     //뷰의 포커스를 가질 수 있는지를 변경할 수 있다. 터치 포커스 혹은 키보드 포커스로 변경하기 위해서 쓰인다.
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        ChangeGameState(new SelectState());
        getHolder( ).addCallback( this );
        m_thread = new GameViewThread(getHolder( ), this);
        display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();

        // m_player=new Player(BitmapFactory.decodeResource(getResources(),R.drawable.player));
        // m_enemy=new Enemy(BitmapFactory.decodeResource(getResources(),R.drawable.enemy1));
        //m_Image=new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background2));
        //m_spr=new SpriteAnimation(BitmapFactory.decodeResource(getResources(),R.drawable.enemy1));
        //m_spr.initSpriteData(300,300,5,4);
        //ChangeGameState( new IntroState());
    }
    public int d_x(){
        return d_x = display.getWidth();
    }
    public int d_y(){
        return d_y= display.getHeight();
    }

    public void ChangeGameState(IState _state)
    {
        if (m_state!=null)
            m_state.Destroy();
        _state.Init();
        m_state=_state;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while (retry) {
            try {
                m_thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }

    }
    public void Update() {
        //long gameTime = System.currentTimeMillis();
        m_state.Update();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return m_state.onKeyDown(keyCode, event);
    }

    public boolean onTouchEvent(MotionEvent event) {

        return m_state.onTouchEvent(event);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        //m_Image.Draw(canvas);
        // m_player.Draw(canvas);
        //m_enemy.Draw(canvas);
        m_state.Render(canvas);
        //m_state.Render(canvas);
        Update();
    }
}