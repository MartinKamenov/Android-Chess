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

public class Knight extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;

    public Knight(PlayerColor playerColor, int row, int col, Context context) {
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
}
