package com.kamenov.martin.chess.game;

import com.kamenov.martin.chess.game.pieces.Piece;

import java.util.ArrayList;

/**
 * Created by Martin on 24.11.2018 г..
 */

public class MovementService {
    private static MovementService instance;
    private ArrayList<Move> movements;

    private MovementService() {
        movements = new ArrayList<>();
    }

    public static MovementService getInstance() {
        if(instance == null) {
            instance = new MovementService();
        }

        return instance;
    }

    public void addMove(Piece piece, int currentRow, int currentCol, int previousRow, int previousCol) {
        Move newMove = new Move(piece, currentRow, currentCol, previousRow, previousCol);
        this.movements.add(newMove);
    }

    public Move getLastMove() {
        if(movements.size() == 0) {
            return null;
        }

        return movements.get(movements.size() - 1);

    }

    public Move popLastMove() {
        if(movements.size() == 0) {
            return null;
        }

        Move lastMove = movements.get(movements.size() - 1);
        movements.remove(movements.size() - 1);
        return lastMove;
    }
}
