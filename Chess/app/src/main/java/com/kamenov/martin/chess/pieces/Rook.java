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

public class Rook implements Piece {
    private Color color;
    private int row;
    private int col;
    private Context context;

    public Rook(Color color, int row, int col, Context context) {
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
        // up
        for(int i = row - 1; i >= 0 ; i--) {
            if(board[i][col]==null) {
                result[i][col] = true;
            }
            else if(board[i][col].getColor() == this.color) {
                break;
            } else {
                result[i][col] = true;
                break;
            }
        }
        // down
        for(int i = row + 1; i < Constants.ROWS; i++) {
            if(board[i][col]==null) {
                result[i][col] = true;
            }
            else if(board[i][col].getColor() == this.color) {
                break;
            } else {
                result[i][col] = true;
                break;
            }
        }
        // left
        for(int i = col - 1; i>=0 ; i--) {
            if(board[row][i]==null) {
                result[row][i] = true;
            }
            else if(board[row][i].getColor() == this.color) {
                break;
            } else {
                result[row][i] = true;
                break;
            }
        }
        // right
        for(int i = col + 1; i < Constants.COLS ; i++) {
            if(board[row][i]==null) {
                result[row][i] = true;
            }
            else if(board[row][i].getColor() == this.color) {
                break;
            } else {
                result[row][i] = true;
                break;
            }
        }

        return result;
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
                    R.drawable.whiterook);
        } else {
            icon = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.blackrook);
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
