package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Queen extends Piece {

    public Queen (boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        // combine rook and bishop logic for moves
        // x / y == 1  or x * y == 0 ?

        return true;
    }
}
