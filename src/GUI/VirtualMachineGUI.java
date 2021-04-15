package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class VirtualMachineGUI extends JPanel implements ActionListener {

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 660;
    GridBagConstraints gbc = new GridBagConstraints();

    VirtualMachineGUI() {

        super();
        this.setBorder(BorderFactory.createLineBorder(new Color(

                31, 31, 31

        ), 15));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(45, 46, 46));
        JButton button;


        JLabel l1, l2, l3;
        l1 = new JLabel("Data segment", CENTER);
        l1.setFont(new Font("Verdana", Font.BOLD, 15));
        l1.setForeground(new Color(3, 92, 92));
        l2 = new JLabel("Code segment", CENTER);
        l2.setFont(new Font("Verdana", Font.BOLD, 15));
        l2.setForeground(new Color(3, 92, 92));
        l3 = new JLabel("Stack", CENTER);
        l3.setFont(new Font("Verdana", Font.BOLD, 15));
        l3.setForeground(new Color(3, 92, 92));


        gbc.ipady = 20;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        ;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(l1, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(l2, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(l3, gbc);

        // Fill model
        String labels[] = {"A", "B", "C", "D", "E", "F", "G", "B", "C", "D", "E", "F", "G", "B", "C", "D", "E", "F", "G", "B", "C", "D", "E", "F", "G"};
        final DefaultListModel model = new DefaultListModel();
        for (int i = 0, n = labels.length; i < n; i++) {
            model.addElement(labels[i]);
        }
        JList jlist = new JList(model);

        JTextArea textArea1 = new JTextArea(20, 100);
        textArea1.setBackground(new Color(18, 18, 18));
        JTextArea textArea2 = new JTextArea(20, 100);
        textArea2.setBackground(new Color(18, 18, 18));
        JTextArea textArea3 = new JTextArea(20, 100);
        textArea3.setBackground(new Color(18, 18, 18));

        JScrollPane scrollableTextArea1 = new JScrollPane(jlist);
        JScrollPane scrollableTextArea2 = new JScrollPane(textArea2);
        JScrollPane scrollableTextArea3 = new JScrollPane(textArea3);

        scrollableTextArea1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollableTextArea2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollableTextArea3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        button = new JButton("Button 1");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 250;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(scrollableTextArea1, gbc);

        button = new JButton("Button 2");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(scrollableTextArea2, gbc);

        button = new JButton("Button 3");
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(scrollableTextArea3, gbc);

        gbc.ipady = 10;

        JLabel l11, l22, l33;
        l11 = new JLabel("DS:");
        l11.setFont(new Font("Verdana", Font.BOLD, 15));
        l11.setForeground(new Color(3, 92, 92));
        l22 = new JLabel("CS:");
        l22.setFont(new Font("Verdana", Font.BOLD, 15));
        l22.setForeground(new Color(3, 92, 92));
        l33 = new JLabel("DS:");
        l33.setFont(new Font("Verdana", Font.BOLD, 15));
        l33.setForeground(new Color(3, 92, 92));

        button = new JButton("Button 4");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(l11, gbc);

        button = new JButton("Button 4");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(l22, gbc);

        button = new JButton("Button 4");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(l33, gbc);

        button = new JButton("5");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       //reset to default
        gbc.weighty = 1.0;   //request any extra vertical space
        gbc.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc.insets = new Insets(10, 0, 0, 0);  //top padding
        gbc.gridx = 1;       //aligned with button 2
        gbc.gridwidth = 2;   //2 columns wide
        gbc.gridy = 3;       //third row
        this.add(button, gbc);


    }

    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

//    public static void addComponentsToPane(Container pane) {
//
//        pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//
//        JButton button;
//        pane.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//
//        button = new JButton("Button 1");
//
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 15;
//        c.gridx = 0;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Button 2");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 1;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Button 3");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 2;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Long-Named Button 4");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 40;      //make this component tall
//        c.weightx = 0.0;
//        c.gridwidth = 3;
//        c.gridx = 0;
//        c.gridy = 1;
//        pane.add(button, c);
//
//        button = new JButton("5");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;       //reset to default
//        c.weighty = 1.0;   //request any extra vertical space
//        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//        c.insets = new Insets(10,0,0,0);  //top padding
//        c.gridx = 1;       //aligned with button 2
//        c.gridwidth = 2;   //2 columns wide
//        c.gridy = 2;       //third row
//        pane.add(button, c);
//    }
}
