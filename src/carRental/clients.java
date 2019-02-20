/* 
 Author: simba
 Topic: Impala Car Rental System
*/

package carRental;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class clients extends JFrame {

	private JPanel contentPane;
	private JTable tableAvailable;
	public JTable tableMax;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clients frame = new clients();
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

	public clients() {
		super("Clients");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					String query = "Select * from Cars where Status='Available'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableAvailable.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 521);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmAbout = new JMenuItem("About");
		menuBar.add(mntmAbout);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuBar.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 55, 740, 395);
		contentPane.add(tabbedPane);

		JPanel panelAvailable = new JPanel();
		tabbedPane.addTab("Available Cars", null, panelAvailable, null);
		panelAvailable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 26, 702, 288);
		panelAvailable.add(scrollPane);

		tableAvailable = new JTable();
		scrollPane.setViewportView(tableAvailable);

		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RentOut outrent = new RentOut();
				outrent.setVisible(true);

			}
		});
		btnRent.setBounds(20, 325, 89, 38);
		Image img2 = new ImageIcon(this.getClass().getResource("/rentout.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnRent.setIcon(new ImageIcon(img2));

		panelAvailable.add(btnRent);
	}
}
