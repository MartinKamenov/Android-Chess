package com.kamenov.martin.chess.android;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kamenov.martin.chess.R;
import com.kamenov.martin.chess.contracts.Constants;
import com.kamenov.martin.chess.game.GamePanel;

public class GameActivity extends Activity implements View.OnClickListener {
    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.COLS = 8;
        Constants.ROWS = 8;
        Constants.GAME_WIDTH = Math.min(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Constants.CELL_WIDTH = Constants.GAME_WIDTH/Constants.COLS;
        gamePanel = new GamePanel(this);
        // int leftSpace = Constants.SCREEN_HEIGHT - Constants.GAME_WIDTH;
        setContentView(R.layout.activity_game);
        LinearLayout parent = findViewById(R.id.container);
        parent.addView(gamePanel);

        LinearLayout.LayoutParams gameLayoutParams = (LinearLayout.LayoutParams)gamePanel.getLayoutParams();
        gameLayoutParams.height = Constants.GAME_WIDTH;
        gameLayoutParams.width = Constants.GAME_WIDTH;
        gamePanel.setLayoutParams(gameLayoutParams);

        Constants.UNDO_BTN_ID = View.generateViewId();
        Button undoButton = new Button(new ContextThemeWrapper(this, R.style.InfoButton));
        undoButton.setText("Undo");
        undoButton.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL), Typeface.BOLD_ITALIC);
        undoButton.setTextColor(Color.WHITE);
        undoButton.setId(Constants.UNDO_BTN_ID);
        undoButton.setOnClickListener(this);
        parent.addView(undoButton);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == Constants.UNDO_BTN_ID) {
            this.gamePanel.undo();
        }
    }
}
