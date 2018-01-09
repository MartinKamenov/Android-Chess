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

public class King implements Piece {
    private Color color;
    private int row;
    private int col;
    private Context context;
    private boolean hasMoved;

    public King(Color color, int row, int col, Context context) {
        setColor(color);
        setRow(row);
        setCol(col);
        hasMoved = false;
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

        fillResult(result, board, row + 1, col + 1);
        fillResult(result, board, row + 1, col - 1);
        fillResult(result, board, row + 1, col);
        fillResult(result, board, row, col + 1);
        fillResult(result, board, row, col - 1);
        fillResult(result, board, row - 1, col + 1);
        fillResult(result, board, row - 1, col - 1);
        fillResult(result, board, row - 1, col);


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
    public void draw(Canvas canvas, int playerTurn) {
        Bitmap icon = null;
        if(getColor()==Color.White) {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.whiteking);
        } else {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.blackking);
        }
        Rect whiteRect = new Rect(col * Constants.CELL_WIDTH, row * Constants.CELL_WIDTH
                , col * Constants.CELL_WIDTH + Constants.CELL_WIDTH, row * Constants.CELL_WIDTH + Constants.CELL_WIDTH);

        Rect blackRect = new Rect((7 - col) * Constants.CELL_WIDTH, (7 - row) * Constants.CELL_WIDTH
                , (7 - col) * Constants.CELL_WIDTH + Constants.CELL_WIDTH, (7 - row) * Constants.CELL_WIDTH + Constants.CELL_WIDTH);

        if(playerTurn%2==1) {
            canvas.drawBitmap(icon, null, whiteRect, null);
        } else {
            canvas.drawBitmap(icon, null, blackRect, null);
        }
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
