package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class student extends JFrame {

	private JPanel student;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student frame = new student();
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
	Connection con = null;
	private JTextField username;
	private JPasswordField password;
	public student() {
		try 
		{
			String url = "jdbc:mysql://localhost:3306/PRATYUSH?"+"autoReconnect=true&useSSL=false";
			String uName = "root";
			String pass="12345678";
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,uName,pass);
			
			JOptionPane.showMessageDialog(null, "successful");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		student = new JPanel();
		student.setBackground(new Color(255, 255, 0));
		student.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(student);
		student.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME TO STUDENT LOGIN");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(51, 22, 333, 24);
		student.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 24));
		lblNewLabel.setBounds(51, 70, 127, 36);
		student.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 25));
		lblNewLabel_1.setBounds(51, 147, 127, 36);
		student.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(203, 70, 116, 28);
		student.add(username);
		
		password = new JPasswordField();
		password.setEchoChar('#');
		password.setBounds(203, 153, 116, 27);
		student.add(password);
		
		JButton login = new JButton("login");
		login.setForeground(new Color(0, 0, 255));
		login.setBackground(new Color(154, 205, 50));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String userName = username.getText();
					String pass = password.getText();
					
					
					String query = "select book from library where username = ? AND password = ?";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, username.getText());
					pstmt.setString(2, password.getText());
					
					ResultSet rs = pstmt.executeQuery();
					
					
					int count = 0;
					
					while(rs.next()) 
					{
						count = count+1;
					}
					
					
					if(count == 1) 
					{
						JOptionPane.showMessageDialog(null, "Correct");
						//frame.dispose();
						Books obj = new Books();
						obj.setVisible(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "incorrect");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 18));
		login.setBounds(120, 214, 137, 36);
		student.add(login);
	}

}
