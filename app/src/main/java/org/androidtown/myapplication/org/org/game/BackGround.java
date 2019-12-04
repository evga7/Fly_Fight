package org.androidtown.myapplication.org.org.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.androidtown.myapplication.org.AppManager;
import org.androidtown.myapplication.R;
import org.androidtown.myapplication.org.GraphicObject;

public class BackGround extends GraphicObject {
    static final float SCROLL_SPEED = 2.4f;
    private float m_scroll = -2000 + 480;

    Bitmap m_layer2;
    static final float SCROLL_SPEED_2 = 2.4f;
    private float m_scroll_2 = -2000 + 480;
    public BackGround(int backtype ) {
        super(null);
        if (backtype == 0)
            m_bitmap = AppManager.getInstance( ).getBitmap (R.drawable. background1);
        else if (backtype == 1)
            m_bitmap = AppManager.getInstance( ).getBitmap (R.drawable. background2);
        //m_layer2 = AppManager.getInstance( ).getBitmap (R.drawable. background_2);
        m_bitmap = Bitmap.createScaledBitmap(m_bitmap,1100, m_bitmap.getHeight(), true);
        setPosition(0, (int) m_scroll);
    }
    void Update( long GameTime) {
        m_scroll = m_scroll + SCROLL_SPEED;
        if ( m_scroll >=0)  m_scroll = 0;
        setPosition(0, (int) m_scroll);
        m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
        if ( m_scroll_2 >=0)  m_scroll_2 = 0;
    }
    @Override
    public void Draw(Canvas canvas)  {
        canvas.drawBitmap( m_bitmap, m_x, m_y, null );
        //canvas.drawBitmap( m_layer2, m_x, m_scroll_2, null );
    }
}