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

public class searchCar extends JFrame {

	private JPanel contentPane;
	private JTextField txtselection;
	private JTable tableSearchCars;
	private JComboBox comboBoxSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchCar frame = new searchCar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public searchCar() {
		super("Search Car");
		connection = Dbconnection.dbconnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtselection = new JTextField();
		txtselection.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					String select = (String) comboBoxSelect.getSelectedItem();
					String query = "select * from Cars where " + select + " = ?";

					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String) txtselection.getText());
					System.out.println(query);
					ResultSet rs = pst.executeQuery();
					tableSearchCars.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
		txtselection.setBounds(262, 21, 178, 36);
		contentPane.add(txtselection);
		txtselection.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 404, 186);
		contentPane.add(scrollPane);
		tableSearchCars = new JTable();
		scrollPane.setViewportView(tableSearchCars);

		comboBoxSelect = new JComboBox();
		comboBoxSelect
				.setModel(new DefaultComboBoxModel(new String[] { "Click---", "Name", "Model", "Color", "Milage " }));
		comboBoxSelect.setBounds(10, 21, 178, 36);
		contentPane.add(comboBoxSelect);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(172, 282, 97, 36);
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(img1));
		contentPane.add(btnNewButton);
	}
}
