package com.kamenov.martin.chess.game.pieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.kamenov.martin.chess.game.PlayerColor;
import com.kamenov.martin.chess.game.Constants;
import com.kamenov.martin.chess.R;

/**
 * Created by Martin on 3.1.2018 г..
 */

public class Rook extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;
    private Context context;

    public Rook(PlayerColor playerColor, int row, int col, Context context) {
        super(context);
        setPlayerColor(playerColor);
        setRow(row);
        setCol(col);
        this.context = context;
    }
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    @Override
    public boolean[][] canMove(Piece[][] board) {
        boolean[][] result = new boolean[Constants.ROWS][Constants.COLS];
        // up
        for(int i = row - 1; i >= 0 ; i--) {
            if(board[i][col]==null) {
                result[i][col] = true;
            }
            else if(board[i][col].getPlayerColor() == this.playerColor) {
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
            else if(board[i][col].getPlayerColor() == this.playerColor) {
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
            else if(board[row][i].getPlayerColor() == this.playerColor) {
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
            else if(board[row][i].getPlayerColor() == this.playerColor) {
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
