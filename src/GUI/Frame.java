package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class Frame {
    public static void start(ArrayList<String> names) {
        JFrame frame = new JFrame("GAME PACMAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(660, 660);
        Menu menu = new Menu(names);
        //frame.addMouseListener(menu);
        frame.getContentPane().add(menu);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
