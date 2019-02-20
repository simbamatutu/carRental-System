/* 
 Author: simba
 Topic: Impala Car Rental System
*/

package carRental;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnection {

	Connection connection = null;

	public static Connection dbconnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(
					"jdbc:sqlite:F:\\Users\\Godwin Matutu\\Desktop\\operations\\java\\ObjectiveLarry\\carRentalDB\\carRentalSystem.db");

			return connection;
		} catch (Exception e) {

			System.out.printf("%s", e);
			return null;
		}

	}
}
