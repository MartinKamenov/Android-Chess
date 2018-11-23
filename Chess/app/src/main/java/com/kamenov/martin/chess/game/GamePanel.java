package com.kamenov.martin.chess.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Martin on 29.12.2017 г..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback  {
    private Board board;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.board = new Board(context, this);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        if(x > Constants.GAME_WIDTH||x < 0||y > Constants.GAME_WIDTH||y < 0) {
            return false;
        }

        int chosenCol = x / Constants.CELL_WIDTH;
        int chosenRow = y / Constants.CELL_WIDTH;

        board.selectPlace(chosenRow, chosenCol);

        if(board.getMatrix()[chosenRow][chosenCol]!=null) {
            board.selectPlace(chosenRow, chosenCol);
        }

        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void draw() {
        canvas = null;

        try {
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                draw(canvas);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(canvas!=null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        board.draw(canvas);
    }
}
