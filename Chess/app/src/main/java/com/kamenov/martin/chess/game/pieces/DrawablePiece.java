package com.kamenov.martin.chess.game.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.kamenov.martin.chess.R;
import com.kamenov.martin.chess.game.Constants;
import com.kamenov.martin.chess.game.PlayerColor;

/**
 * Created by Martin on 23.11.2018 г..
 */

public abstract class DrawablePiece implements Piece {
    private Context context;
    private int row;
    private int col;
    protected PlayerColor playerColor;

    protected DrawablePiece(PlayerColor playerColor, int row, int col, Context context) {
        this.context = context;

    }

    public void draw(Canvas canvas, PlayerColor playerTurn) {
        Bitmap icon = null;
        if(getPlayerColor()== PlayerColor.White) {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.whiterook);
        } else {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.blackrook);
        }
        Rect whiteRect = new Rect(getCol() * Constants.CELL_WIDTH, getRow() * Constants.CELL_WIDTH
                , getCol() * Constants.CELL_WIDTH + Constants.CELL_WIDTH, getRow() * Constants.CELL_WIDTH + Constants.CELL_WIDTH);

        Rect blackRect = new Rect((7 - getCol()) * Constants.CELL_WIDTH, (7 - getRow()) * Constants.CELL_WIDTH
                , (7 - getCol()) * Constants.CELL_WIDTH + Constants.CELL_WIDTH, (7 - getRow()) * Constants.CELL_WIDTH + Constants.CELL_WIDTH);

        if(playerTurn == PlayerColor.White) {
            canvas.drawBitmap(icon, null, whiteRect, null);
        } else {
            canvas.drawBitmap(icon, null, blackRect, null);
        }
    }

    @Override
    public void move(int row, int col) {
        setRow(row);
        setCol(col);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
