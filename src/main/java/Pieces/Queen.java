package Pieces;

import GameBoard.Board;
import GameBoard.Spot;

public class Queen extends Piece {

    public Queen (boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Spot start, Spot end) {
        return true;
    }
}
