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

public class Bishop extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;

    public Bishop(PlayerColor playerColor, int row, int col, Context context) {
        super(context);
        setPlayerColor(playerColor);
        setRow(row);
        setCol(col);
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
        // up left diagonal
        int checkingCol = col;
        int checkingRow = row;
        while(true) {
            if(--checkingCol<0||--checkingRow<0) {
                break;
            }
            if(board[checkingRow][checkingCol]==null) {
                result[checkingRow][checkingCol] = true;
            }
            else if(board[checkingRow][checkingCol].getPlayerColor() == this.playerColor) {
                break;
            } else {
                result[checkingRow][checkingCol] = true;
                break;
            }
        }

        // up right diagonal
        checkingCol = col;
        checkingRow = row;
        while(true) {
            if(++checkingCol>= Constants.COLS||--checkingRow<0) {
                break;
            }
            if(board[checkingRow][checkingCol]==null) {
                result[checkingRow][checkingCol] = true;
            }
            else if(board[checkingRow][checkingCol].getPlayerColor() == this.playerColor) {
                break;
            } else {
                result[checkingRow][checkingCol] = true;
                break;
            }
        }

        // up right diagonal
        checkingCol = col;
        checkingRow = row;
        while(true) {
            if(--checkingCol<0||++checkingRow>=Constants.ROWS) {
                break;
            }
            if(board[checkingRow][checkingCol]==null) {
                result[checkingRow][checkingCol] = true;
            }
            else if(board[checkingRow][checkingCol].getPlayerColor() == this.playerColor) {
                break;
            } else {
                result[checkingRow][checkingCol] = true;
                break;
            }
        }

        // down right diagonal
        checkingCol = col;
        checkingRow = row;
        while(true) {
            if(++checkingCol>=Constants.COLS||++checkingRow>=Constants.ROWS) {
                break;
            }
            if(board[checkingRow][checkingCol]==null) {
                result[checkingRow][checkingCol] = true;
            }
            else if(board[checkingRow][checkingCol].getPlayerColor() == this.playerColor) {
                break;
            } else {
                result[checkingRow][checkingCol] = true;
                break;
            }
        }

        return result;
    }
}
