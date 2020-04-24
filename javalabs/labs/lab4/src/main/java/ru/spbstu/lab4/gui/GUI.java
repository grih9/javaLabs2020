package ru.spbstu.lab4.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
   private final JButton startButton = new JButton("START");
   private final JTextField input = new JTextField("", 15);
   private final JLabel label = new JLabel("Input:");
   private final JRadioButton radio1 = new JRadioButton("Select this");
   private final JRadioButton radio2 = new JRadioButton("Select that");
   private final JCheckBox check = new JCheckBox("Check", false);

   public GUI() {
       super("Warehouse Start");
       this.setBounds(0, 0, 1450, 800);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       Container container = this.getContentPane();
       container.setLayout(new GridLayout(6, 2, 2, 2));
       container.add(label);
       container.add(input);

       startButton.setBounds(100, 100, 1000, 1000);
       ButtonGroup group = new ButtonGroup();
       group.add(radio1);
       group.add(radio2);
       container.add(radio1);
       radio1.setSelected(true);
       container.add(radio2);
       container.add(check);
       startButton.addActionListener(new ButtonEventListener());
       container.add(startButton);
   }

   class ButtonEventListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           String message = "";
           message += "Button was pressed\n";
           message += "Text is " + input.getText() + "\n";
           message += (radio1.isSelected() ? "Radio #1" : "Radio #2") + " is selected!\n";
           message += "Checkbox is " + (check.isSelected() ? "checked" : "unchecked");
           JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
       }
    }

}
