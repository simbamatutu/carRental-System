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

public class addsupplier extends JFrame {

	private JPanel contentPane;
	private JTextField txtSuplName;
	private JTextField txtRegion;
	private JTextField txtEmail;
	private JTextField txtSuNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addsupplier frame = new addsupplier();
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

	public addsupplier() {
		super("Add Supplier");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setBounds(10, 41, 141, 36);
		contentPane.add(lblSupplierName);

		JLabel lblRegion = new JLabel("Region");
		lblRegion.setBounds(10, 114, 141, 36);
		contentPane.add(lblRegion);

		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(10, 186, 141, 36);
		contentPane.add(lblEmailAddress);

		JLabel lblSupplierNumber = new JLabel("Supplier Number ");
		lblSupplierNumber.setBounds(10, 255, 141, 36);
		contentPane.add(lblSupplierNumber);

		txtSuplName = new JTextField();
		txtSuplName.setBounds(200, 41, 206, 36);
		contentPane.add(txtSuplName);
		txtSuplName.setColumns(10);

		txtRegion = new JTextField();
		txtRegion.setColumns(10);
		txtRegion.setBounds(200, 114, 206, 36);
		contentPane.add(txtRegion);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(200, 186, 206, 36);
		contentPane.add(txtEmail);

		txtSuNum = new JTextField();
		txtSuNum.setColumns(10);
		txtSuNum.setBounds(200, 255, 206, 36);
		contentPane.add(txtSuNum);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Suppliers ('Name','Region','Email Address','Supplier_Number' ) values(?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtSuplName.getText());
					pst.setString(2, txtRegion.getText());
					pst.setString(3, txtEmail.getText());
					pst.setString(4, txtSuNum.getText());

					pst.execute();
					// tableCars.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Saved!");
					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSave.setBounds(10, 327, 89, 36);
		Image img1 = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSave.setIcon(new ImageIcon(img1));
		contentPane.add(btnSave);

		JButton btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnclose.setBounds(317, 327, 89, 36);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnclose.setIcon(new ImageIcon(img2));
		contentPane.add(btnclose);
	}

}
