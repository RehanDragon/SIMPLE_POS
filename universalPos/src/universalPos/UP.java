package universalPos;



//import java.awt.print.*;
//
//import javafx.print.Printer;
//
//import javafx.*;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.fabric.xmlrpc.base.Array;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.channels.ShutdownChannelGroupException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.print.*;
import java.awt.Panel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JRadioButton;


public class UP extends JFrame {

	private JFrame frame;
	private JTextField textField_ProductCode;
	private JTextField textField_ProductName;
	private JTextField textField_Price;
	private JTextField textField_Amount;
	private JTextField textField_Total;
	private JTextField textField_Pay;
	private JTextField textField_Balance;
	private JTable table;
private	JTextArea textArea;
	private JSpinner spinnerQuantity ;
	JTextArea textArea_table1;
private	String ProductName;
private	String ProductQuantity;
private	 String ProductPrice;
private	String Amount;
ArrayList<String> ItemsKeList;
int Quantity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UP window = new UP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UP() {
		
		
		
		
		initialize();
		
		
//		Images will come in constructor of the current class after initialize method
//		Panel panel_3 = new Panel();
//		 panel_3.setBounds(21, 28, 141, 139);
//		 frame.getContentPane().add(panel_3);
//		 panel_3.setLayout(null);
//		 
//		 JLabel lblPic1 = new JLabel("");
//		 ImageIcon img1= new ImageIcon("image/coffee.JPG");
//		 lblPic1.setIcon(img1);
//		 lblPic1.setBounds(10, 11, 121, 117);
//		 panel_3.add(lblPic1);
		 
		 
		 
		 
		 
		 
		 
		 
		
	}

	
//	Making Connection Object
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textFieldEnterTokenNumber;
	private JTextField textFieldTokenNoForTable1;
//	Making Connection Object ends here
	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize() 
	
	{
		
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1679, 967);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 250, 154));
		panel.setBorder(new TitledBorder(null, "Universal POS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 248, 815, 374);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setBounds(10, 34, 97, 14);
		panel.add(lblProductCode);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(195, 34, 79, 14);
		panel.add(lblProductName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(368, 34, 65, 14);
		panel.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(536, 34, 56, 14);
		panel.add(lblPrice);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(731, 34, 46, 14);
		panel.add(lblAmount);
		
		 spinnerQuantity = new JSpinner();
		 spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerQuantity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent stateChangeKaVariable) {
				
				 Quantity= Integer.parseInt(spinnerQuantity.getValue().toString()); 
				int Price=Integer.parseInt(textField_Price.getText()); 
				int Total=Quantity*Price;
				
				textField_Amount.setText(String.valueOf(Total) );
				
			}
		});
		
		
		
		
		spinnerQuantity.setBounds(368, 71, 29, 20);
		panel.add(spinnerQuantity);
		
		textField_ProductCode = new JTextField();
		
		
		textField_ProductCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			
		        if(arg0.getKeyCode() == KeyEvent.VK_ENTER) 
		        {
		        	String ProductCode = textField_ProductCode.getText();
		        	
		        	try {
						Class.forName("com.mysql.jdbc.Driver");
						
//						jo table localhost main bnya ha wo table ka nam aye ga
//						jesa wordpress main dalte hain ausa install krte hua  username root aur password blank chorta hain wesa he yehan pa chezon ko initialiaze kia ha
						con=DriverManager.getConnection("jdbc:mysql://localhost/salespos","root","");
						pst=con.prepareStatement("select * from product where id = ?");
						pst.setString(1, ProductCode);
						rs=pst.executeQuery();
						
						
//						optimize style main likh dia ,,, if ka bad jo pehle line hote ha wo aik bar he chla ge else likhna ke zarurat nai ku k aus pehle line k bad sara else he consider kia gye ga
						if(rs.next()==false)JOptionPane.showMessageDialog(frame,"product ID not found");
						String pname = rs.getString("prodname");
						String price = rs.getString("price");
						textField_ProductName.setText(pname.trim());
						textField_Price.setText(price.trim());
							
					} 
		        	
		        	
		        	catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        	
		        	
		        }  	
		        
		        
		        
			
			}
		});
		
		
		textField_ProductCode.setBounds(10, 71, 86, 20);
		panel.add(textField_ProductCode);
		textField_ProductCode.setColumns(10);
		
		textField_ProductName = new JTextField();
		textField_ProductName.setEditable(false);
		textField_ProductName.setBounds(188, 71, 86, 20);
		panel.add(textField_ProductName);
		textField_ProductName.setColumns(10);
		
		textField_Price = new JTextField();
		textField_Price.setEditable(false);
		textField_Price.setBounds(516, 71, 86, 20);
		panel.add(textField_Price);
		textField_Price.setColumns(10);
		
		textField_Amount = new JTextField();
		textField_Amount.setEditable(false);
		textField_Amount.setBounds(702, 71, 86, 20);
		panel.add(textField_Amount);
		textField_Amount.setColumns(10);
		
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 271, 177, 23);
		panel.add(btnAdd);
		
		
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = new DefaultTableModel();
				
				model= (DefaultTableModel)table.getModel();
				
				model.addRow(new Object[]
						
						{
					textField_ProductCode.getText(),
					textField_ProductName.getText(),
					spinnerQuantity.getValue().toString(),
					textField_Price.getText(),
					textField_Amount.getText(),
						}
						
						);
				
//				code optimized kr k likh dia for k bad curly braces hta dia
				int count=0;
				for(int i=0;i<table.getRowCount();i++)count=count+Integer.parseInt(table.getValueAt(i, 4).toString());
				
//		 		My custom logic works finally hurray 
		 		spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				textField_Total.setText(Integer.toString(count));
				textField_ProductCode.setText("");
				textField_ProductName.setText("");
				textField_Price.setText("");
				textField_Amount.setText("");
//				spinnerQuantity.setValue(0);
//				int Quant= Integer.parseInt(spinnerQuantity.getValue().toString()); 
                       
//				Quant=0;
//				spinnerQuantity.setName("");
			
				
				textField_ProductCode.requestFocus();
				
			}
		});
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 153));
		panel_1.setBounds(934, 221, 421, 226);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(198, 22, 46, 14);
		panel_1.add(lblTotal);
		
		textField_Total = new JTextField();
		textField_Total.setEditable(false);
		textField_Total.setBounds(173, 47, 86, 20);
		panel_1.add(textField_Total);
		textField_Total.setColumns(10);
		
		JLabel lblPay = new JLabel("Customer Paid");
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setBounds(173, 76, 86, 14);
		panel_1.add(lblPay);
		
		textField_Pay = new JTextField();
		textField_Pay.setBounds(173, 101, 86, 20);
		panel_1.add(textField_Pay);
		textField_Pay.setColumns(10);
		
		JLabel lblBalance = new JLabel("Amountt to Pay ");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setBounds(173, 132, 96, 14);
		panel_1.add(lblBalance);
		
		textField_Balance = new JTextField();
		textField_Balance.setEditable(false);
		textField_Balance.setBounds(173, 153, 86, 20);
		panel_1.add(textField_Balance);
		textField_Balance.setColumns(10);
		
		
		
		JButton btnPrintBill = new JButton("Get Bill");
		btnPrintBill.setBounds(173, 192, 89, 23);
		panel_1.add(btnPrintBill);
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent printBill_ka_Action_Ka_Variable) {
				
//				Balance Logic

				Balance();
				
				
//				Bill Logic
				
				Bill(); 
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 633, 815, 201);
		frame.getContentPane().add(scrollPane);
		
		
		
		
//		table issue
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProductID", "ProductName", "Qty", "Price", "Amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
//	jub tak value nai dalain ga ais main jub tak chezain nai ayain ge 	
		
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{1, "John", 40.0, false},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//				{null, null, null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
//			}
//		));
		
		
		
		
		scrollPane.setViewportView(table);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {	
				
				
//			 lblBillRecipt.fil.print();
				 
				textArea.print();
//				ya print ho reha ha
//				table.print();
			}
			catch(PrinterException pe) 
			{
				pe.printStackTrace();
			}
				
				
			}
		});
		btnPrint.setBounds(747, 845, 89, 23);
		frame.getContentPane().add(btnPrint);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(934, 476, 421, 392);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(10, 11, 401, 370);
		 panel_2.add(scrollPane_1);
		
		 textArea = new JTextArea();
		 textArea.setEditable(false);
		 scrollPane_1.setViewportView(textArea);
		 
		 JButton btnClearAll = new JButton("clear all");
		 btnClearAll.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
//		 		My custom logic works finally hurray 
		 		spinnerQuantity.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
//		 		this is the code to clear table data
				table.setModel(new DefaultTableModel(null,new String[]{"ProductID", "ProductName", "Qty", "Price", "Amount"}));
		 		textArea.setText("");
		 		textField_Total.setText("");
		 		textField_Pay.setText("");
		 		textField_Balance.setText("");
		 		textFieldEnterTokenNumber.setText("");
//		 		spinnerQuantity.getValue();
//		 		spinnerQuantity.setValue(1);

//		 		hourSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
//		 		spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
//		 		spinnerQuantity.On
//		 		spinnerQuantity.setValue(0);

				
		 		
		 		
				
		 	}
		 });
		 btnClearAll.setBounds(600, 845, 89, 23);
		 frame.getContentPane().add(btnClearAll);
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBounds(21, 25, 832, 212);
		 frame.getContentPane().add(panel_3);
		 panel_3.setLayout(null);
		 
		 JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		 tabbedPane.setBounds(0, 0, 816, 201);
		 panel_3.add(tabbedPane);
		 
		 Panel panel_Tab1 = new Panel();
		 tabbedPane.addTab("ice creams", null, panel_Tab1, null);
		 panel_Tab1.setLayout(null);
		 
		 JLabel label = new JLabel("");
		 label.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label.setBounds(10, 11, 123, 116);
		 panel_Tab1.add(label);
		 
		 JLabel label_1 = new JLabel("");
		 label_1.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label_1.setBounds(143, 11, 123, 116);
		 panel_Tab1.add(label_1);
		 
		 JLabel label_2 = new JLabel("");
		 label_2.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label_2.setBounds(276, 11, 123, 116);
		 panel_Tab1.add(label_2);
		 
		 JLabel label_3 = new JLabel("");
		 label_3.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label_3.setBounds(409, 11, 123, 116);
		 panel_Tab1.add(label_3);
		 
		 JLabel label_4 = new JLabel("");
		 label_4.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label_4.setBounds(542, 11, 123, 116);
		 panel_Tab1.add(label_4);
		 
		 JLabel label_5 = new JLabel("");
		 label_5.setIcon(new ImageIcon("Image/coffee.JPG"));
		 label_5.setBounds(675, 11, 123, 116);
		 panel_Tab1.add(label_5);
		 
		 JLabel lblFlavourBlue = new JLabel("Flavour : Blue Berrie ");
		 lblFlavourBlue.setBounds(10, 135, 139, 14);
		 panel_Tab1.add(lblFlavourBlue);
		 
		 JLabel lblCodeIceberrie = new JLabel("code: ice berry");
		 lblCodeIceberrie.setHorizontalAlignment(SwingConstants.CENTER);
		 lblCodeIceberrie.setBounds(0, 148, 106, 14);
		 panel_Tab1.add(lblCodeIceberrie);
		 
		 JLabel lblFlavourChocolate = new JLabel("Flavour: Chocolate");
		 lblFlavourChocolate.setBounds(143, 135, 123, 14);
		 panel_Tab1.add(lblFlavourChocolate);
		 
		 JLabel lblCodeIceChoco = new JLabel("code: ice choco");
		 lblCodeIceChoco.setBounds(143, 148, 91, 14);
		 panel_Tab1.add(lblCodeIceChoco);
		 
		 JLabel lblFlavourBlack = new JLabel("Flavour : Black Berrie");
		 lblFlavourBlack.setBounds(276, 138, 123, 14);
		 panel_Tab1.add(lblFlavourBlack);
		 
		 JLabel lblCodeIceBb = new JLabel("code: ice bb");
		 lblCodeIceBb.setBounds(276, 148, 101, 14);
		 panel_Tab1.add(lblCodeIceBb);
		 
		 JLabel lblFlavourMango = new JLabel("Flavour : mango");
		 lblFlavourMango.setBounds(409, 138, 91, 14);
		 panel_Tab1.add(lblFlavourMango);
		 
		 JLabel lblCodeIceMan = new JLabel("code: ice man");
		 lblCodeIceMan.setBounds(409, 148, 91, 14);
		 panel_Tab1.add(lblCodeIceMan);
		 
		 JLabel lblFlavourCherry = new JLabel("Flavour : Cherry");
		 lblFlavourCherry.setBounds(542, 138, 101, 14);
		 panel_Tab1.add(lblFlavourCherry);
		 
		 JLabel lblCodeIceCh = new JLabel("code: ice ch");
		 lblCodeIceCh.setBounds(542, 148, 80, 14);
		 panel_Tab1.add(lblCodeIceCh);
		 
		 JLabel lblFlavourGrapes = new JLabel("Flavour : grapes");
		 lblFlavourGrapes.setBounds(675, 138, 101, 14);
		 panel_Tab1.add(lblFlavourGrapes);
		 
		 JLabel lblCodeIceGrp = new JLabel("code: ice grp");
		 lblCodeIceGrp.setBounds(675, 148, 80, 14);
		 panel_Tab1.add(lblCodeIceGrp);
		 
		 Panel panel_Tab2 = new Panel();
		 tabbedPane.addTab("drinks", null, panel_Tab2, null);
		 panel_Tab2.setLayout(null);
		 
		 JPanel panel_4 = new JPanel();
		 panel_4.setBounds(934, 40, 421, 172);
		 frame.getContentPane().add(panel_4);
		 panel_4.setLayout(null);
		 
		 textFieldEnterTokenNumber = new JTextField();
		 textFieldEnterTokenNumber.setBounds(159, 69, 114, 20);
		 panel_4.add(textFieldEnterTokenNumber);
		 textFieldEnterTokenNumber.setColumns(10);
		 
		 JButton btnGenrateToken = new JButton("Genrate Token");
		 btnGenrateToken.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		GenrateToken();
		 		
		 	}
		 });
		 btnGenrateToken.setBounds(159, 100, 114, 23);
		 panel_4.add(btnGenrateToken);
		 
		 JButton btnPrintToken = new JButton("Print Token");
		 btnPrintToken.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		try {
					textArea.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}
		 });
		 btnPrintToken.setBounds(159, 138, 114, 23);
		 panel_4.add(btnPrintToken);
		 
		 JScrollPane scrollPane_2 = new JScrollPane();
		 scrollPane_2.setBounds(1375, 40, 261, 828);
		 frame.getContentPane().add(scrollPane_2);
		 
		 JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		 scrollPane_2.setViewportView(tabbedPane_1);
		 
		 JPanel panel_Table1 = new JPanel();
		 tabbedPane_1.addTab("table 1", null, panel_Table1, null);
		 panel_Table1.setLayout(null);
		 
		 textFieldTokenNoForTable1 = new JTextField();
		 textFieldTokenNoForTable1.setBounds(10, 40, 234, 20);
		 panel_Table1.add(textFieldTokenNoForTable1);
		 textFieldTokenNoForTable1.setColumns(10);
		 
		 JButton btnGenrateTokenForTable1 = new JButton("Genrate Token For Table 1");
		 btnGenrateTokenForTable1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				
				DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss");
				   LocalDateTime now = LocalDateTime.now();
				   LocalDateTime now1 = LocalDateTime.now();
				String todays_date_and_time=dtf.format(now);
				String todays_date_and_time_1=dtf1.format(now1);
				
				
				textArea_table1.setText(textArea_table1.getText()+" ==========================================");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+" ===================== CUSTOMER =====================");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				
				textArea_table1.setText(textArea_table1.getText()+" TOKEN NO :  "+textFieldTokenNoForTable1.getText());
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+" ===================== WAITER =====================");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+" ==========================================");
				
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				
				textArea_table1.setText(textArea_table1.getText()+"Date : "+todays_date_and_time);
				
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+"Time "+todays_date_and_time_1);
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+" TOKEN NO :  "+textFieldTokenNoForTable1.getText());
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+" Waiter Name  :                   ");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				
				textArea_table1.setText(textArea_table1.getText()+" Table Number :            1       ");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				
				textArea_table1.setText(textArea_table1.getText()+" Products & Quantity  :                   ");
				
				textArea_table1.setText(textArea_table1.getText()+"\n");textArea_table1.setText(textArea_table1.getText()+"\n");textArea_table1.setText(textArea_table1.getText()+"\n");textArea_table1.setText(textArea_table1.getText()+"\n");
					
				
					
					
				
				textArea_table1.setText(textArea_table1.getText()+" ==========================================");		
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnGenrateTokenForTable1.setBounds(10, 80, 234, 23);
		 panel_Table1.add(btnGenrateTokenForTable1);
		 
		 JScrollPane scrollPane_3 = new JScrollPane();
		 scrollPane_3.setBounds(10, 199, 234, 284);
		 panel_Table1.add(scrollPane_3);
		 
		 textArea_table1 = new JTextArea();
		 scrollPane_3.setViewportView(textArea_table1);
		 
		 JButton btnGetBillForToken1 = new JButton("Get Bill For Table 1");
		 btnGetBillForToken1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
//		 	Balance logic	
		 		
		 		int total = Integer.parseInt(textField_Total.getText()); 
//				Pay wo value ha jitna customer na pay kia ho ga 
				int pay = Integer.parseInt(textField_Pay.getText());
				if(textField_Pay.getText()=="")JOptionPane.showMessageDialog(frame, "");
				int bal = pay - total;
				
				textField_Balance.setText(String.valueOf(bal) );
		 		
		 		
		 		
//		 		Bill Logic
				
				String SubTotal= textField_Total.getText();
				int quantity= Integer.parseInt(String.valueOf(spinnerQuantity.getValue())) ;
				String payBill=textField_Pay.getText();
				String balance=textField_Balance.getText();
				
				
				DefaultTableModel model = new DefaultTableModel();
				
				model= (DefaultTableModel)table.getModel();
				
				textArea_table1.setText(textArea_table1.getText()+"**************************\n");
				
				textArea_table1.setText(textArea_table1.getText()+"          UNIVERSAL POS   \n");
				
				textArea_table1.setText(textArea_table1.getText()+"**************************\n");
				
				textArea_table1.setText(textArea_table1.getText()+"           PRODUCT         "+"\t"+" Quantity "+"\t"+" Price Per Item"+"\t"+" Amount "+"\n");
				
				for(int i=0;i<model.getRowCount();i++) 
				{
					ProductName=(String)model.getValueAt(/*row*/i, /*column*/1);
					ProductQuantity=(String)model.getValueAt(/*row*/i, /*column*/2);
					ProductPrice=(String)model.getValueAt(/*row*/i, /*column*/3);
					Amount=(String)model.getValueAt(/*row*/i, /*column*/4);
					
					
					textArea_table1.setText(textArea_table1.getText()+"\t"+ProductName+"\t"+ProductQuantity+"\t"+ProductPrice+"\t"+Amount+"\n");
					
					
				}
				textArea_table1.setText(textArea_table1.getText()+"\n");
				textArea_table1.setText(textArea_table1.getText()+"\n");
				
				textArea_table1.setText(textArea_table1.getText()+"\t"+"\t"+"Sub Total : "+SubTotal+"\n");
				textArea_table1.setText(textArea_table1.getText()+"\t"+"\t"+"Customer Paid : "+payBill+"\n");
				textArea_table1.setText(textArea_table1.getText()+"\t"+"\t"+"Amount to Pay to Customer: "+balance+"\n");
				
				
				textArea_table1.setText(textArea_table1.getText()+"**************************\n");
				textArea_table1.setText(textArea_table1.getText()+"*****THANKS FOR SHOPPING HAVE A NICE DAY ***\n");
		 		
		 	}
		 });
		 btnGetBillForToken1.setBounds(10, 109, 234, 23);
		 panel_Table1.add(btnGetBillForToken1);
		 
		 JButton btnPrintRaseedFor = new JButton("Print Raseed For Table 1");
		 btnPrintRaseedFor.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		try {	
					
					
//					 lblBillRecipt.fil.print();
						 
		 			textArea_table1.print();
//						ya print ho reha ha
//						table.print();
					}
					catch(PrinterException pe) 
					{
						pe.printStackTrace();
					}
		 	}
		 });
		 btnPrintRaseedFor.setBounds(10, 512, 234, 23);
		 panel_Table1.add(btnPrintRaseedFor);
		 
		 JPanel panel_Table2 = new JPanel();
		 tabbedPane_1.addTab("table 2", null, panel_Table2, null);
		 panel_Table2.setLayout(null);
		 
		 
		 
		
		 
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	
	public void Balance() 
	{
		
		int total = Integer.parseInt(textField_Total.getText()); 
//		Pay wo value ha jitna customer na pay kia ho ga 
		int pay = Integer.parseInt(textField_Pay.getText());
		if(textField_Pay.getText()=="")JOptionPane.showMessageDialog(frame, "");
		int bal = pay - total;
		
		textField_Balance.setText(String.valueOf(bal) );
	}
	
	
	public void Bill() 
	{
		String SubTotal= textField_Total.getText();
		int quantity= Integer.parseInt(String.valueOf(spinnerQuantity.getValue())) ;
		String pay=textField_Pay.getText();
		String balance=textField_Balance.getText();
		
		
		DefaultTableModel model = new DefaultTableModel();
		
		model= (DefaultTableModel)table.getModel();
		
		textArea.setText(textArea.getText()+"**************************\n");
		
		textArea.setText(textArea.getText()+"          UNIVERSAL POS   \n");
		
		textArea.setText(textArea.getText()+"**************************\n");
		
		textArea.setText(textArea.getText()+"           PRODUCT         "+"\t"+" Quantity "+"\t"+" Price Per Item"+"\t"+" Amount "+"\n");
		
		for(int i=0;i<model.getRowCount();i++) 
		{
			ProductName=(String)model.getValueAt(/*row*/i, /*column*/1);
			ProductQuantity=(String)model.getValueAt(/*row*/i, /*column*/2);
			ProductPrice=(String)model.getValueAt(/*row*/i, /*column*/3);
			Amount=(String)model.getValueAt(/*row*/i, /*column*/4);
			
			
			textArea.setText(textArea.getText()+"\t"+ProductName+"\t"+ProductQuantity+"\t"+ProductPrice+"\t"+Amount+"\n");
			
			
		}
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+"\n");
		
		textArea.setText(textArea.getText()+"\t"+"\t"+"Sub Total : "+SubTotal+"\n");
		textArea.setText(textArea.getText()+"\t"+"\t"+"Customer Paid : "+pay+"\n");
		textArea.setText(textArea.getText()+"\t"+"\t"+"Amount to Pay to Customer: "+balance+"\n");
		
		
		textArea.setText(textArea.getText()+"**************************\n");
		textArea.setText(textArea.getText()+"*****THANKS FOR SHOPPING HAVE A NICE DAY ***\n");
		
		
		
		
	}
	
	public void GenrateToken() 
	
	{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   LocalDateTime now1 = LocalDateTime.now();
		String todays_date_and_time=dtf.format(now);
		String todays_date_and_time_1=dtf1.format(now1);
		
		
		textArea.setText(textArea.getText()+" ==========================================");
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+" ===================== CUSTOMER =====================");
		textArea.setText(textArea.getText()+"\n");
		
		textArea.setText(textArea.getText()+" TOKEN NO :  "+textFieldEnterTokenNumber.getText());
		textArea.setText(textArea.getText()+"\n");
				textArea.setText(textArea.getText()+" ===================== WAITER =====================");
				textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+" ==========================================");
		
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+"\n");
		
		textArea.setText(textArea.getText()+"Date : "+todays_date_and_time);
		
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+"Time "+todays_date_and_time_1);
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+" TOKEN NO :  "+textFieldEnterTokenNumber.getText());
		textArea.setText(textArea.getText()+"\n");
		textArea.setText(textArea.getText()+" Waiter Name  :                   ");
		textArea.setText(textArea.getText()+"\n");
		
		textArea.setText(textArea.getText()+" Table Number :                   ");
		textArea.setText(textArea.getText()+"\n");
		
		textArea.setText(textArea.getText()+" Products & Quantity  :                   ");
		
		textArea.setText(textArea.getText()+"\n");textArea.setText(textArea.getText()+"\n");textArea.setText(textArea.getText()+"\n");textArea.setText(textArea.getText()+"\n");
			
		
			
			
		
			textArea.setText(textArea.getText()+" ==========================================");
		
		
		
		
	}
	
	
	
}
