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

public class addClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtfirstname;
	private JTextField txtlastname;
	private JTextField txtGender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addClient frame = new addClient();
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
	private JButton btnBack;

	public addClient() {
		super("Add Client");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 31, 139, 37);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 95, 139, 37);
		contentPane.add(lblLastName);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 190, 139, 37);
		contentPane.add(lblGender);

		txtfirstname = new JTextField();
		txtfirstname.setText("");
		txtfirstname.setBounds(234, 35, 151, 29);
		contentPane.add(txtfirstname);
		txtfirstname.setColumns(10);

		txtlastname = new JTextField();
		txtlastname.setText("");
		txtlastname.setColumns(10);
		txtlastname.setBounds(234, 99, 151, 29);
		contentPane.add(txtlastname);

		txtGender = new JTextField();
		txtGender.setText("");
		txtGender.setColumns(10);
		txtGender.setBounds(234, 194, 151, 29);
		contentPane.add(txtGender);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Clients ('Name','Surname','Gender' ) values(?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtfirstname.getText());
					pst.setString(2, txtlastname.getText());
					pst.setString(3, txtGender.getText());

					pst.execute();
					// tableCars.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Saved!");
					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(10, 289, 89, 37);
		Image img1 = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(img1));
		contentPane.add(btnSave);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(296, 289, 89, 37);
		Image img = new ImageIcon(this.getClass().getResource("/Back.png")).getImage();
		img = img.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img));
		contentPane.add(btnBack);
	}

}
