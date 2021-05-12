
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;


public class ChessGame {
    private JFrame gameWindow;
    
    public Clock blackClock;
    public Clock whiteClock;
    
    private Timer timer;
    
    private Board board;
    private JLabel wTime;
    private JLabel bTime;
    private JLabel white;
    private JLabel black;
    
    
    
    public ChessGame(String blackName, String whiteName, int hh, 
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
            System.out.println(e);
        }

        gameWindow.setLocation(100, 100);
        gameWindow.getContentPane().setLayout(null);
       
        // RunChess Data window
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
                        "Jon es un Tterrorista",
                        "How to play // como jugar",
                        JOptionPane.PLAIN_MESSAGE);
            }
          });
        
        nGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        gameWindow,
                        "Are you sure you want to begin a new game? \n"
                        + "// estas seguro de que quieres empezar un nuevo juego",
                        "Confirm // Confima ", JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    SwingUtilities.invokeLater(new StartMenu());
                    gameWindow.dispose();
                }
            }
          });
        
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        gameWindow,
                        "Are you sure you want to quit? \n"
                        + "estas seguro de que quieres salir?",
                        "Confirm // Confirma", JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
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
        
        white = new JLabel(wn);
        white.setForeground(Color.LIGHT_GRAY);
        white.setLocation(0, 0);
        white.setFont(new Font("Dialog", Font.BOLD, 30));
        
        white.setHorizontalAlignment(JLabel.CENTER);
        white.setVerticalAlignment(JLabel.CENTER);
        
        white.setSize(new Dimension(216, 37));
        
        gameData.add(white);
        
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
                            int option = JOptionPane.showConfirmDialog(
                                    gameWindow,
                                    bn + " wins by time! Play a new game? \n "
                                    		+ "// "+bn+" gana por timepo, quieres juegar de nuevo?",
                                    bn + " wins! // "+bn+" gana!",
                                    JOptionPane.YES_NO_OPTION);
                            
                            if (option == JOptionPane.YES_OPTION) {
                                new ChessGame(bn, wn, hh, mm, ss);
                                gameWindow.dispose();
                            } else gameWindow.dispose();
                        }
                    } else {
                        blackClock.decr();
                        bTime.setText(blackClock.getTime());
                        
                        if (blackClock.outOfTime()) {
                            timer.stop();
                            int option = JOptionPane.showConfirmDialog(
                                    gameWindow,
                                    wn + " wins by time! Play a new game? \n"
                                    		+ "// "+wn+" gana por timepo, quieres juegar de nuevo?",
                                    wn + " wins! // "+wn+" gana!",
                                    JOptionPane.YES_NO_OPTION);
                            
                            if (option == JOptionPane.YES_OPTION) {
                                new ChessGame(bn, wn, hh, mm, ss);
                                gameWindow.dispose();
                            } else gameWindow.dispose();
                        }
                    }
                }
            });
            timer.start();
        } 
        black = new JLabel(bn);
        black.setForeground(Color.LIGHT_GRAY);
        black.setLocation(216, 0);
        black.setFont(new Font("Dialog", Font.BOLD, 30));
        black.setHorizontalAlignment(JLabel.CENTER);
        black.setVerticalAlignment(JLabel.CENTER);
        black.setSize(new Dimension(216, 37));
        gameData.add(black);
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
    
    
    
    public void checkmateOccurred (int check) {
        if (check == 0) {
            if (timer != null) timer.stop();
            int option = JOptionPane.showConfirmDialog(
                    gameWindow,
                    "White wins by checkmate! Set up a new game? \n" +
                    "Blancas gana por jaque mate, quieres empezar un nuevo juego?",
                    "White wins! // Blancas ganan!!",
                    JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(new StartMenu());
                gameWindow.dispose();
            }
        } else {
            if (timer != null) timer.stop();
            int option = JOptionPane.showConfirmDialog(
                    gameWindow,
                    "Black wins by checkmate! Set up a new game? \n" +
                            "Negras gana por jaque mate, quieres empezar un nuevo juego?",
                            "Black wins! // Negras ganan!!",
                    JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                SwingUtilities.invokeLater(new StartMenu());
                gameWindow.dispose();
            }
        }
    }
}
