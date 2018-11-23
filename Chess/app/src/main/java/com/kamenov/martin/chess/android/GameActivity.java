package com.kamenov.martin.chess.android;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kamenov.martin.chess.R;
import com.kamenov.martin.chess.contracts.Constants;
import com.kamenov.martin.chess.game.GamePanel;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
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
        GamePanel gamePanel = new GamePanel(this);
        int leftSpace = Constants.SCREEN_HEIGHT - Constants.GAME_WIDTH;
        setContentView(R.layout.activity_game);
        LinearLayout parent = (LinearLayout) findViewById(R.id.container);
        parent.addView(gamePanel);
        Constants.UNDO_BTN_ID = View.generateViewId();
        Button undoButton = new Button(this);
        undoButton.setText("Undo");
        undoButton.setId(Constants.UNDO_BTN_ID);
        undoButton.setOnClickListener(this);
        parent.addView(undoButton);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == Constants.UNDO_BTN_ID) {
            Toast.makeText(this, "Undo button clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
