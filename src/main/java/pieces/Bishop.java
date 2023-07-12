package pieces;

import gameboard.Board;
import gameboard.Spot;

public class Bishop extends Piece {

    private final String imagePath;

    public Bishop(boolean white) {
        super(white);
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/bishop_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/bishop_black.png";
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

        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        int x = Math.abs(startX - endX);
        int y = Math.abs(startY - endY);

        // must have difference in both x and y for a move.  must be an equal difference
        // 1 diagonal move is dx = 1, dy = 1,  2 diagonal spaces would be dx = 2,  dy = 2
        if (x == 0 || y == 0) {
            return false;
        }

        if (x / y == 1) {
            if (startX < endX && startY < endY) {
                int i = startX + 1;
                int j = startY + 1;
                for (; i < endX && j < endY; i++, j++) {
                    if (board.getBox(i, j).getPiece() != null) {
                        return false;
                    }
                }
            }
            if (startX < endX && startY > endY) {
                int i = startX + 1;
                int j = startY - 1;
                for (; i < endX && j > endY; i++, j--) {
                    if (board.getBox(i, j).getPiece() != null) {
                        return false;
                    }
                }
            }
            if (startX > endX && startY < endY) {
                int i = startX - 1;
                int j = startY + 1;
                for (; i > endX && j < endY; i--, j++) {
                    if (board.getBox(i, j).getPiece() != null) {
                        return false;
                    }
                }
            }
            if (startX > endX && startY > endY) {
                int i = startX - 1;
                int j = startY - 1;
                for (; i > endX && j > endY; i--, j--) {
                    if (board.getBox(i, j).getPiece() != null) {
                        return false;
                    }
                }
            }
            this.spot = end;
            return true;
        }

        // didn't meet valid move criteria
        return false;
    }
}
