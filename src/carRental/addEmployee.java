/* 
 Author: simba
 Topic: Impala Car Rental System
*/

package carRental;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class addEmployee extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addEmployee frame = new addEmployee();
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
	private JTextField textFname;
	private JTextField textlasName;
	private JTextField textGEnder;
	private JTextField textDepart;
	private JButton button;

	public addEmployee() {
		super("Add Employees");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 26, 145, 33);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 95, 145, 33);
		contentPane.add(lblLastName);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 162, 133, 39);
		contentPane.add(lblGender);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 244, 133, 33);
		contentPane.add(lblDepartment);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Employees ('Name','Surname','Gender' ,'Post') values(?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFname.getText());
					pst.setString(2, textlasName.getText());
					pst.setString(3, textGEnder.getText());
					pst.setString(4, textDepart.getText());

					pst.execute();
					// tableCars.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Saved!");
					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(10, 309, 89, 30);
		Image img1 = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(img1));
		contentPane.add(btnSave);

		textFname = new JTextField();
		textFname.setBounds(215, 31, 156, 23);
		contentPane.add(textFname);
		textFname.setColumns(10);

		textlasName = new JTextField();
		textlasName.setColumns(10);
		textlasName.setBounds(215, 100, 156, 23);
		contentPane.add(textlasName);

		textGEnder = new JTextField();
		textGEnder.setColumns(10);
		textGEnder.setBounds(215, 170, 156, 23);
		contentPane.add(textGEnder);

		textDepart = new JTextField();
		textDepart.setColumns(10);
		textDepart.setBounds(215, 249, 156, 23);
		contentPane.add(textDepart);

		button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(282, 309, 89, 30);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(img2));
		contentPane.add(button);
	}

}
