package org.androidtown.myapplication.org;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.androidtown.myapplication.org.org.game.GameState;
import org.androidtown.myapplication.org.org.game.Missile_Player;
import org.androidtown.myapplication.org.org.game.Player;
import org.androidtown.myapplication.org.org.game.SelectState;
import org.androidtown.myapplication.org.org.game.Shop;

public class AppManager {
    private static AppManager s_instance;
    public GameView m_gameView;
    private Resources m_resources;
    public GameState m_gameState;
    public SelectState m_selectState;
    public Missile_Player m_missile_player;
    public Player m_player;
    public Shop m_shop;

    public Bitmap getBitmap(int r ) {
        return BitmapFactory.decodeResource( m_resources, r);
    }

    void setMissile (Missile_Player _missile) {
        m_missile_player = _missile;
    }       //추가
    Missile_Player getMissile( ) {
        return m_missile_player;
    }                   //추가
    void setGameView (GameView _gameView) {
        m_gameView = _gameView;
    }
    void setResources (Resources _resources) {
        m_resources = _resources;
    }
    GameView getGameView( ) {
        return m_gameView;
    }
    Resources getResource( ) {
        return m_resources;
    }

    public static AppManager getInstance()
    {
        if (s_instance==null) s_instance=new AppManager();
        return s_instance;
    }
}
