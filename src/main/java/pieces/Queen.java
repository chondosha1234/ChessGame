package pieces;

import gameboard.Board;
import gameboard.Spot;

public class Queen extends Piece {

    private String imagePath;

    public Queen (boolean white) {
        super(white);
        if (white) {
            this.imagePath = "src/main/resources/chess_pieces/queen_white.png";
        } else {
            this.imagePath = "src/main/resources/chess_pieces/queen_black.png";
        }
    }

    public String getImagePath() {
        return this.imagePath;
    }

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

        // diagonal line movement like bishop
        if (y != 0 && x / y == 1) {
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
            return true;
        }

        // horizontal and vertical line movement like rook
        if (x * y == 0) {
            // checking to see if there are blocking pieces along y-axis
            if (x == 0) {
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
            } else {
                //checking for blocking along x-axis
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
            }
            return true;
        }

        // didn't meet any valid move criteria
        return false;
    }
}
