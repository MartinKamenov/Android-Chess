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

public class Pawn extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;
    private boolean hasMoved;

    public Pawn(PlayerColor playerColor, int row, int col, Context context) {
        super(context);
        setPlayerColor(playerColor);
        setRow(row);
        setCol(col);
        hasMoved = false;
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
        if(playerColor == PlayerColor.Black) {
            if(++checkingRow<Constants.ROWS) {
                if (board[checkingRow][checkingCol] == null) {
                    result[checkingRow][checkingCol] = true;
                    if(!hasMoved && board[++checkingRow][checkingCol] == null) {
                        result[checkingRow][checkingCol] = true;
                    }
                }
                checkingRow = row + 1;
                if (checkingCol+1<Constants.COLS && board[checkingRow][checkingCol+1] != null
                        && board[checkingRow][checkingCol+1].getPlayerColor()!= playerColor) {
                    result[checkingRow][checkingCol + 1] = true;
                }
                if (checkingCol-1>=0 && board[checkingRow][checkingCol-1] != null
                        && board[checkingRow][checkingCol-1].getPlayerColor()!= playerColor) {
                    result[checkingRow][checkingCol - 1] = true;
                }
            }
        }

        if(playerColor == PlayerColor.White) {
            if(--checkingRow>=0) {
                if (board[checkingRow][checkingCol] == null) {
                    result[checkingRow][checkingCol] = true;
                    if(!hasMoved && board[--checkingRow][checkingCol] == null) {
                        result[checkingRow][checkingCol] = true;
                    }
                }
                checkingRow = row - 1;
                if (checkingCol+1<Constants.COLS && board[checkingRow][checkingCol+1] != null
                        && board[checkingRow][checkingCol+1].getPlayerColor()!= playerColor) {
                    result[checkingRow][checkingCol + 1] = true;
                }
                if (checkingCol-1>=0 && board[checkingRow][checkingCol-1] != null
                        && board[checkingRow][checkingCol-1].getPlayerColor()!= playerColor) {
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
