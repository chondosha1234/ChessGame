package pieces;

import gameboard.Board;
import gameboard.Spot;

public class Rook extends Piece {

    private String imagePath;

    public Rook(boolean white) {
        super(white);
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/rook_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/rook_black.png";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        // todo: check to make sure its not same spot?

        // can't move to spot with piece of same color
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        // valid move will be x*y == 0 because it should be 0 difference in 1 direction
        // same team pieces can block on the path and first enemy piece will die and block
        if (x * y == 0) {
            // checking to see if there are blocking pieces along y-axis
            if (x == 0) {
                int startY = start.getY();
                int endY = end.getY();
                int startX = start.getX();

                // checking only direction of movement
                if (startY < endY) {
                    for (int i = startY + 1; i < endY; i++) {
                        if (board.getBox(startX, i).getPiece() != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = startY - 1; i > endY; i--) {
                        if (board.getBox(startX, i).getPiece() != null) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                //checking for blocking along x-axis
                int startX = start.getX();
                int endX = end.getX();
                int startY = start.getY();

                if (startX < endX) {
                    for (int i = startX + 1; i < endX; i++) {
                        if (board.getBox(i, startY).getPiece() != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = startX - 1; i > endX; i--) {
                        if (board.getBox(i, startY).getPiece() != null) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }

        // didn't meet valid move criteria
        return false;
    }
}
