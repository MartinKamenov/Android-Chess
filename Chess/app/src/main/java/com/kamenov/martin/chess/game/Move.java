package com.kamenov.martin.chess.game;

import com.kamenov.martin.chess.game.pieces.Piece;

/**
 * Created by Martin on 24.11.2018 г..
 */

public class Move {
    private Piece piece;
    private int currentRow;
    private int currentCol;
    private int previousRow;
    private int previousCol;

    public Move(Piece piece, int currentRow, int currentCol, int previousRow, int previousCol) {
        this.piece = piece;
        this.currentCol = currentRow;
        this.currentCol = currentCol;
        this.previousRow = previousRow;
        this.previousCol = previousCol;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public int getPreviousRow() {
        return previousRow;
    }

    public int getPreviousCol() {
        return previousCol;
    }
}
