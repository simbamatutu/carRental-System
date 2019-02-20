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

public class corporate extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					corporate frame = new corporate();
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
	private JTable tableCars;
	private JTable tableEmp;
	private JTable tableClients;
	private JTable tableSupplier;

	public corporate() {

		super("Corporate Panel");

		connection = Dbconnection.dbconnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 513);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mtnAbout = new JMenuItem("About");
		menuBar.add(mtnAbout);

		JMenuItem mtnexit = new JMenuItem("Exit");
		mtnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(EXIT_ON_CLOSE);

			}

		});
		menuBar.add(mtnexit);

		JMenuItem mntmNewMenuItem = new JMenuItem("");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 55, 755, 395);
		contentPane.add(tabbedPane);

		JPanel panelCars = new JPanel();
		panelCars.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					String query = "Select * from Cars";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableCars.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.addTab("Cars", null, panelCars, null);
		panelCars.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();

		scrollPane_2.setBounds(10, 11, 730, 303);
		panelCars.add(scrollPane_2);

		tableCars = new JTable();
		scrollPane_2.setViewportView(tableCars);

		JButton btnCarSearc = new JButton("Search");
		Image img = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img = img.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnCarSearc.setIcon(new ImageIcon(img));

		btnCarSearc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCar carsearch = new searchCar();
				carsearch.setVisible(true);
			}
		});
		btnCarSearc.setBounds(10, 322, 94, 38);
		panelCars.add(btnCarSearc);

		JButton btnCORadd = new JButton("Add");
		btnCORadd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addCar Caradd = new addCar();
				Caradd.setVisible(true);
			}
		});
		btnCORadd.setBounds(128, 322, 89, 38);
		Image img1 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img1 = img1.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnCORadd.setIcon(new ImageIcon(img1));
		panelCars.add(btnCORadd);

		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tableCars.getSelectedRow();
				String cell = tableCars.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM Cars WHERE Name  =?";

				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Cars";
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						tableCars.setModel(DbUtils.resultSetToTableModel(rs));

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
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "e");
					System.out.println(e);

				}

			}
		});
		btndelete.setBounds(254, 322, 89, 38);
		Image img2 = new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		img2 = img2.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btndelete.setIcon(new ImageIcon(img2));

		panelCars.add(btndelete);

		JPanel panelEmp = new JPanel();
		panelEmp.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					String query = "Select * from Employees";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableEmp.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		tabbedPane.addTab("Employees", null, panelEmp, null);
		panelEmp.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 730, 303);
		panelEmp.add(scrollPane);

		tableEmp = new JTable();
		scrollPane.setViewportView(tableEmp);

		JButton btnSearchEmp = new JButton("Search");
		btnSearchEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchEmployee EmployeeSearch = new SearchEmployee();
				EmployeeSearch.setVisible(true);
			}
		});
		btnSearchEmp.setBounds(20, 323, 94, 38);
		Image img3 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img3 = img3.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSearchEmp.setIcon(new ImageIcon(img3));
		panelEmp.add(btnSearchEmp);

		JButton btnEMAdd = new JButton("Add");
		btnEMAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee addemp = new addEmployee();
				addemp.setVisible(true);
			}
		});
		btnEMAdd.setBounds(129, 323, 89, 38);
		Image img4 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img4 = img4.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnEMAdd.setIcon(new ImageIcon(img4));
		panelEmp.add(btnEMAdd);

		JButton btnEMDel = new JButton("Delete");
		btnEMDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tableEmp.getSelectedRow();
				String cell = tableEmp.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM Employees WHERE Name =?";

				// String query = "select * from Suppliers where " + tselect + " =?";
				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, (String) cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Employees";
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						tableEmp.setModel(DbUtils.resultSetToTableModel(rs));

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

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "e");
					System.out.println(e);

				}
			}
		});
		btnEMDel.setBounds(262, 323, 89, 38);
		Image img5 = new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		img5 = img5.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnEMDel.setIcon(new ImageIcon(img5));

		panelEmp.add(btnEMDel);

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
		scrollPane_1.setBounds(10, 11, 730, 290);
		panelClients.add(scrollPane_1);

		tableClients = new JTable();
		scrollPane_1.setViewportView(tableClients);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchClient ClientsSearch = new searchClient();
				ClientsSearch.setVisible(true);
			}
		});
		btnSearch.setBounds(10, 313, 94, 38);

		Image img6 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img6 = img6.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSearch.setIcon(new ImageIcon(img6));

		panelClients.add(btnSearch);

		JButton btnCliAdd = new JButton("Add");
		btnCliAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient clientadd = new addClient();
				clientadd.setVisible(true);
			}
		});
		btnCliAdd.setBounds(120, 313, 89, 38);
		Image img8 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img8 = img8.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnCliAdd.setIcon(new ImageIcon(img8));
		panelClients.add(btnCliAdd);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tableClients.getSelectedRow();
				String cell = tableClients.getModel().getValueAt(row, 0).toString();
				String sql = "DELETE FROM Clients WHERE Name =?";

				System.out.println(cell);
				// String query = "select * from Suppliers where " + tselect + " =?";
				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, (String) cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Clients";
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
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "e1");
					System.out.println(e1);

				}
			}
		});
		btnNewButton.setBounds(229, 313, 89, 36);
		Image img7 = new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		img7 = img7.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(img7));
		panelClients.add(btnNewButton);

		JPanel panelSuppliers = new JPanel();
		panelSuppliers.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				try {
					String query = "Select * from Suppliers";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableSupplier.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e1) {
					e1.printStackTrace();

				}
			}
		});
		tabbedPane.addTab("Suppliers", null, panelSuppliers, null);
		panelSuppliers.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 730, 303);
		panelSuppliers.add(scrollPane_3);

		tableSupplier = new JTable();
		scrollPane_3.setViewportView(tableSupplier);

		JButton btnSupplieSearc = new JButton("Search");
		btnSupplieSearc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchSupplier suppliersearch = new searchSupplier();
				suppliersearch.setVisible(true);
			}
		});
		btnSupplieSearc.setBounds(10, 325, 94, 38);
		Image img9 = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		img9 = img9.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSupplieSearc.setIcon(new ImageIcon(img9));
		panelSuppliers.add(btnSupplieSearc);

		JButton btnSuppAdd = new JButton("Add");
		btnSuppAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addsupplier SupplierAdd = new addsupplier();
				SupplierAdd.setVisible(true);
			}
		});
		btnSuppAdd.setBounds(123, 325, 89, 38);
		Image img10 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img10 = img10.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSuppAdd.setIcon(new ImageIcon(img10));
		panelSuppliers.add(btnSuppAdd);

		JButton btnSupDel = new JButton("Delete");
		btnSupDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableSupplier.getSelectedRow();
				String cell = tableSupplier.getModel().getValueAt(row, 1).toString();
				String sql = "DELETE FROM Suppliers WHERE Region =?";

				// String query = "select * from Suppliers where " + tselect + " =?";
				try {
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, (String) cell);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deletion Done");
					String query = "select * from Suppliers";
					try {
						pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						tableSupplier.setModel(DbUtils.resultSetToTableModel(rs));

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
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "e1");
					System.out.println(e1);
				}
			}
		});
		btnSupDel.setBounds(231, 325, 89, 38);
		Image img11 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		img11 = img11.getScaledInstance(15, 20, java.awt.Image.SCALE_SMOOTH);
		btnSupDel.setIcon(new ImageIcon(img11));
		panelSuppliers.add(btnSupDel);
	}
}
