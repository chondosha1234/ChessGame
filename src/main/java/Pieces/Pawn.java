package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
