package org.androidtown.myapplication.org;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
    public Bitmap m_bitmap;
    public int m_x;
    public int m_y;
    public boolean mapPosion = false;
    public GraphicObject(Bitmap bitmap) {
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
    }
    public void setPosition( int x, int y) {
        m_x = x;
        m_y = y;
    }
    public void Draw(Canvas canvas) {
        canvas.drawBitmap( m_bitmap, m_x, m_y, null);
    }
    public int getX( ) { return m_x; }
    public int getY( ) { return m_y; }
}
