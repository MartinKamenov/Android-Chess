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
    protected int row;
    protected int col;
    protected PlayerColor playerColor;
    private Context context;
    private Class clazz;

    protected DrawablePiece(PlayerColor playerColor, int row, int col, Context context) {
        this.playerColor = playerColor;
        this.row = row;
        this.col = col;
        this.context = context;
        clazz = this.getClass();
    }

    public void draw(Canvas canvas, PlayerColor playerTurn) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), getResourceFigure());
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
    public PlayerColor getPlayerColor() {
        return playerColor;
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

    private int getResourceFigure() {
        if(clazz == Bishop.class && playerColor == PlayerColor.White) {
            return R.drawable.whitebishop;
        } else if(clazz == King.class && playerColor == PlayerColor.White) {
            return R.drawable.whiteking;
        } else if(clazz == Knight.class && playerColor == PlayerColor.White) {
            return R.drawable.whiteknight;
        } else if(clazz == Pawn.class && playerColor == PlayerColor.White) {
            return R.drawable.whitepawn;
        } else if(clazz == Queen.class && playerColor == PlayerColor.White) {
            return R.drawable.whitequeen;
        } else if(clazz == Rook.class && playerColor == PlayerColor.White) {
            return R.drawable.whiterook;
        } else if(clazz == Bishop.class) {
            return R.drawable.blackbishop;
        } else if(clazz == King.class) {
            return R.drawable.blackking;
        } else if(clazz == Knight.class) {
            return R.drawable.blackknight;
        } else if(clazz == Pawn.class) {
            return R.drawable.blackpawn;
        } else if(clazz == Queen.class) {
            return R.drawable.blackqueen;
        } else {
            return R.drawable.blackrook;
        }
    }
}
