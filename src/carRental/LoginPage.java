/* 
Author: simba
Topic: Impala Car Rental System
*/

package carRental;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	Connection connection = null;
	private JTextField txtusername;
	private JPasswordField passwordField;
	private JLabel lbluserimg;
	private JLabel label;
	private JLabel lblpassword;
	private JComboBox comboBox;

	public LoginPage() {

		initialize();
		connection = Dbconnection.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 340, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtusername = new JTextField();

		txtusername.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtusername.setText(" ");
		txtusername.setBounds(95, 127, 166, 43);
		frame.getContentPane().add(txtusername);
		txtusername.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Click--", "Corporate", "Employee", "Client" }));
		// comboBox.setSelectedItem(null);
		comboBox.setBounds(95, 255, 166, 28);
		frame.getContentPane().add(comboBox);

		JButton btnlogin = new JButton("Login");
		Image img = new ImageIcon(this.getClass().getResource("/okk.png")).getImage();
		img = img.getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnlogin.setIcon(new ImageIcon(img));

		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "select * from Employees where Username=? and Password =? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtusername.getText());
					pst.setString(2, passwordField.getText());
					String value = (String) comboBox.getSelectedItem();
					int val = 8888;
					switch (value) {
					case "Corporate":
						val = 2;
						break;
					case "Employee":
						val = 3;
						break;
					case "Client":
						val = 4;
						break;
					default:
						JOptionPane.showMessageDialog(null, "Combo empyty");

					}

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;

					}

					if (val == 2) {

						frame.dispose();
						corporate manager = new corporate();
						manager.setVisible(true);
					} else if (val == 3) {

						frame.dispose();
						employee emp = new employee();
						emp.setVisible(true);
					} else if (val == 4) {
						frame.dispose();
						clients client = new clients();
						client.setVisible(true);

					}

					else {
						JOptionPane.showMessageDialog(null, "Access Denied!");
					}

					rs.close();
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "e");

				}

			}

		});
		btnlogin.setBounds(106, 335, 124, 43);
		frame.getContentPane().add(btnlogin);

		JLabel lblimage = new JLabel("");
		img = new ImageIcon(this.getClass().getResource("/log.png")).getImage();
		img = img.getScaledInstance(124, 105, Image.SCALE_DEFAULT);
		lblimage.setIcon(new ImageIcon(img));
		lblimage.setBounds(95, 11, 124, 105);
		frame.getContentPane().add(lblimage);

		passwordField = new JPasswordField();

		passwordField.setToolTipText("password");
		passwordField.setBounds(95, 201, 166, 43);
		frame.getContentPane().add(passwordField);

		lbluserimg = new JLabel("");
		img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		img = img.getScaledInstance(57, 43, Image.SCALE_DEFAULT);
		lbluserimg.setIcon(new ImageIcon(img));
		lbluserimg.setBounds(39, 127, 57, 43);
		frame.getContentPane().add(lbluserimg);

		label = new JLabel("");
		label.setBounds(39, 127, 57, 43);
		frame.getContentPane().add(label);

		lblpassword = new JLabel("");
		img = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		img = img.getScaledInstance(57, 43, Image.SCALE_DEFAULT);
		lblpassword.setIcon(new ImageIcon(img));
		lblpassword.setBounds(39, 201, 57, 43);
		frame.getContentPane().add(lblpassword);

	}
}
