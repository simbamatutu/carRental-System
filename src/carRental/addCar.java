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

public class addCar extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField textModel;
	private JTextField txtColor;
	private JTextField txtReg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCar frame = new addCar();
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

	public addCar() {
		super("Add Car");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 53, 137, 31);
		contentPane.add(lblName);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 105, 137, 38);
		contentPane.add(lblModel);

		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(10, 165, 137, 31);
		contentPane.add(lblColor);

		JLabel lblRegstrationNumber = new JLabel("Regstration Number");
		lblRegstrationNumber.setBounds(10, 238, 137, 38);
		contentPane.add(lblRegstrationNumber);

		txtname = new JTextField();
		txtname.setBounds(230, 53, 185, 31);
		contentPane.add(txtname);
		txtname.setColumns(10);

		textModel = new JTextField();
		textModel.setBounds(230, 114, 185, 31);
		textModel.setText("");
		contentPane.add(textModel);
		textModel.setColumns(10);

		txtColor = new JTextField();
		txtColor.setBounds(230, 170, 185, 38);
		contentPane.add(txtColor);
		txtColor.setColumns(10);

		txtReg = new JTextField();
		txtReg.setBounds(230, 247, 185, 29);
		contentPane.add(txtReg);
		txtReg.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into Cars ('Name','Model','Color' ,'Regstration Number') values(?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtname.getText());
					pst.setString(2, textModel.getText());
					pst.setString(3, txtColor.getText());
					pst.setString(4, txtReg.getText());

					pst.execute();
					// tableCars.setModel(DbUtils.resultSetToTableModel(rs));
					JOptionPane.showMessageDialog(null, "Saved!");
					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnSave.setBounds(21, 339, 101, 31);
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
		btnBack.setBounds(314, 339, 101, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img2));
		contentPane.add(btnBack);
	}
}
