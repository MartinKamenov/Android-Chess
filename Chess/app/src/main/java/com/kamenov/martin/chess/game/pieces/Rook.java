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

    public Rook(PlayerColor playerColor, int row, int col, Context context) {
        super(playerColor, row, col, context);
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
}
