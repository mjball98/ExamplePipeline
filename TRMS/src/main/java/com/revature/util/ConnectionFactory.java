package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {

		Connection conn = null;

		try {

			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(
					"jdbc:postgresql://jan25instance.cbqll05ud0pv.us-east-2.rds.amazonaws.com:5432/postgres",
					"postgres", "password");
			/*
			 * conn = DriverManager.getConnection( System.getenv("dburl"),
			 * System.getenv("dbusername"), System.getenv("dbpassword") );
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
