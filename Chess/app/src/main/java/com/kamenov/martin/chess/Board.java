package com.kamenov.martin.chess;

import android.content.Context;
import android.graphics.*;
import android.graphics.Color;

import com.kamenov.martin.chess.pieces.Bishop;
import com.kamenov.martin.chess.pieces.King;
import com.kamenov.martin.chess.pieces.Knight;
import com.kamenov.martin.chess.pieces.Pawn;
import com.kamenov.martin.chess.pieces.Queen;
import com.kamenov.martin.chess.pieces.Rook;

import java.util.ArrayList;

/**
 * Created by Martin on 29.12.2017 г..
 */

public class Board implements GameObject {
    private Piece[][] matrix;
    private int playerTurn;
    private boolean pieceIsSelected;
    private Piece selectedPiece;
    private GamePanel gamePanel;

    public Board(Context context, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        matrix = new Piece[8][8];
        playerTurn = 1;
        pieceIsSelected = false;
        selectedPiece = null;

        matrix[7][0] = new Rook(com.kamenov.martin.chess.Color.White, 7, 0, context);
        matrix[7][7] = new Rook(com.kamenov.martin.chess.Color.White, 7, 7, context);
        matrix[0][0] = new Rook(com.kamenov.martin.chess.Color.Black, 0, 0, context);
        matrix[0][7] = new Rook(com.kamenov.martin.chess.Color.Black, 0, 7, context);

        matrix[7][1] = new Knight(com.kamenov.martin.chess.Color.White, 7, 1, context);
        matrix[7][6] = new Knight(com.kamenov.martin.chess.Color.White, 7, 6, context);
        matrix[0][1] = new Knight(com.kamenov.martin.chess.Color.Black, 0, 1, context);
        matrix[0][6] = new Knight(com.kamenov.martin.chess.Color.Black, 0, 6, context);

        matrix[7][2] = new Bishop(com.kamenov.martin.chess.Color.White, 7, 2, context);
        matrix[7][5] = new Bishop(com.kamenov.martin.chess.Color.White, 7, 5, context);
        matrix[0][2] = new Bishop(com.kamenov.martin.chess.Color.Black, 0, 2, context);
        matrix[0][5] = new Bishop(com.kamenov.martin.chess.Color.Black, 0, 5, context);

        matrix[7][3] = new Queen(com.kamenov.martin.chess.Color.White, 7, 3, context);
        matrix[0][3] = new Queen(com.kamenov.martin.chess.Color.Black, 0, 3, context);

        matrix[7][4] = new King(com.kamenov.martin.chess.Color.White, 7, 4, context);
        matrix[0][4] = new King(com.kamenov.martin.chess.Color.Black, 0, 4, context);

        for (int i = 0; i < Constants.COLS; i++) {
            matrix[6][i] = new Pawn(com.kamenov.martin.chess.Color.White, 6, i, context);
            matrix[1][i] = new Pawn(com.kamenov.martin.chess.Color.Black, 1, i, context);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int cellWidth = Constants.CELL_WIDTH;
        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.parseColor("#9D5233"));
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.parseColor("#FEE7A9"));
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.parseColor("#00FBFF"));
        bluePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint redPaint = new Paint();
        redPaint.setColor(Color.parseColor("#ff1414"));
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // Draws board
        Paint outsidePaint = new Paint();
        outsidePaint.setColor(Color.BLACK);
        outsidePaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(0, 0, Constants.GAME_WIDTH, Constants.GAME_WIDTH, outsidePaint);
        boolean[][] placesToMove = null;
        if (selectedPiece != null) {
            placesToMove = selectedPiece.canMove(matrix);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    canvas.drawRect(j * cellWidth, i * cellWidth, (j * cellWidth) + cellWidth,
                            (i * cellWidth) + cellWidth, blackPaint);
                } else {
                    canvas.drawRect(j * cellWidth, i * cellWidth, (j * cellWidth) + cellWidth,
                            (i * cellWidth) + cellWidth, whitePaint);
                }
                if (placesToMove != null && placesToMove[i][j]) {
                    if (matrix[i][j] == null) {
                        canvas.drawCircle(j * cellWidth + cellWidth / 2, i * cellWidth + cellWidth / 2, cellWidth / 5, bluePaint);
                    } else {
                        canvas.drawRect(j * cellWidth, i * cellWidth, (j * cellWidth) + cellWidth,
                                (i * cellWidth) + cellWidth, redPaint);
                    }
                }
            }
        }

        // Draws pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].draw(canvas);
                }
            }
        }

    }

    public Piece[][] getMatrix() {
        return matrix;
    }

    public void selectPlace(int row, int col) {
        if (row >= Constants.ROWS || col >= Constants.COLS ||
                row < 0 || col < 0) {
            return;
        } else if (!pieceIsSelected && matrix[row][col] != null) {
            com.kamenov.martin.chess.Color playerColor = null;
            if(playerTurn%2==1) {
                playerColor = com.kamenov.martin.chess.Color.White;
            } else {
                playerColor = com.kamenov.martin.chess.Color.Black;
            }
            selectedPiece = matrix[row][col];
            pieceIsSelected = true;
            if(selectedPiece.getColor()!=playerColor) {
                selectedPiece = null;
                pieceIsSelected = false;
            }
        } else if(pieceIsSelected && matrix[row][col] == null) {
            if(selectedPiece.canMove(getMatrix())[row][col]) {
                matrix[row][col] = selectedPiece;
                matrix[selectedPiece.getRow()][selectedPiece.getCol()] = null;
                selectedPiece.move(row, col);
                selectedPiece = null;
                pieceIsSelected = false;
                playerTurn++;
            }
        } else if(pieceIsSelected && matrix[row][col] != null) {
            if(selectedPiece.canMove(getMatrix())[row][col]) {
                matrix[row][col] = selectedPiece;
                matrix[selectedPiece.getRow()][selectedPiece.getCol()] = null;
                selectedPiece.move(row, col);
                selectedPiece = null;
                pieceIsSelected = false;
                playerTurn++;
            }
            else {
                if(selectedPiece.getColor()==matrix[row][col].getColor()) {
                    selectedPiece = matrix[row][col];
                }
            }
        }
        this.gamePanel.draw();
    }

    public void getMatrix(Piece[][] matrix) {
        this.matrix = matrix;
    }
}
