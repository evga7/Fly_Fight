package org.androidtown.myapplication.org;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;
    private boolean m_run = false;
    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView) {
        m_surfaceHolder = surfaceHolder;
        m_gameView = gameView;
    }
    public void setRunning( boolean run ) {
        m_run = run;
    }

    @SuppressLint("WrongCall")
    @Override
        public void run() {
        Canvas _canvas;
        while ( m_run ) {
            _canvas = null;
            try { // SurfaceHolder를 통해 Surface에 접근해서 가져옴
                _canvas = m_surfaceHolder .lockCanvas( null );
                synchronized ( m_surfaceHolder ) {
                    m_gameView.onDraw(_canvas); // 그림을 그림
                }
            } finally {
                if (_canvas != null )
// Surface를 화면에 표시함
                    m_surfaceHolder .unlockCanvasAndPost(_canvas);
            }
        }
    }
}