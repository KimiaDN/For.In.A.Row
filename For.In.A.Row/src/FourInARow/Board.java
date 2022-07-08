package FourInARow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.SwingConstants;

public class Board extends JFrame {

	private JPanel contentPane;
	private JPanel board ;
	
	
	private int width = 6 ;
	private int length = 7;
	private int turn = 1 ; 		        // turn = 1 --> human, turn = 0 --> computer
	public String playerName;
	
	private Player computer;           // object of player for computer
	private Player human;           // object of player for human
	private Thread AI;
	
	private JButton[][] buttons = new JButton[width][length];
	private Cells[][] cells = new Cells[width][length];
	JLabel player_bead ;
	JLabel turn_name ;
	
	Image human_I = new ImageIcon(this.getClass().getResource("/human.png")).getImage();
	Image computer_I = new ImageIcon(this.getClass().getResource("/computer.png")).getImage();
	ImageIcon human_B = new ImageIcon(human_I);
	ImageIcon computer_B = new ImageIcon(computer_I);

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
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
	public Board(String playerName) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		board = new JPanel();
		board.setBounds(12, 13, 673, 627);
		contentPane.add(board);
		board.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBounds(697, 13, 173, 627);
		contentPane.add(menu);
		menu.setLayout(null);
		
		this.playerName = playerName;
		computer = new Player();    // an object of player for compuer
		human = new Player();
		human.palyerName = playerName;
		
		
		JLabel lblNewLabel = new JLabel("Player");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(12, 13, 149, 44);
		menu.add(lblNewLabel);
		
		JLabel player_name = new JLabel(playerName);
		player_name.setFont(new Font("Tahoma", Font.ITALIC, 20));
		player_name.setHorizontalAlignment(SwingConstants.CENTER);
		player_name.setBounds(12, 97, 149, 44);
		menu.add(player_name);
		
		JLabel lblNewLabel_2 = new JLabel("Turn:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(12, 223, 149, 44);
		menu.add(lblNewLabel_2);
		
		player_bead = new JLabel("");
		player_bead.setHorizontalAlignment(SwingConstants.CENTER);
		player_bead.setBounds(37, 298, 107, 95);
		player_bead.setIcon(human_B);
		menu.add(player_bead);
		
	    turn_name = new JLabel(playerName);
		turn_name.setHorizontalAlignment(SwingConstants.CENTER);
		turn_name.setFont(new Font("Tahoma", Font.ITALIC, 20));
		turn_name.setBounds(12, 413, 149, 44);
		menu.add(turn_name);
		
		addButtons();
		addMoves();

	}
	
	public void addButtons() {
		
		board.setLayout(new GridLayout(width, length));
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0 ; j < length ; j++) {
								
				cells[i][j] = new Cells();
				cells[i][j].x = i;
				cells[i][j].y = j;
				
				buttons[i][j] = new JButton();
				buttons[i][j].setSize(50,50);
				buttons[i][j].setBackground(Color.lightGray);
				board.add(buttons[i][j]);
			}
		}
	}
	
	public void addMoves() {
		
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0 ; j < length ; j++) {
				
				buttons[i][j].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						Object click = e.getSource();        // get the button wich clicked
						for(int z = 0 ; z < width ; z++) {
							for(int q = 0; q < length ; q++) {
								
								if(buttons[z][q] == click) {   // find the clicked button
									
									addHumanMove(z, q);
									if(human.checkForWinner(cells, "x")) {
										JOptionPane.showMessageDialog(null, playerName + " win \n");
										Start_Menu start = new Start_Menu();
										dispose();     // bach to the menu
										start.setVisible(true);
									}
									if(human.checkForEqual(cells)) {
										JOptionPane.showMessageDialog(null,"Equal \n");
										Start_Menu start = new Start_Menu();
										dispose();     // bach to the menu
										start.setVisible(true);
									}
									
								}
							}
						}
					}
				});
			}
		}
	}
	
	public void addHumanMove(int x, int y){
		
		if(turn == 1) {
			for(int i = width - 1 ; i >= 0 ; i-- ) { // check all the buttons of that column
				if(cells[i][y].player_icon.equals(" ")) {
					// find the one wich is empty (biggest one)
					turn = 0;
					cells[i][y].player_icon = "x" ;       // that cell has an icon now 
					turn_name.setText("computer");
					player_bead.setIcon(computer_B);
					buttons[i][y].setIcon(human_B);   // add player's icon to that button
					break;
						
				}
			}
			threadOfAI();
		}
	}
	public void addAIMove() {
		
		int[] bestResult = new int[2];
		bestResult = computer.negaMax(cells, 4,-50000, 50000, 1);
		int AICol = bestResult[0] ;
		for(int i = width -1 ; i >= 0 ; i--) {
			if(cells[i][AICol].player_icon.equals(" ")) {
				turn = 1;
				cells[i][AICol].player_icon = "o" ;       // that cell has an icon now 
				turn_name.setText(playerName);
				player_bead.setIcon(human_B);
				buttons[i][AICol].setIcon(computer_B);   // add player's icon to that button
				break;
				
			}
		}
		if(computer.checkForWinner(cells, "o")) {
			JOptionPane.showMessageDialog(null,"computer" + " win \n");
			Start_Menu start = new Start_Menu();
			dispose();     // bach to the menu
			start.setVisible(true);
		}
		if(computer.checkForEqual(cells)) {
			JOptionPane.showMessageDialog(null,"Equal" + " win \n");
			Start_Menu start = new Start_Menu();
			dispose();     // bach to the menu
			start.setVisible(true);
		}
	}
	 public void threadOfAI() {
		  
		  AI = new Thread() {
			  
			  public void run() {
				  try {
					  
					  if(turn == 0) {
						  sleep(1000);        // wait for one second
						  addAIMove();
						  turn = 1;
					  }
				  }catch( Exception e) {
					  Thread.currentThread().interrupt();
				  }finally {
					  Thread.currentThread().interrupt();
				  }
			}
		};
		  AI.start();
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
