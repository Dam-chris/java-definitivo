
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;


public class GameWindow {
    private JFrame gameWindow;
    
    public Clock blackClock;
    public Clock whiteClock;
    
    private Timer timer;
    
    private Board board;
    private JLabel wTime;
    private JLabel bTime;
    private JLabel w;
    private JLabel b;
    
    
    
    public GameWindow(String blackName, String whiteName, int hh, 
            int mm, int ss) {
        
        blackClock = new Clock(hh, ss, mm);
        whiteClock = new Clock(hh, ss, mm);
        
        gameWindow = new JFrame("Chess");
        gameWindow.getContentPane().setBackground(Color.DARK_GRAY);
        gameWindow.setBackground(Color.DARK_GRAY);
        gameWindow.setResizable(false);
        

        try {
            Image whiteImg = ImageIO.read(getClass().getResource("wp.png"));
            gameWindow.setIconImage(whiteImg);
        } catch (Exception e) {
            System.out.println("Game file wp.png not found");
        }

        gameWindow.setLocation(100, 100);
        gameWindow.getContentPane().setLayout(null);
       
        // Game Data window
        JPanel gameData = gameDataPanel(blackName, whiteName, hh, mm, ss);
        gameData.setSize(new Dimension(432, 111));
        gameWindow.getContentPane().add(gameData);
        
        this.board = new Board(this);
        board.setBounds(10, 10, 800, 800);
        
        gameWindow.getContentPane().add(board);
        
        final JButton quit = new JButton("Quit");
        quit.setBorder(new LineBorder(Color.WHITE));
        quit.setForeground(Color.LIGHT_GRAY);
        quit.setFont(new Font("Dialog", Font.BOLD, 20));
        quit.setBackground(Color.GRAY);
        quit.setBounds(967, 507, 207, 52);
        gameWindow.getContentPane().add(quit);
        
        final JButton nGame = new JButton("New game");
        nGame.setBorder(new LineBorder(Color.WHITE));
        nGame.setForeground(Color.LIGHT_GRAY);
        nGame.setFont(new Font("Dialog", Font.BOLD, 20));
        nGame.setBackground(Color.GRAY);
        nGame.setBounds(967, 386, 207, 52);
        gameWindow.getContentPane().add(nGame);
        
        final JButton instr = new JButton("How to play");
        instr.setBorder(new LineBorder(Color.WHITE));
        instr.setForeground(Color.LIGHT_GRAY);
        instr.setFont(new Font("Dialog", Font.BOLD, 20));
        instr.setBackground(Color.GRAY);
        instr.setBounds(967, 262, 207, 52);
        gameWindow.getContentPane().add(instr);
        
        instr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(gameWindow,
                        "Move the chess pieces on the board by clicking\n"
                        + "and dragging. The game will watch out for illegal\n"
                        + "moves. You can win either by your opponent running\n"
                        + "out of time or by checkmating your opponent.\n"
                        + "\nGood luck, hope you enjoy the game!",
                        "How to play",
                        JOptionPane.PLAIN_MESSAGE);
            }
          });
        
        nGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        gameWindow,
                        "Are you sure you want to begin a new game?",
                        "Confirm new game", JOptionPane.YES_NO_OPTION);
                
                if (n == JOptionPane.YES_OPTION) {
                    SwingUtilities.invokeLater(new StartMenu());
                    gameWindow.dispose();
                }
            }
          });
        
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        gameWindow,
                        "Are you sure you want to quit?",
                        "Confirm quit", JOptionPane.YES_NO_OPTION);
                
                if (n == JOptionPane.YES_OPTION) {
                    if (timer != null) timer.stop();
                    gameWindow.dispose();
                }
            }
          });
        
        gameWindow.setMinimumSize(new Dimension(1300, 850));
        
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
// Helper function to create data panel
    
    private JPanel gameDataPanel(final String bn, final String wn, 
            final int hh, final int mm, final int ss) {
        
        JPanel gameData = new JPanel();
        gameData.setBorder(new LineBorder(Color.WHITE));
        gameData.setBackground(Color.GRAY);
        gameData.setLocation(843, 12);
        gameData.setLayout(null);
        
        
        // PLAYER NAMES
        
        w = new JLabel(wn);
        w.setForeground(Color.LIGHT_GRAY);
        w.setLocation(0, 0);
        w.setFont(new Font("Dialog", Font.BOLD, 30));
        
        w.setHorizontalAlignment(JLabel.CENTER);
        w.setVerticalAlignment(JLabel.CENTER);
        
        w.setSize(new Dimension(216, 37));
        
        gameData.add(w);
        
        // CLOCKS
        
        bTime = new JLabel(blackClock.getTime());
        bTime.setForeground(Color.LIGHT_GRAY);
        bTime.setBounds(216, 62, 216, 37);
        bTime.setFont(new Font("Dialog", Font.BOLD, 40));
        
        bTime.setHorizontalAlignment(JLabel.CENTER);
        bTime.setVerticalAlignment(JLabel.CENTER);
        
        if (!(hh == 0 && mm == 0 && ss == 0)) {
            timer = new Timer(1000, null);
            timer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean turn = board.getTurn();
                    
                    if (turn) {
                        whiteClock.decr();
                        wTime.setText(whiteClock.getTime());
                        
                        if (whiteClock.outOfTime()) {
                            timer.stop();
                            int n = JOptionPane.showConfirmDialog(
                                    gameWindow,
                                    bn + " wins by time! Play a new game? \n" +
                                    "Choosing \"No\" quits the game.",
                                    bn + " wins!",
                                    JOptionPane.YES_NO_OPTION);
                            
                            if (n == JOptionPane.YES_OPTION) {
                                new GameWindow(bn, wn, hh, mm, ss);
                                gameWindow.dispose();
                            } else gameWindow.dispose();
                        }
                    } else {
                        blackClock.decr();
                        bTime.setText(blackClock.getTime());
                        
                        if (blackClock.outOfTime()) {
                            timer.stop();
                            int n = JOptionPane.showConfirmDialog(
                                    gameWindow,
                                    wn + " wins by time! Play a new game? \n" +
                                    "Choosing \"No\" quits the game.",
                                    wn + " wins!",
                                    JOptionPane.YES_NO_OPTION);
                            
                            if (n == JOptionPane.YES_OPTION) {
                                new GameWindow(bn, wn, hh, mm, ss);
                                gameWindow.dispose();
                            } else gameWindow.dispose();
                        }
                    }
                }
            });
            timer.start();
        } else {
            wTime.setText("Untimed game");
            bTime.setText("Untimed game");
        }
        b = new JLabel(bn);
        b.setForeground(Color.LIGHT_GRAY);
        b.setLocation(216, 0);
        b.setFont(new Font("Dialog", Font.BOLD, 30));
        b.setHorizontalAlignment(JLabel.CENTER);
        b.setVerticalAlignment(JLabel.CENTER);
        b.setSize(new Dimension(216, 37));
        gameData.add(b);
        wTime = new JLabel(whiteClock.getTime());
        wTime.setForeground(Color.LIGHT_GRAY);
        wTime.setBounds(0, 62, 216, 37);
        wTime.setFont(new Font("Dialog", Font.BOLD, 40));
        wTime.setHorizontalAlignment(JLabel.CENTER);
        wTime.setVerticalAlignment(JLabel.CENTER);
        
        gameData.add(wTime);
        gameData.add(bTime);
        
        gameData.setPreferredSize(gameData.getMinimumSize());
        
        return gameData;
    }
    
    
    
    public void checkmateOccurred (int c) {
        if (c == 0) {
            if (timer != null) timer.stop();
            int n = JOptionPane.showConfirmDialog(
                    gameWindow,
                    "White wins by checkmate! Set up a new game? \n" +
                    "Choosing \"No\" lets you look at the final situation.",
                    "White wins!",
                    JOptionPane.YES_NO_OPTION);
            
            if (n == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(new StartMenu());
                gameWindow.dispose();
            }
        } else {
            if (timer != null) timer.stop();
            int n = JOptionPane.showConfirmDialog(
                    gameWindow,
                    "Black wins by checkmate! Set up a new game? \n" +
                    "Choosing \"No\" lets you look at the final situation.",
                    "Black wins!",
                    JOptionPane.YES_NO_OPTION);
            
            if (n == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(new StartMenu());
                gameWindow.dispose();
            }
        }
    }
}
