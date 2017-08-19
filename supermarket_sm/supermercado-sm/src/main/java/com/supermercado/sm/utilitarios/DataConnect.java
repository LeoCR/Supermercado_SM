
package com.supermercado.sm.utilitarios;
import java.sql.Connection;
import java.sql.DriverManager;
public class DataConnect {
    public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/supermarket_sm", "root", "123");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
