package com.kamenov.martin.chess;

import android.graphics.Canvas;

/**
 * Created by Martin on 29.12.2017 г..
 */

public interface Piece {
    void draw(Canvas canvas, int playerTurn);

    Color getColor();

    void setColor(Color color);

    boolean[][] canMove(Piece[][] board);

    void move(int row, int col);

    int getRow();

    int getCol();
}
