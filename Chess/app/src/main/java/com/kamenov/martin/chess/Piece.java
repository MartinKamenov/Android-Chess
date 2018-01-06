package com.kamenov.martin.chess;

/**
 * Created by Martin on 29.12.2017 г..
 */

public interface Piece extends GameObject {
    Color getColor();

    void setColor(Color color);

    boolean[][] canMove(Piece[][] board);

    void move(int row, int col);

    int getRow();

    int getCol();
}
