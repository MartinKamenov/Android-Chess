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

public class King extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;
    private boolean hasMoved;

    public King(PlayerColor playerColor, int row, int col, Context context) {
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
            if(board[row][col]==null||board[row][col].getPlayerColor()!= playerColor) {
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}