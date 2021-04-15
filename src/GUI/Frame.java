package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class Frame {
    private static boolean showMenu = false;
    public static void start(ArrayList<String> names) {
        JFrame frame = new JFrame("GAME PACMAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        if (showMenu) {
            Menu menu = new Menu(names);
            frame.getContentPane().add(menu);
        } else {
            VirtualMachineGUI vm = new VirtualMachineGUI();
            frame.getContentPane().add(vm);
        }
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
