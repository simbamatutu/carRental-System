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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class SearchEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private JComboBox comboBoxSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEmployee frame = new SearchEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	/**
	 * Create the frame.
	 */
	public SearchEmployee() {
		super("Search Employees");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String select = (String) comboBoxSelect.getSelectedItem();
					String query = "select * from Employees where " + select + " = ?";

					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, (String) txtSearch.getText());
					System.out.println(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					ps.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
					ex.printStackTrace();
				}
			}
		});
		txtSearch.setBounds(318, 54, 178, 34);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		comboBoxSelect = new JComboBox();
		comboBoxSelect.setModel(new DefaultComboBoxModel(
				new String[] { "Click---", "Name", "Surname", "Gender", "Email Address", "Post" }));
		comboBoxSelect.setBounds(10, 54, 163, 34);
		contentPane.add(comboBoxSelect);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 128, 472, 208);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(186, 342, 89, 39);
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img1));
		contentPane.add(btnBack);

	}
}
