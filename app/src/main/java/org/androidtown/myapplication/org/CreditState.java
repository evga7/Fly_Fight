package org.androidtown.myapplication.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.androidtown.myapplication.R;

public class CreditState implements IState {
    Bitmap android;
    int x, y;
    @Override
    public void Destroy( ) { }
    @Override
    public void Init( ) {
        android = AppManager.getInstance( ).getBitmap(R.drawable.android);
    }
    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap( android, x, y, null);
    }
    @Override
    public void Update( ) { }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
