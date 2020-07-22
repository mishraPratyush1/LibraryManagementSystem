package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class adminStuff extends JFrame {

	private JPanel admin;
	private JTextField name;
	private JTextField regd_no;
	private JTextField username;
	private JPasswordField password;
	private JTextField book;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminStuff frame = new adminStuff();
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
	
	
	LocalDate date = LocalDate.now();
	Connection con = null;
	public void refreshTabel() 
	{
		try {
			
			String Query = "select * from library";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			    
	        } 
	       
			catch (Exception e2) {
				e2.printStackTrace();
			 
			}
	}
	
	File f = new File(
			"C:\\Users\\PRATYUSH\\Desktop\\engg books\\Computer\\backup\\codes_java\\jdbcDemo\\src\\project\\copy.txt");
			

	public adminStuff() 
	{
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 428);
		admin = new JPanel();
		admin.setBackground(new Color(255, 218, 185));
		admin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(admin);
		admin.setLayout(null);
		
		JLabel NAME = new JLabel("NAME");
		NAME.setHorizontalAlignment(SwingConstants.CENTER);
		NAME.setForeground(new Color(0, 0, 255));
		NAME.setFont(new Font("Consolas", Font.BOLD, 17));
		NAME.setBounds(10, 40, 69, 23);
		admin.add(NAME);
		
		name = new JTextField();
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setColumns(10);
		name.setBounds(89, 42, 125, 20);
		admin.add(name);
		
		JLabel REGD = new JLabel("REGD_NO");
		REGD.setHorizontalAlignment(SwingConstants.CENTER);
		REGD.setForeground(new Color(0, 0, 255));
		REGD.setFont(new Font("Consolas", Font.BOLD, 17));
		REGD.setBounds(10, 86, 69, 23);
		admin.add(REGD);
		
		regd_no = new JTextField();
		regd_no.setHorizontalAlignment(SwingConstants.CENTER);
		regd_no.setColumns(10);
		regd_no.setBounds(89, 87, 125, 23);
		admin.add(regd_no);
		
		JLabel USERNAME = new JLabel("USERNAME");
		USERNAME.setFont(new Font("Consolas", Font.BOLD, 17));
		USERNAME.setHorizontalAlignment(SwingConstants.CENTER);
		USERNAME.setForeground(Color.BLUE);
		USERNAME.setBounds(10, 136, 79, 23);
		admin.add(USERNAME);
		
		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setColumns(10);
		username.setBounds(89, 135, 125, 23);
		admin.add(username);
		
		JLabel lblNewLabel_5 = new JLabel("PASSWORD");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.BLUE);
		lblNewLabel_5.setFont(new Font("Consolas", Font.BOLD, 17));
		lblNewLabel_5.setBounds(3, 185, 86, 23);
		admin.add(lblNewLabel_5);
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(89, 182, 125, 26);
		admin.add(password);
		
		JLabel boook = new JLabel("BOOK");
		boook.setHorizontalAlignment(SwingConstants.CENTER);
		boook.setForeground(Color.BLUE);
		boook.setFont(new Font("Consolas", Font.BOLD, 17));
		boook.setBounds(11, 230, 68, 26);
		admin.add(boook);
		
		book = new JTextField();
		book.setColumns(10);
		book.setBounds(89, 231, 125, 23);
		admin.add(book);
		
		JLabel lblNewLabel = new JLabel("welcome to college library!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 22));
		lblNewLabel.setBounds(270, 11, 360, 32);
		admin.add(lblNewLabel);
		
		JLabel operations = new JLabel("OPERATIONS");
		operations.setForeground(new Color(30, 144, 255));
		operations.setHorizontalAlignment(SwingConstants.CENTER);
		operations.setFont(new Font("Consolas", Font.BOLD, 20));
		operations.setBounds(61, 286, 153, 26);
		admin.add(operations);
		
		JButton insert = new JButton("INSERT");
		insert.setForeground(new Color(128, 0, 0));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO library(name,regd_no,username,password) VALUES(?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, name.getText());
					pstmt.setString(2, regd_no.getText());
					pstmt.setString(3, username.getText());
					pstmt.setString(4, password.getText());
					
					pstmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "welcome to our Library!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				refreshTabel();
			}
		});
		insert.setBounds(3, 329, 89, 51);
		admin.add(insert);
		
		JButton update = new JButton("UPDATE");
		update.setForeground(new Color(139, 0, 0));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE library SET book = ? where regd_no ='"+regd_no.getText()+"' ";
					PreparedStatement pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, book.getText());
					
					
					pstmt.executeUpdate();


					JOptionPane.showMessageDialog(null, "updated..");
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				refreshTabel();
			}
		});
		update.setBounds(112, 327, 89, 55);
		admin.add(update);
		
		JButton display = new JButton("DISPLAY");
		display.setForeground(new Color(139, 0, 0));
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTabel();
			}
		});
		display.setBounds(222, 331, 89, 47);
		admin.add(display);
		
		JButton delete = new JButton("DELETE");
		delete.setForeground(new Color(139, 0, 0));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE from library where name = ? AND regd_no = ?";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, name.getText());
					pstmt.setString(2, regd_no.getText());
					
					pstmt.executeUpdate();
					
					
					JOptionPane.showMessageDialog(null, "deleted!");
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				refreshTabel();
			}
		});
		delete.setBounds(332, 331, 89, 46);
		admin.add(delete);
		
		JLabel lblNewLabel_1 = new JLabel("list of students and their information");
		lblNewLabel_1.setForeground(new Color(148, 0, 211));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(312, 58, 347, 32);
		admin.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 90, 486, 222);
		admin.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton print = new JButton("PRINT");
		print.setForeground(new Color(139, 0, 0));
		
		
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					 
					String query ="select name,regd_no,book from library where username = ? AND password = ?";
					PreparedStatement pstmt = con.prepareStatement(query);
					
					JOptionPane.showMessageDialog(null, "relogin to print your credentials");
					String username = JOptionPane.showInputDialog("username ");
					String password = JOptionPane.showInputDialog("password ");
					pstmt.setString(1,username);
					pstmt.setString(2,password);
					
					ResultSet rs= pstmt.executeQuery();
					
					while(rs.next()) 
					{
						try {
							
							FileWriter out = new FileWriter(f);
							out.write("name ->"+rs.getString("name")+"\nregd_no ->"+rs.getString("regd_no")+"\nbook ->"+rs.getString("book")+"\nissued on ->"+date);
							JOptionPane.showMessageDialog(null, "printed Succuesfully..");
							out.close();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2);
						}
						
						
					}
						
					
					
				} catch (SQLException e2) {
					
					e2.printStackTrace();
				}
				
				
				
				
				
			}
		});
		print.setBounds(446, 327, 103, 54);
		admin.add(print);
	}
}
