package carlos.Application;

import carlos.wirtual_life.World;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Window window ;
    public Board(Window window){
        this.window = window ;
    }



    @Override
    protected void paintComponent(Graphics g) {
        window.printBoard();

    }
}
