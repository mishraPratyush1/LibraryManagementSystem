package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Books extends JFrame {

	private JPanel contentPane;
	private JLabel change,lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Books frame = new Books();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshTabel() 
	{
		
		try {
			
			String Query = "select * from books";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			tableOfBooks.setModel(DbUtils.resultSetToTableModel(rs));    
	        } 
	       
			catch (Exception e2) {
				e2.printStackTrace();
			 
			}
		
	}

	Connection con = null;
	private JTable tableOfBooks;
	public Books() {
		
		try 
		{
			String url = "jdbc:mysql://localhost:3306/PRATYUSH";
			String uName = "root";
			String pass="12345678";
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,uName,pass);
			
			JOptionPane.showMessageDialog(null, "successful");
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
		//refreshTabel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(127, 255, 212));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 168, 605, 208);
		contentPane.add(scrollPane);
		
		tableOfBooks = new JTable();
		tableOfBooks.setFont(new Font("Consolas", Font.BOLD, 11));
		tableOfBooks.setBackground(new Color(255, 218, 185));
		tableOfBooks.setForeground(new Color(0, 0, 255));
		scrollPane.setViewportView(tableOfBooks);
		
		JLabel change = new JLabel("BOOKS AVAILABLE IN THE LIBRARY");
		change.setForeground(new Color(204, 0, 0));
		change.setFont(new Font("Consolas", Font.BOLD, 23));
		change.setHorizontalAlignment(SwingConstants.CENTER);
		change.setBounds(288, 11, 439, 55);
		contentPane.add(change);
		
		JButton allbooks = new JButton("All BOOKS");
		allbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				change.setForeground(Color.BLACK);
				refreshTabel();
			}
		});
		
		allbooks.setBounds(10, 11, 129, 55);
		contentPane.add(allbooks);
		
		JLabel lblNewLabel = new JLabel("BOOKS WHICH YOU HAVE ISSUED");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(288, 98, 439, 59);
		contentPane.add(lblNewLabel);
		
		JButton mybook = new JButton("MY BOOK");
		mybook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setForeground(Color.BLACK);
				try {
					
					lblNewLabel.setText("BOOKS YOU HAVE ISSUED ");
					String query = "select book from library where username = ? AND password = ?";
					PreparedStatement pstmt = con.prepareStatement(query);
					JOptionPane.showMessageDialog(getParent(), "you have to relogin!");
					String a = JOptionPane.showInputDialog(getParent(), "enter you username ");
					String b = JOptionPane.showInputDialog(getParent(), "enter you password ");
					pstmt.setString(1, a);
					pstmt.setString(2, b);
					
					ResultSet rs = pstmt.executeQuery();
					tableOfBooks.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					JOptionPane.showMessageDialog(null, "succesfully viewed");
					
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		
		mybook.setBounds(10, 98, 129, 59);
		contentPane.add(mybook);
		
		
	}
}
