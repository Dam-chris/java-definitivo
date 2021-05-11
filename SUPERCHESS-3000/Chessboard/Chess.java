import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Chess extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Board canvasBoard;
	private JLabel lblTimerBlack;
	private JLabel lblTimerWhite;
	private JButton btndraw;
	private JButton btn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chess frame = new Chess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chess() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Images//bn.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 865);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlDkShadow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//canvas 
		canvasBoard = new Board(this);
		canvasBoard.setBackground(Color.blue);
		contentPane.add(canvasBoard);
		
		lblTimerBlack = new JLabel("10:00");
		lblTimerBlack.setForeground(SystemColor.textHighlightText);
		lblTimerBlack.setBackground(SystemColor.textHighlightText);
		lblTimerBlack.setFont(new Font("Dialog", Font.BOLD, 50));
		lblTimerBlack.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimerBlack.setBounds(939, 115, 185, 78);
		contentPane.add(lblTimerBlack);
		
		lblTimerWhite = new JLabel("10:00");
		lblTimerWhite.setForeground(SystemColor.textHighlightText);
		lblTimerWhite.setBackground(SystemColor.textHighlightText);
		lblTimerWhite.setFont(new Font("Dialog", Font.BOLD, 50));
		lblTimerWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimerWhite.setBounds(939, 568, 185, 78);
		contentPane.add(lblTimerWhite);
		
		btndraw = new JButton("Offer Draw");
		btndraw.setFont(new Font("Dialog", Font.BOLD, 25));
		btndraw.setFocusPainted(false);
		btndraw.setRequestFocusEnabled(true);
		btndraw.setBorder(UIManager.getBorder("CheckBox.border"));
		btndraw.setForeground(SystemColor.textHighlightText);
		btndraw.setBackground(Color.GRAY);
		btndraw.setBounds(905, 284, 219, 60);
		contentPane.add(btndraw);
		
		btn = new JButton("Abandon");
		btn.setFont(new Font("Dialog", Font.BOLD, 25));
		btn.setRequestFocusEnabled(false);
		btn.setBorder(UIManager.getBorder("CheckBox.border"));
		btn.setForeground(SystemColor.textHighlightText);
		btn.setBackground(Color.GRAY);
		btn.setBounds(905, 427, 219, 60);
		contentPane.add(btn);
		
	}
}
