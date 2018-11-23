package com.kamenov.martin.chess.game.pieces;

import android.content.Context;

import com.kamenov.martin.chess.game.PlayerColor;
import com.kamenov.martin.chess.contracts.Constants;

/**
 * Created by Martin on 3.1.2018 г..
 */

public class Bishop extends DrawablePiece {

    public Bishop(PlayerColor playerColor, int row, int col, Context context) {
        super(playerColor, row, col, context);
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
