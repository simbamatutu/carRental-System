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

public class searchClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtselect;
	private JTable tablesearchClient;
	private JComboBox comboBoxSelect;
	Connection connection = null;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchClient frame = new searchClient();
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
	public searchClient() {
		super("Search Client");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxSelect = new JComboBox();
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] { "Click---", "Name", "Surname", "Gender" }));
		comboBoxSelect.setBounds(20, 27, 173, 36);
		contentPane.add(comboBoxSelect);

		txtselect = new JTextField();
		txtselect.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String select = (String) comboBoxSelect.getSelectedItem();
					String query = "select * from Clients where " + select + " = ?";

					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, (String) txtselect.getText());
					System.out.println(query);
					ResultSet rs = ps.executeQuery();
					tablesearchClient.setModel(DbUtils.resultSetToTableModel(rs));
					ps.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error");
					ex.printStackTrace();
				}
			}

		});
		txtselect.setBounds(259, 27, 173, 36);
		contentPane.add(txtselect);
		txtselect.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 104, 427, 205);
		contentPane.add(scrollPane);

		tablesearchClient = new JTable();
		scrollPane.setViewportView(tablesearchClient);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(186, 320, 89, 39);
		Image img1 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnBack.setIcon(new ImageIcon(img1));
		contentPane.add(btnBack);
	}

}
