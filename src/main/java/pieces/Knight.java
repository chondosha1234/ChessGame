package pieces;

import gameboard.Board;
import gameboard.Spot;

public class Knight extends Piece {

    private final String imagePath;

    public Knight(boolean white) {
        super(white);
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/knight_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/knight_black.png";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // can't move to spot with piece of same color
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        // knight move will always be a difference of 2 in one direction and 1 in the other direction
        // multiplying the difference of abs(2) and abs(1) will be 2 for a valid move
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        boolean isValidMove = (x * y == 2);

        if (isValidMove) {
            this.spot = end;
            return true;
        } else {
            return false;
        }
    }
}
