
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
<<<<<<< HEAD
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.GridLayout;
=======
>>>>>>> java

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
<<<<<<< HEAD
        startWindow.setSize(320, 450);
        startWindow.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
=======
        startWindow.setSize(320, 400);
>>>>>>> java
        
        Box components = Box.createVerticalBox();
        components.setBackground(Color.DARK_GRAY);
        startWindow.getContentPane().add(components);
        
<<<<<<< HEAD
        // RunChess title
=======
        // Game title
>>>>>>> java
        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        components.add(titlePanel);
        final JLabel titleLabel = new JLabel("Chess");
<<<<<<< HEAD
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
=======
>>>>>>> java
        titlePanel.add(titleLabel);
        
        // Black player selections
        final JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.DARK_GRAY);
        components.add(blackPanel, BorderLayout.EAST);
        final JLabel blackPiece = new JLabel();
<<<<<<< HEAD
        blackPiece.setBounds(new Rectangle(0, 0, 40, 40));
        blackPiece.setMaximumSize(new Dimension(40, 40));
        blackPiece.setMinimumSize(new Dimension(40, 40));
        blackPiece.setSize(new Dimension(40, 40));
=======
>>>>>>> java
        try {
            Image blackImg = ImageIO.read(getClass().getResource("bp.png"));
            blackPiece.setIcon(new ImageIcon(blackImg));
            blackPanel.add(blackPiece);
        } catch (Exception e) {
<<<<<<< HEAD
            System.out.println(e);
=======
            System.out.println("Required game file bp.png missing");
>>>>>>> java
        }
        
        
        
        final JTextField blackInput = new JTextField("Black", 10);
<<<<<<< HEAD
        blackInput.setFont(new Font("Dialog", Font.PLAIN, 15));
        blackInput.setPreferredSize(new Dimension(200, 40));
=======
        blackInput.setPreferredSize(new Dimension(180, 30));
>>>>>>> java
        blackInput.setMinimumSize(new Dimension(20, 21));
        blackPanel.add(blackInput);
        
        // White player selections
        final JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.DARK_GRAY);
        components.add(whitePanel);
        final JLabel whitePiece = new JLabel();
        
        try {
            Image whiteImg = ImageIO.read(getClass().getResource("wp.png"));
<<<<<<< HEAD
            whitePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
=======
>>>>>>> java
            whitePiece.setIcon(new ImageIcon(whiteImg));
            whitePanel.add(whitePiece);
            startWindow.setIconImage(whiteImg);
        }  catch (Exception e) {
<<<<<<< HEAD
            System.out.println(e);
=======
            System.out.println("Required game file wp.png missing");
>>>>>>> java
        }
        
        
        final JTextField whiteInput = new JTextField("White", 10);
<<<<<<< HEAD
        whiteInput.setFont(new Font("Dialog", Font.PLAIN, 15));
        whiteInput.setMaximumSize(new Dimension(180, 50));       
        whiteInput.setMinimumSize(new Dimension(80, 40));
        whiteInput.setPreferredSize(new Dimension(200, 40));
=======
        whiteInput.setPreferredSize(new Dimension(180, 30));
>>>>>>> java
        whitePanel.add(whiteInput);
        
        // Timer settings
        final String[] minSecInts = new String[60];
        for (int i = 0; i < 60; i++) {
<<<<<<< HEAD
            if (i < 10) 
            {
                minSecInts[i] = "0" + Integer.toString(i);
            } else 
            {
=======
            if (i < 10) {
                minSecInts[i] = "0" + Integer.toString(i);
            } else {
>>>>>>> java
                minSecInts[i] = Integer.toString(i);
            }
        }
        
<<<<<<< HEAD
        JComboBox<String> hours = new JComboBox<String>(new String[] {"0","1","2"});
      	JComboBox<String> minutes = new JComboBox<String>(minSecInts);
      	JComboBox<String> seconds = new JComboBox<String>(minSecInts);
      	
        
        Box timerSettings = Box.createHorizontalBox();
        
       // hours.setMaximumSize(hours.getPreferredSize());
        hours.setMaximumSize(new Dimension(50, 30));
        hours.setForeground(Color.LIGHT_GRAY);
        hours.setBackground(Color.GRAY);
        hours.setBorder(new LineBorder(Color.WHITE));
        minutes.setMaximumSize(new Dimension(50, 30));
        minutes.setForeground(Color.LIGHT_GRAY);
        minutes.setBackground(Color.GRAY);
        minutes.setBorder(new LineBorder(Color.WHITE));
        seconds.setMaximumSize(new Dimension(50, 30));
        seconds.setForeground(Color.LIGHT_GRAY);
        seconds.setBackground(Color.GRAY);
        seconds.setBorder(new LineBorder(Color.WHITE));
=======
       final JComboBox<String> seconds = new JComboBox<String>(minSecInts);
        final JComboBox<String> minutes = new JComboBox<String>(minSecInts);
        final JComboBox<String> hours = 
                new JComboBox<String>(new String[] {"0","1","2","3"});
        
        Box timerSettings = Box.createHorizontalBox();
        
        hours.setMaximumSize(hours.getPreferredSize());
        minutes.setMaximumSize(minutes.getPreferredSize());
        seconds.setMaximumSize(minutes.getPreferredSize());
>>>>>>> java
        
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
<<<<<<< HEAD
        quit.setMaximumSize(new Dimension(70, 30));
        quit.setForeground(Color.LIGHT_GRAY);
=======
>>>>>>> java
        quit.setFont(new Font("Dialog", Font.BOLD, 15));
        quit.setBorder(new LineBorder(Color.WHITE));
        quit.setBackground(Color.GRAY);
        
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              startWindow.dispose();
            }
          });
        
<<<<<<< HEAD
       final JButton start = new JButton("Start");
       start.setMaximumSize(new Dimension(70, 30));
       start.setBorder(new LineBorder(Color.WHITE));
       start.setFont(new Font("Dialog", Font.BOLD, 15));
       start.setForeground(Color.LIGHT_GRAY);
       start.setBackground(Color.GRAY);
=======
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
>>>>>>> java
        
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bn = blackInput.getText();
                String wn = whiteInput.getText();
<<<<<<< HEAD
            
                int hh = Integer.parseInt((String) hours.getSelectedItem());
                int mm = Integer.parseInt((String) minutes.getSelectedItem());
                int ss = Integer.parseInt((String) seconds.getSelectedItem());
                if (hh == 0 && mm == 0 && ss == 0  ) 
                {
                	 JOptionPane.showMessageDialog(startWindow,
                             "you must choose a time to play / Debes elegir un tiempo para poder jugar","warning / aviso",
                             JOptionPane.PLAIN_MESSAGE);
                 
				}else
				{
					   new ChessGame(bn, wn, hh, mm, ss);
		                startWindow.dispose();
				}
             
            }
          });
        
        buttons.add(start);
        buttons.add(Box.createHorizontalStrut(10));
=======
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
>>>>>>> java
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(quit);
        components.add(buttons);
        
        Component space = Box.createGlue();
        components.add(space);

        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startWindow.setVisible(true);
    }
}
