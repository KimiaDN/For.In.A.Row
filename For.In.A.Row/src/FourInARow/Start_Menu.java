package FourInARow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start_Menu extends JFrame {

	private JPanel contentPane;
	private JTextField playerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start_Menu frame = new Start_Menu();
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
	public Start_Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Your Name :");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(249, 149, 296, 44);
		contentPane.add(lblNewLabel);
		
		playerName = new JTextField();
		playerName.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 20));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setBounds(289, 253, 227, 44);
		contentPane.add(playerName);
		playerName.setColumns(10);
		
		JButton btnNewButton = new JButton("Get Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Board board = new Board(playerName.getText());
				dispose();     // bach to the menu
				board.setVisible(true);
			}
		});
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(SystemColor.controlText);
		btnNewButton.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(337, 374, 116, 44);
		contentPane.add(btnNewButton);
	}
}
