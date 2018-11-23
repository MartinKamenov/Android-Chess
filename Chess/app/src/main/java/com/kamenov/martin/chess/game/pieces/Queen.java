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
 * Created by Martin on 29.12.2017 г..
 */

public class Queen extends DrawablePiece {
    private PlayerColor playerColor;
    private int row;
    private int col;

    public Queen(PlayerColor playerColor, int row, int col, Context context) {
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
        // up
        for(int i = row - 1; i>=0 ; i--) {
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
            if(++checkingCol>=Constants.COLS||--checkingRow<0) {
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

    @Override
    public void move(int row, int col) {
        setRow(row);
        setCol(col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
