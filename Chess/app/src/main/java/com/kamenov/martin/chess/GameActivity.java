package com.kamenov.martin.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.COLS = 8;
        Constants.ROWS = 8;
        Constants.GAME_WIDTH = Math.min(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Constants.CELL_WIDTH = Constants.GAME_WIDTH/Constants.COLS;
        setContentView(new GamePanel(this));
    }
}
