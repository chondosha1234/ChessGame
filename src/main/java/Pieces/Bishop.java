package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
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

        // must have difference in both x and y for a move.  must be an equal difference
        // 1 diagonal move is dx = 1, dy = 1,  2 diagonal spaces would be dx = 2,  dy = 2
        // x / y == 1

        return true;
    }
}
