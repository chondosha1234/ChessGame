package game;

import ui.ChessGui;
import javax.swing.*;

public class ChessGame {

    public static void main(String[] args) {
        ChessGui chessGui = new ChessGui();
        chessGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chessGui.setTitle("Chess Game");
        chessGui.pack();
        chessGui.setVisible(true);
    }
}
