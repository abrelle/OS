package GUI;


import VM.VirtualMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JPanel implements ActionListener {


    private static final int WINDOW_WIDTH = 660;
    private static final int WINDOW_HEIGHT = 660;
    GridBagConstraints gbc = new GridBagConstraints();
    private JComboBox nameList;

    Menu(ArrayList<String> programNames) {

        super();
        this.setBorder(BorderFactory.createLineBorder(new Color(168, 181, 181), 15));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(193, 216, 217));
        JLabel jlabel = new JLabel("Choose program");
        jlabel.setFont(new Font("Verdana", 1, 25));
        jlabel.setForeground(new Color(93, 112, 112));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(jlabel, gbc);


        gbc.insets = new Insets(10, 10, 10, 10);
        nameList = new JComboBox<>(programNames.toArray());
        nameList.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 10;
        nameList.addActionListener(this);
        this.add(nameList, gbc);

    }

    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }


//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
////        if(e.getSource() == programOne)
////        {
////            game.changeStatus(IN_PROGRESS);
////        }
////        if(e.getSource() == programTwo)
////        {
////            try {
////                new GameDataReader(game);
////            } catch (IOException | ClassNotFoundException ex) {
////                ex.printStackTrace();
////            }
////            if(!(game.getModel().getEnemy(0).getStatus()))
////                game.getModel().deactivateEnemies();
////            game.changeStatus(IN_PROGRESS);
////        }
////        if(e.getSource() == quitButton)
////        {
////            System.exit(0);
////        }
//
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) nameList.getSelectedItem();
        new VirtualMachine(s);
    }
}

