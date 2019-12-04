package org.androidtown.myapplication.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.androidtown.myapplication.R;

public class IntroState implements IState {
    Bitmap icon;
    int x, y;
    @Override
    public void Destroy( ) { }
    @Override
    public void Init( ) {
        icon = AppManager.getInstance( ).getBitmap(R.drawable. icon);
    }
    public void Render(Canvas canvas) {
        canvas.drawBitmap( icon, x, y, null);
    }
    @Override
    public void Update( ) { }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppManager.getInstance( ).getGameView( ).ChangeGameState
                ( new CreditState( ));
        return true;
    }

}

