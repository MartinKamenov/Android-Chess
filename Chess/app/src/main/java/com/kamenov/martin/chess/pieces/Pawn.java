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

public class Pawn implements Piece {
    private Color color;
    private int row;
    private int col;
    private Context context;
    private boolean hasMoved;

    public Pawn(Color color, int row, int col, Context context) {
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
        // up left diagonal
        int checkingCol = col;
        int checkingRow = row;
        if(color == Color.Black) {
            if(++checkingRow<Constants.ROWS) {
                if (board[checkingRow][checkingCol] == null) {
                    result[checkingRow][checkingCol] = true;
                    if(!hasMoved && board[++checkingRow][checkingCol] == null) {
                        result[checkingRow][checkingCol] = true;
                    }
                }
                checkingRow = row + 1;
                if (checkingCol+1<Constants.COLS && board[checkingRow][checkingCol+1] != null
                        && board[checkingRow][checkingCol+1].getColor()!=color) {
                    result[checkingRow][checkingCol + 1] = true;
                }
                if (checkingCol-1>=0 && board[checkingRow][checkingCol-1] != null
                        && board[checkingRow][checkingCol-1].getColor()!=color) {
                    result[checkingRow][checkingCol - 1] = true;
                }
            }
        }

        if(color == Color.White) {
            if(--checkingRow>=0) {
                if (board[checkingRow][checkingCol] == null) {
                    result[checkingRow][checkingCol] = true;
                    if(!hasMoved && board[--checkingRow][checkingCol] == null) {
                        result[checkingRow][checkingCol] = true;
                    }
                }
                checkingRow = row - 1;
                if (checkingCol+1<Constants.COLS && board[checkingRow][checkingCol+1] != null
                        && board[checkingRow][checkingCol+1].getColor()!=color) {
                    result[checkingRow][checkingCol + 1] = true;
                }
                if (checkingCol-1>=0 && board[checkingRow][checkingCol-1] != null
                        && board[checkingRow][checkingCol-1].getColor()!=color) {
                    result[checkingRow][checkingCol - 1] = true;
                }
            }
        }

        return result;
    }

    @Override
    public void move(int row, int col) {
        hasMoved = true;
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
                    R.drawable.whitepawn);
        } else {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.blackpawn);
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
