/* 
Author: simba
Topic: Impala Car Rental System
*/

package carRental;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class searchSupplier extends JFrame {

	private JPanel contentPane;
	private JTextField txtselect;
	private JComboBox comboBoxselection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchSupplier frame = new searchSupplier();
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
	private JTable table;
	private JScrollPane scrollPane;

	public searchSupplier() {
		super("Search Suppliers");
		connection = Dbconnection.dbconnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxselection = new JComboBox();
		comboBoxselection.setModel(
				new DefaultComboBoxModel(new String[] { "Name", "Region", "Email Address", "Supplier_Number" }));
		comboBoxselection.setBounds(41, 59, 145, 29);
		contentPane.add(comboBoxselection);

		txtselect = new JTextField();
		txtselect.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String tselect = (String) comboBoxselection.getSelectedItem();
					String query = "select * from Suppliers where " + tselect + " =?";
					System.out.println(query);
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtselect.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});

		txtselect.setBounds(307, 59, 145, 29);
		txtselect.setText("");
		contentPane.add(txtselect);
		txtselect.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(186, 342, 89, 39);
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img1));
		contentPane.add(btnBack);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 118, 436, 215);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
