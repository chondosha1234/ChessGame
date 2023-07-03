package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        // valid move will be x*y == 0 because it should be 0 difference in 1 direction
        // same team pieces can block on the path and first enemy piece will die and block
        return true;
    }
}
