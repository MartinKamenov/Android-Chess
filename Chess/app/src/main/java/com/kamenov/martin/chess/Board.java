package com.kamenov.martin.chess;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.graphics.Color;
import android.widget.Toast;

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
    private Piece whiteKing;
    private Piece blackKing;
    private boolean whiteIsInCheck;
    private boolean blackIsInCheck;
    private Context context;

    public Board(Context context, GamePanel gamePanel) {
        this.context = context;
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

        whiteKing = new King(com.kamenov.martin.chess.Color.White, 7, 4, context);
        blackKing = new King(com.kamenov.martin.chess.Color.Black, 0, 4, context);
        matrix[7][4] = whiteKing;
        matrix[0][4] = blackKing;

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
        Paint turquoisePaint = new Paint();
        turquoisePaint.setColor(Color.parseColor("#ffe019"));
        turquoisePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // Draws board
        Paint outsidePaint = new Paint();
        outsidePaint.setColor(Color.BLACK);
        outsidePaint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(0, 0, Constants.GAME_WIDTH, Constants.GAME_WIDTH, outsidePaint);
        boolean[][] placesToMove = null;
        if (selectedPiece != null) {
            placesToMove = selectedPiece.canMove(matrix);
        }
        if(playerTurn%2==1) {
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
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 1) {
                        canvas.drawRect((7 - j) * cellWidth, (7 - i) * cellWidth, ((7 - j) * cellWidth) + cellWidth,
                                ((7 - i) * cellWidth) + cellWidth, blackPaint);
                    } else {
                        canvas.drawRect((7 - j) * cellWidth, (7 - i) * cellWidth, ((7 - j) * cellWidth) + cellWidth,
                                ((7 - i) * cellWidth) + cellWidth, whitePaint);
                    }
                    if (placesToMove != null && placesToMove[i][j]) {
                        if (matrix[i][j] == null) {
                            canvas.drawCircle((7 - j) * cellWidth + cellWidth / 2, (7 - i) * cellWidth + cellWidth / 2, cellWidth / 5, bluePaint);
                        } else {
                            canvas.drawRect((7 - j) * cellWidth, (7 - i) * cellWidth, ((7 - j) * cellWidth) + cellWidth,
                                    ((7 - i) * cellWidth) + cellWidth, redPaint);
                        }
                    }
                }
            }
        }

        if(selectedPiece!=null) {
            int selectedRow = selectedPiece.getRow();
            int selectedCol = selectedPiece.getCol();
            if(playerTurn%2 == 1) {
                canvas.drawRect(selectedCol * cellWidth, selectedRow * cellWidth, (selectedCol * cellWidth) + cellWidth,
                        (selectedRow * cellWidth) + cellWidth, turquoisePaint);
            } else {
                canvas.drawRect((7 - selectedCol) * cellWidth, (7 - selectedRow) * cellWidth, ((7 - selectedCol) * cellWidth) + cellWidth,
                        ((7 - selectedRow) * cellWidth) + cellWidth, turquoisePaint);
            }
        }

        // Draws pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].draw(canvas, playerTurn);
                }
            }
        }

    }

    public Piece[][] getMatrix() {
        return matrix;
    }

    public void selectPlace(int row, int col) {
        if(playerTurn%2==0) {
            row = 7 - row;
            col = 7 - col;
        }
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
        } else if(pieceIsSelected) {
            if(matrix[row][col]!=null&&matrix[row][col].getColor()==selectedPiece.getColor()) {
                selectedPiece = matrix[row][col];
            }
            else if(selectedPiece.canMove(getMatrix())[row][col]) {
                int currentRow = selectedPiece.getRow();
                int currentCol = selectedPiece.getCol();
                Piece pieceInPlace = matrix[row][col];
                matrix[row][col] = selectedPiece;
                matrix[selectedPiece.getRow()][selectedPiece.getCol()] = null;

                selectedPiece.move(row, col);
                boolean hasMoved = true;
                if(playerTurn%2==1) {
                    if(checkForBlackChess()) {
                        selectedPiece.move(currentRow, currentCol);
                        if(pieceInPlace!=null) {
                            matrix[row][col] = pieceInPlace;
                        }
                        matrix[currentRow][currentCol] = selectedPiece;
                        hasMoved = false;
                    }
                } else {
                    if(checkForWhiteChess()) {
                        selectedPiece.move(currentRow, currentCol);
                        if(pieceInPlace!=null) {
                            matrix[row][col] = pieceInPlace;
                        }
                        matrix[currentRow][currentCol] = selectedPiece;
                        hasMoved = false;
                    }
                }

                if(playerTurn%2==1) {
                    if(checkForWhiteChess()) {
                        blackIsInCheck = true;
                        Toast.makeText(context, "Black is in chess", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(checkForBlackChess()) {
                        whiteIsInCheck = true;
                        Toast.makeText(context, "White is in chess", Toast.LENGTH_SHORT).show();
                    }
                }
                if(hasMoved) {
                    whiteIsInCheck = false;
                    blackIsInCheck = false;
                    selectedPiece = null;
                    pieceIsSelected = false;
                    playerTurn++;
                }

            }
        }

        this.gamePanel.draw();
    }

    public void getMatrix(Piece[][] matrix) {
        this.matrix = matrix;
    }

    private boolean checkForWhiteChess() {
        for (int i = 0; i <  7; i++) {
            for (int j = 0; j < 7; j++) {
                if(matrix[i][j] != null) {
                    Piece piece = matrix[i][j];
                    if(piece.getColor() == com.kamenov.martin.chess.Color.White) {
                        boolean[][] placesPieceCanMove = piece.canMove(matrix);
                        if(placesPieceCanMove[blackKing.getRow()][blackKing.getCol()]) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean checkForBlackChess() {
        for (int i = 0; i <  7; i++) {
            for (int j = 0; j < 7; j++) {
                if(matrix[i][j] != null) {
                    Piece piece = matrix[i][j];
                    if(piece.getColor() == com.kamenov.martin.chess.Color.Black) {
                        boolean[][] placesPieceCanMove = piece.canMove(matrix);
                        if(placesPieceCanMove[whiteKing.getRow()][whiteKing.getCol()]) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


}
