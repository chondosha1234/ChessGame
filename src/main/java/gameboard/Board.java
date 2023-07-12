package gameboard;

import pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * GameBoard.Board class represents the chess board
 */
public class Board {
    private Spot[][] boxes;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private King whiteKing;
    private King blackKing;

    public Board() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        this.resetBoard();
    }

    public Spot getBox(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            try {
                throw new Exception("Index out of bound");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return boxes[x][y];
    }

    public List<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public King getWhiteKing() { return this.whiteKing; }

    public King getBlackKing() { return this.blackKing; }

    public void resetBoard() {

        this.whiteKing = new King(true);
        this.blackKing = new King(false);
        //Spot whiteKingSpot = new Spot(0, 4, whiteKing);
        //Spot blackKingSpot = new Spot(7, 4, blackKing);

        Rook whiteRook1 = new Rook(true);
        Rook whiteRook2 = new Rook(true);
        Knight whiteKnight1 = new Knight(true);
        Knight whiteKnight2 = new Knight(true);
        Bishop whiteBishop1 = new Bishop(true);
        Bishop whiteBishop2 = new Bishop(true);
        Queen whiteQueen = new Queen(true);

        Pawn whitePawn1 = new Pawn(true);
        Pawn whitePawn2 = new Pawn(true);
        Pawn whitePawn3 = new Pawn(true);
        Pawn whitePawn4 = new Pawn(true);
        Pawn whitePawn5 = new Pawn(true);
        Pawn whitePawn6 = new Pawn(true);
        Pawn whitePawn7 = new Pawn(true);
        Pawn whitePawn8 = new Pawn(true);

        whitePieces.add(whiteRook1);
        whitePieces.add(whiteRook2);
        whitePieces.add(whiteKnight1);
        whitePieces.add(whiteKnight2);
        whitePieces.add(whiteBishop1);
        whitePieces.add(whiteBishop2);
        whitePieces.add(whiteQueen);
        whitePieces.add(whiteKing);
        whitePieces.add(whitePawn1);
        whitePieces.add(whitePawn2);
        whitePieces.add(whitePawn3);
        whitePieces.add(whitePawn4);
        whitePieces.add(whitePawn5);
        whitePieces.add(whitePawn6);
        whitePieces.add(whitePawn7);
        whitePieces.add(whitePawn8);

        Rook blackRook1 = new Rook(false);
        Rook blackRook2 = new Rook(false);
        Knight blackKnight1 = new Knight(false);
        Knight blackKnight2 = new Knight(false);
        Bishop blackBishop1 = new Bishop(false);
        Bishop blackBishop2 = new Bishop(false);
        Queen blackQueen = new Queen(false);

        Pawn blackPawn1 = new Pawn(false);
        Pawn blackPawn2 = new Pawn(false);
        Pawn blackPawn3 = new Pawn(false);
        Pawn blackPawn4 = new Pawn(false);
        Pawn blackPawn5 = new Pawn(false);
        Pawn blackPawn6 = new Pawn(false);
        Pawn blackPawn7 = new Pawn(false);
        Pawn blackPawn8 = new Pawn(false);

        blackPieces.add(blackRook1);
        blackPieces.add(blackRook2);
        blackPieces.add(blackKnight1);
        blackPieces.add(blackKnight2);
        blackPieces.add(blackBishop1);
        blackPieces.add(blackBishop2);
        blackPieces.add(blackQueen);
        blackPieces.add(blackKing);
        blackPieces.add(blackPawn1);
        blackPieces.add(blackPawn2);
        blackPieces.add(blackPawn3);
        blackPieces.add(blackPawn4);
        blackPieces.add(blackPawn5);
        blackPieces.add(blackPawn6);
        blackPieces.add(blackPawn7);
        blackPieces.add(blackPawn8);


        boxes = new Spot[8][8];
        boxes[0][0] = new Spot(0, 0, whiteRook1);
        boxes[0][1] = new Spot(0, 1, whiteKnight1);
        boxes[0][2] = new Spot(0, 2, whiteBishop1);
        boxes[0][3] = new Spot(0, 3, whiteQueen);
        boxes[0][4] = new Spot(0, 4, whiteKing);
        boxes[0][5] = new Spot(0, 5, whiteBishop2);
        boxes[0][6] = new Spot(0, 6, whiteKnight2);
        boxes[0][7] = new Spot(0, 7, whiteRook2);

        boxes[1][0] = new Spot(1, 0, whitePawn1);
        boxes[1][1] = new Spot(1, 1, whitePawn2);
        boxes[1][2] = new Spot(1, 2, whitePawn3);
        boxes[1][3] = new Spot(1, 3, whitePawn4);
        boxes[1][4] = new Spot(1, 4, whitePawn5);
        boxes[1][5] = new Spot(1, 5, whitePawn6);
        boxes[1][6] = new Spot(1, 6, whitePawn7);
        boxes[1][7] = new Spot(1, 7, whitePawn8);

        boxes[7][0] = new Spot(7, 0, blackRook1);
        boxes[7][1] = new Spot(7, 1, blackKnight1);
        boxes[7][2] = new Spot(7, 2, blackBishop1);
        boxes[7][3] = new Spot(7, 3, blackQueen);
        boxes[7][4] = new Spot(7, 4, blackKing);
        boxes[7][5] = new Spot(7, 5, blackBishop2);
        boxes[7][6] = new Spot(7, 6, blackKnight2);
        boxes[7][7] = new Spot(7, 7, blackRook2);

        boxes[6][0] = new Spot(6, 0, blackPawn1);
        boxes[6][1] = new Spot(6, 1, blackPawn2);
        boxes[6][2] = new Spot(6, 2, blackPawn3);
        boxes[6][3] = new Spot(6, 3, blackPawn4);
        boxes[6][4] = new Spot(6, 4, blackPawn5);
        boxes[6][5] = new Spot(6, 5, blackPawn6);
        boxes[6][6] = new Spot(6, 6, blackPawn7);
        boxes[6][7] = new Spot(6, 7, blackPawn8);

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
    }
}
