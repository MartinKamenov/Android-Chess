package com.kamenov.martin.chess.game.pieces;

import android.graphics.Canvas;

import com.kamenov.martin.chess.game.PlayerColor;

/**
 * Created by Martin on 29.12.2017 г..
 */

public interface Piece {
    void draw(Canvas canvas, PlayerColor playerTurn);

    PlayerColor getPlayerColor();

    void setPlayerColor(PlayerColor playerColor);

    boolean[][] canMove(Piece[][] board);

    void move(int row, int col);

    int getRow();

    int getCol();
}
