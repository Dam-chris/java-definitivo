
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class StartMenu implements Runnable {
    /**
     * @wbp.parser.entryPoint
     */
    public void run() {
        final JFrame startWindow = new JFrame("Chess");
        startWindow.getContentPane().setBackground(Color.DARK_GRAY);
        startWindow.setBackground(Color.DARK_GRAY);
        // Set window properties
        startWindow.setLocation(300,100);
        startWindow.setResizable(false);
        startWindow.setSize(320, 400);
        
        Box components = Box.createVerticalBox();
        components.setBackground(Color.DARK_GRAY);
        startWindow.getContentPane().add(components);
        
        // Game title
        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        components.add(titlePanel);
        final JLabel titleLabel = new JLabel("Chess");
        titlePanel.add(titleLabel);
        
        // Black player selections
        final JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.DARK_GRAY);
        components.add(blackPanel, BorderLayout.EAST);
        final JLabel blackPiece = new JLabel();
        try {
            Image blackImg = ImageIO.read(getClass().getResource("bp.png"));
            blackPiece.setIcon(new ImageIcon(blackImg));
            blackPanel.add(blackPiece);
        } catch (Exception e) {
            System.out.println("Required game file bp.png missing");
        }
        
        
        
        final JTextField blackInput = new JTextField("Black", 10);
        blackInput.setPreferredSize(new Dimension(180, 30));
        blackInput.setMinimumSize(new Dimension(20, 21));
        blackPanel.add(blackInput);
        
        // White player selections
        final JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.DARK_GRAY);
        components.add(whitePanel);
        final JLabel whitePiece = new JLabel();
        
        try {
            Image whiteImg = ImageIO.read(getClass().getResource("wp.png"));
            whitePiece.setIcon(new ImageIcon(whiteImg));
            whitePanel.add(whitePiece);
            startWindow.setIconImage(whiteImg);
        }  catch (Exception e) {
            System.out.println("Required game file wp.png missing");
        }
        
        
        final JTextField whiteInput = new JTextField("White", 10);
        whiteInput.setPreferredSize(new Dimension(180, 30));
        whitePanel.add(whiteInput);
        
        // Timer settings
        final String[] minSecInts = new String[60];
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                minSecInts[i] = "0" + Integer.toString(i);
            } else {
                minSecInts[i] = Integer.toString(i);
            }
        }
        
       final JComboBox<String> seconds = new JComboBox<String>(minSecInts);
        final JComboBox<String> minutes = new JComboBox<String>(minSecInts);
        final JComboBox<String> hours = 
                new JComboBox<String>(new String[] {"0","1","2","3"});
        
        Box timerSettings = Box.createHorizontalBox();
        
        hours.setMaximumSize(hours.getPreferredSize());
        minutes.setMaximumSize(minutes.getPreferredSize());
        seconds.setMaximumSize(minutes.getPreferredSize());
        
        timerSettings.add(hours);
        timerSettings.add(Box.createHorizontalStrut(10));
        timerSettings.add(seconds);
        timerSettings.add(Box.createHorizontalStrut(10));
        timerSettings.add(minutes);
        
        timerSettings.add(Box.createVerticalGlue());
        
        components.add(timerSettings);
        
        // Buttons
        Box buttons = Box.createHorizontalBox();
        final JButton quit = new JButton("Quit");
        quit.setFont(new Font("Dialog", Font.BOLD, 15));
        quit.setBorder(new LineBorder(Color.WHITE));
        quit.setBackground(Color.GRAY);
        
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              startWindow.dispose();
            }
          });
        
        final JButton instr = new JButton("Instructions");
        instr.setFont(new Font("Dialog", Font.BOLD, 15));
        instr.setBorder(new LineBorder(Color.WHITE));
        instr.setBackground(Color.GRAY);
        
        instr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(startWindow,
                        "To begin a new game, input player names\n" +
                        "next to the pieces. Set the clocks and\n" +
                        "click \"Start\". Setting the timer to all\n" +
                        "zeroes begins a new untimed game.",
                        "How to play",
                        JOptionPane.PLAIN_MESSAGE);
            }
          });
        
       final JButton start = new JButton("Start");
        
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bn = blackInput.getText();
                String wn = whiteInput.getText();
                int hh = Integer.parseInt((String) hours.getSelectedItem());
                int mm = Integer.parseInt((String) minutes.getSelectedItem());
                int ss = Integer.parseInt((String) seconds.getSelectedItem());
                
                new GameWindow(bn, wn, hh, mm, ss);
                startWindow.dispose();
            }
          });
        
        /*buttons.add(start);*/
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(instr);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(quit);
        components.add(buttons);
        
        Component space = Box.createGlue();
        components.add(space);

        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setVisible(true);
    }
}
