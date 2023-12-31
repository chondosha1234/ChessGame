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
        boxes[1][0] = new Spot(1, 0, whiteKnight1);
        boxes[2][0] = new Spot(2, 0, whiteBishop1);
        boxes[3][0] = new Spot(3, 0, whiteQueen);
        boxes[4][0] = new Spot(4, 0, whiteKing);
        boxes[5][0] = new Spot(5, 0, whiteBishop2);
        boxes[6][0] = new Spot(6, 0, whiteKnight2);
        boxes[7][0] = new Spot(7, 0, whiteRook2);

        boxes[0][1] = new Spot(0, 1, whitePawn1);
        boxes[1][1] = new Spot(1, 1, whitePawn2);
        boxes[2][1] = new Spot(2, 1, whitePawn3);
        boxes[3][1] = new Spot(3, 1, whitePawn4);
        boxes[4][1] = new Spot(4, 1, whitePawn5);
        boxes[5][1] = new Spot(5, 1, whitePawn6);
        boxes[6][1] = new Spot(6, 1, whitePawn7);
        boxes[7][1] = new Spot(7, 1, whitePawn8);

        boxes[0][7] = new Spot(0, 7, blackRook1);
        boxes[1][7] = new Spot(1, 7, blackKnight1);
        boxes[2][7] = new Spot(2, 7, blackBishop1);
        boxes[3][7] = new Spot(3, 7, blackQueen);
        boxes[4][7] = new Spot(4, 7, blackKing);
        boxes[5][7] = new Spot(5, 7, blackBishop2);
        boxes[6][7] = new Spot(6, 7, blackKnight2);
        boxes[7][7] = new Spot(7, 7, blackRook2);

        boxes[0][6] = new Spot(0, 6, blackPawn1);
        boxes[1][6] = new Spot(1, 6, blackPawn2);
        boxes[2][6] = new Spot(2, 6, blackPawn3);
        boxes[3][6] = new Spot(3, 6, blackPawn4);
        boxes[4][6] = new Spot(4, 6, blackPawn5);
        boxes[5][6] = new Spot(5, 6, blackPawn6);
        boxes[6][6] = new Spot(6, 6, blackPawn7);
        boxes[7][6] = new Spot(7, 6, blackPawn8);

        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }
    }
}
