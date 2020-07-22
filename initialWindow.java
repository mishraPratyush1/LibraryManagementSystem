package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class initialWindow {

	private JFrame LibraryWindow,management;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialWindow window = new initialWindow();
					window.LibraryWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con = null;
	public initialWindow() {
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
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LibraryWindow = new JFrame();
		LibraryWindow.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		LibraryWindow.getContentPane().setBackground(new Color(255, 215, 0));
		LibraryWindow.setTitle("COLLEGE LIBRARY ");
		
		LibraryWindow.setBackground(Color.CYAN);
		LibraryWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(initialWindow.class.getResource("/project/images/iter.png")));
		LibraryWindow.setBounds(100, 100, 450, 300);
		LibraryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LibraryWindow.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WHO ARE YOU?");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 22));
		lblNewLabel.setBounds(93, 26, 244, 40);
		LibraryWindow.getContentPane().add(lblNewLabel);
		
		JButton student = new JButton("STUDENT");
		student.setFont(new Font("Tahoma", Font.BOLD, 16));
		student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LibraryWindow.dispose();
				
				student st = new student();
				st.setVisible(true);
				
				
			}
		});
		student.setBounds(151, 77, 122, 52);
		LibraryWindow.getContentPane().add(student);
		
		JButton admin = new JButton("ADMIN");
		admin.setFont(new Font("Tahoma", Font.BOLD, 15));
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryWindow.dispose();
				adminStuff st = new adminStuff();
				st.setVisible(true); 
				
				
				
				
				
				
			}
			
		});
		admin.setBounds(151, 163, 121, 57);
		LibraryWindow.getContentPane().add(admin);
	}

}
