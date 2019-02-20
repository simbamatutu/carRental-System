/* 
Author: simba
Topic: Impala Car Rental System
*/

package carRental;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.proteanit.sql.DbUtils;

public class RentOut extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentOut frame = new RentOut();
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

	Connection connection = null;
	private JTextField txtname;
	private JTextField txtsurname;
	private JTextField txtID;
	private JTextField txtage;
	private JTextField txtcar;
	private JTextField txtmodel;
	private JTextField txtmilage;
	private JTextField txtcolor;
	private JTextField txtreg;
	private JTextField txtNumofdays;
	private JTextField txtDiscount;
	private JTextField txttotal;
	public JTable tableMax;
	private JTextField txtcost;

	public RentOut() {
		super("Renting");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					String query = "Select * from Cars where Status='Available'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableMax.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 754);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, "name_748736246574978");
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 53, 363, 289);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 16, 150, 25);
		panel_1.add(lblName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(6, 74, 154, 31);
		panel_1.add(lblSurname);

		JLabel lblIdNumber = new JLabel("ID NUmber");
		lblIdNumber.setBounds(10, 138, 154, 31);
		panel_1.add(lblIdNumber);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 205, 163, 31);
		panel_1.add(lblAge);

		txtname = new JTextField();
		txtname.setBounds(199, 16, 138, 25);
		panel_1.add(txtname);
		txtname.setColumns(10);

		txtsurname = new JTextField();
		txtsurname.setBounds(199, 77, 138, 25);
		panel_1.add(txtsurname);
		txtsurname.setColumns(10);

		txtID = new JTextField();
		txtID.setBounds(199, 141, 138, 25);
		panel_1.add(txtID);
		txtID.setColumns(10);

		txtage = new JTextField();
		txtage.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				try {
					String query = "insert into Clients ('Name','Surname','ID Number','Age' ) values(?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtname.getText());
					pst.setString(2, txtsurname.getText());
					pst.setString(3, txtID.getText());
					pst.setString(4, txtage.getText());

					pst.execute();
					// tableCars.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Saved!");
					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		txtage.setBounds(199, 208, 138, 25);
		panel_1.add(txtage);
		txtage.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Car Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(441, 53, 457, 289);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(6, 69, 154, 31);
		panel_2.add(lblModel);

		JLabel lblMilage = new JLabel("Milage");
		lblMilage.setBounds(6, 121, 154, 31);
		panel_2.add(lblMilage);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(6, 177, 154, 31);
		panel_2.add(lblColor);

		JLabel lblRegNumber = new JLabel("Reg Number");
		lblRegNumber.setBounds(6, 246, 154, 31);
		panel_2.add(lblRegNumber);

		JLabel lblCar = new JLabel("Car");
		lblCar.setBounds(6, 16, 154, 31);
		panel_2.add(lblCar);

		txtcar = new JTextField();
		txtcar.setBounds(267, 21, 144, 20);
		panel_2.add(txtcar);
		txtcar.setColumns(10);

		txtmilage = new JTextField();
		txtmilage.setBounds(267, 126, 144, 20);
		panel_2.add(txtmilage);
		txtmilage.setColumns(10);

		txtcolor = new JTextField();
		txtcolor.setBounds(267, 182, 144, 20);
		panel_2.add(txtcolor);
		txtcolor.setColumns(10);

		txtreg = new JTextField();
		txtreg.setBounds(267, 251, 144, 20);
		panel_2.add(txtreg);
		txtreg.setColumns(10);

		txtmodel = new JTextField();
		txtmodel.setBounds(267, 74, 144, 20);
		panel_2.add(txtmodel);
		txtmodel.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "Rental Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(25, 366, 353, 263);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNumberOfDays = new JLabel("Number Of Days");
		lblNumberOfDays.setBounds(6, 16, 163, 31);
		panel_3.add(lblNumberOfDays);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(6, 168, 163, 31);
		panel_3.add(lblDiscount);

		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(6, 221, 163, 31);
		panel_3.add(lblGrandTotal);

		txtNumofdays = new JTextField();
		txtNumofdays.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				warn();

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) throws NumberFormatException {
				// TODO Auto-generated method stub
				warn();
			}

			public void warn() {
				double days = 0;
				days = Double.parseDouble(txtNumofdays.getText());
				double cost = Double.parseDouble(txtcost.getText());
				double discount = Double.parseDouble(txtDiscount.getText());

				double price = cost * days;
				double vat = (10 * (price)) / 100;
				double finaldiscount = (discount * (price)) / 100;
				double insurance = 30.00;
				double serviceTax = 2.5;

				double grandtotal = (price + vat + insurance + serviceTax) - finaldiscount;
				System.out.println(grandtotal);
				// String grand = Double.toString(grandtotal);
				double grand = grandtotal;

				DecimalFormat df = new DecimalFormat("#.00");
				String angleFormated = df.format(grand);
				// System.out.println(grand);

				txttotal.setText(angleFormated);

			}
		});

		txtNumofdays.setBounds(166, 21, 86, 31);
		panel_3.add(txtNumofdays);
		txtNumofdays.setColumns(10);

		txtDiscount = new JTextField();
		txtDiscount.setBounds(166, 168, 86, 31);
		panel_3.add(txtDiscount);
		txtDiscount.setColumns(10);

		txttotal = new JTextField();
		txttotal.setBounds(166, 221, 86, 31);
		panel_3.add(txttotal);
		txttotal.setColumns(10);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(6, 91, 163, 31);
		panel_3.add(lblCost);

		txtcost = new JTextField();
		txtcost.setColumns(10);
		txtcost.setBounds(166, 96, 86, 31);
		panel_3.add(txtcost);

		JButton btnCheckout = new JButton("Check Out");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOrder orderShow = new showOrder();
				RentOut rentout = new RentOut();

				rentout.setVisible(false);
				orderShow.setVisible(true);
			}
		});
		btnCheckout.setBounds(45, 652, 114, 41);
		Image img = new ImageIcon(this.getClass().getResource("/chec.png")).getImage();
		img = img.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnCheckout.setIcon(new ImageIcon(img));
		panel.add(btnCheckout);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(225, 650, 114, 41);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img2));
		panel.add(btnBack);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		btnExit.setBounds(431, 650, 114, 41);
		Image im = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		im = im.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnExit.setIcon(new ImageIcon(im));
		panel.add(btnExit);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(448, 366, 493, 263);
		panel.add(tabbedPane);

		JPanel PanelCars = new JPanel();
		tabbedPane.addTab("Cars", null, PanelCars, null);
		PanelCars.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 11, 468, 213);
		PanelCars.add(scrollPane);

		tableMax = new JTable();
		tableMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableMax.getSelectedRow();
				String cell = tableMax.getModel().getValueAt(row, 0).toString();

				try {
					String query = "select * from Cars where Name = '" + cell + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						txtcar.setText(rs.getString("Name"));
						txtmodel.setText(rs.getString("Model"));
						txtmilage.setText(rs.getString("Milage"));
						txtcolor.setText(rs.getString("Color"));
						txtreg.setText(rs.getString("Regstration Number"));
						txtDiscount.setText(rs.getString("Discount"));
						txtcost.setText(rs.getString("Cost"));
					}
					pst.close();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Refresh Error");
				}

			}
		});
		scrollPane.setViewportView(tableMax);
	}
}
