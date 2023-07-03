package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class King extends Piece {

    public King(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
