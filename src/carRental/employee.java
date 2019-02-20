/* 
Author: simba
Topic: Impala Car Rental System
*/

package carRental;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class employee extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tableClients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee frame = new employee();
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

	public employee() {
		super("Employees' Panel");
		connection = Dbconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 531);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mtnAbout = new JMenuItem("About");
		menuBar.add(mtnAbout);

		JMenuItem mtnExit = new JMenuItem("Exit");
		mtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuBar.add(mtnExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 55, 755, 395);
		contentPane.add(tabbedPane);

		JPanel panelAvailable = new JPanel();
		panelAvailable.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					String query = "Select * from Cars";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.addTab("Available Cars", null, panelAvailable, null);
		panelAvailable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 730, 303);
		panelAvailable.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCar Caradd = new addCar();
				Caradd.setVisible(true);
			}
		});
		btnAdd.setBounds(20, 322, 89, 38);
		Image img = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img = img.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnAdd.setIcon(new ImageIcon(img));
		panelAvailable.add(btnAdd);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCar carsearch = new searchCar();
				carsearch.setVisible(true);
			}
		});
		btnSearch.setBounds(138, 322, 97, 38);
		Image img1 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSearch.setIcon(new ImageIcon(img1));

		panelAvailable.add(btnSearch);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String cell = table.getModel().getValueAt(row, 1).toString();
				String sql = "DELETE FROM Cars WHERE Model  =?";

				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Cars";
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Refresh Error");
					} finally {
						try {
							// rs.close();
							pst.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "e");
					System.out.println(e2);

				}
			}
		});
		btnDelete.setBounds(256, 322, 89, 38);
		Image img2 = new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnDelete.setIcon(new ImageIcon(img2));
		panelAvailable.add(btnDelete);

		JPanel panelClients = new JPanel();
		panelClients.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					String query = "Select * from Clients";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableClients.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.addTab("Clients", null, panelClients, null);
		panelClients.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 730, 299);
		panelClients.add(scrollPane_1);

		tableClients = new JTable();
		scrollPane_1.setViewportView(tableClients);

		JButton btnaddclient = new JButton("Add");
		btnaddclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient ClientAdd = new addClient();
				ClientAdd.setVisible(true);
			}
		});
		btnaddclient.setBounds(20, 321, 89, 38);
		Image img3 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img3 = img3.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnaddclient.setIcon(new ImageIcon(img3));

		panelClients.add(btnaddclient);

		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchClient clientsearch = new searchClient();
				clientsearch.setVisible(true);
			}
		});
		btnSearch_1.setBounds(137, 321, 94, 38);
		Image img4 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img4 = img4.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSearch_1.setIcon(new ImageIcon(img4));
		panelClients.add(btnSearch_1);

		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableClients.getSelectedRow();
				String cell = tableClients.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM Employees WHERE Name =?";

				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, (String) cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Employees";
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						tableClients.setModel(DbUtils.resultSetToTableModel(rs));

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Refresh Error");
					} finally {
						try {
							// rs.close();
							pst.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "e3");
					System.out.println(e3);

				}
			}
		});
		btnDelete_1.setBounds(249, 321, 89, 38);
		Image img5 = new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		img5 = img5.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnDelete_1.setIcon(new ImageIcon(img5));
		panelClients.add(btnDelete_1);
	}
}
