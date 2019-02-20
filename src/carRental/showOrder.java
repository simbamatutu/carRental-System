/* 
Author: simba
Topic: Impala Car Rental System
*/

package carRental;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class showOrder extends JFrame {

	private JPanel contentPane;
	public JLabel lblorder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showOrder frame = new showOrder();
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
	public showOrder() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				char num;

				String alphabet = "01346789ABWCDEFGHZX";
				int N = alphabet.length();

				Random r = new Random();

				String text = "";
				for (int i = 0; i < 6; i++) {
					text += String.valueOf(alphabet.charAt(r.nextInt(N))); // Shouldn't even implicitly need

				}

				lblorder.setText(String.valueOf(text));
				//
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(53, 29, 234, 153);
		Image img1 = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		img1 = img1.getScaledInstance(234, 153, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(img1));
		contentPane.add(lblNewLabel);

		JLabel lblOrderNumber = new JLabel("ORDER NUMBER:");
		lblOrderNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrderNumber.setBounds(84, 222, 196, 40);
		contentPane.add(lblOrderNumber);

		lblorder = new JLabel("");
		lblorder.setForeground(Color.GREEN);
		lblorder.setFont(new Font("Traditional Arabic", Font.PLAIN, 50));
		lblorder.setBounds(66, 288, 221, 64);
		contentPane.add(lblorder);
	}

}
