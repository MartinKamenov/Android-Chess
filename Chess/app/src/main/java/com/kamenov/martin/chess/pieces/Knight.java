package com.kamenov.martin.chess.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.kamenov.martin.chess.Color;
import com.kamenov.martin.chess.Constants;
import com.kamenov.martin.chess.Piece;
import com.kamenov.martin.chess.R;

/**
 * Created by Martin on 3.1.2018 г..
 */

public class Knight implements Piece {
    private Color color;
    private int row;
    private int col;
    private Context context;

    public Knight(Color color, int row, int col, Context context) {
        setColor(color);
        setRow(row);
        setCol(col);
        this.context = context;
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean[][] canMove(Piece[][] board) {
        boolean[][] result = new boolean[Constants.ROWS][Constants.COLS];

        fillResult(result, board, row + 1, col + 2);
        fillResult(result, board, row + 1, col - 2);
        fillResult(result, board, row - 1, col - 2);
        fillResult(result, board, row - 1, col + 2);
        fillResult(result, board, row + 2, col + 1);
        fillResult(result, board, row + 2, col - 1);
        fillResult(result, board, row - 2, col + 1);
        fillResult(result, board, row - 2, col - 1);

        return result;
    }

    private void fillResult(boolean[][] result, Piece[][] board, int row, int col) {
        if(isInMatrix(row, col)) {
            if(board[row][col]==null||board[row][col].getColor()!=color) {
                result[row][col] = true;
            }
        }
    }

    private boolean isInMatrix(int checkingRow, int checkingCol) {
        if(checkingRow<0||checkingCol<0||checkingRow>=Constants.ROWS||checkingCol>=Constants.COLS) {
            return false;
        }
        return true;
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

    @Override
    public void draw(Canvas canvas) {
        Bitmap icon = null;
        if(getColor()==Color.White) {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.whiteknight);
        } else {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.blackknight);
        }
        Rect rect = new Rect(col * Constants.CELL_WIDTH, row * Constants.CELL_WIDTH
                , col * Constants.CELL_WIDTH + Constants.CELL_WIDTH, row * Constants.CELL_WIDTH + Constants.CELL_WIDTH);
        canvas.drawBitmap(icon, null, rect, null);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
