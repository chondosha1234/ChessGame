package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
