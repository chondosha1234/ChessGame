package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        // knight move will always be a difference of 2 in one direction and 1 in the other direction
        // multiplying the difference of abs(2) and abs(1) will be 2 for a valid move
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}
