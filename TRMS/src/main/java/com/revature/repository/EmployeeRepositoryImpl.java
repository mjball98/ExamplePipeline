package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;

		final String SQL = "select * from employee2";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);

			while (set.next()) {
				// extract data from the row the cursor is positioned at
				employees.add(new Employee(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getInt(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
			ConnectionClosers.close(set);
		}

		return employees;
	}

	@Override
	public void updateUsername(Employee employee, String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update employee2 set username = (?) where username = (?)";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, employee.getUsername());
			stmt.setString(2, username);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}
	
	@Override
	public void updatePassword(Employee employee, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update employee2 set password = (?) where password = (?)";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, employee.getPassword());
			stmt.setString(2, password);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}
	
	
	@Override
	public void updateFullName(Employee employee, String fullname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update employee2 set full_name = (?) where full_name = (?)";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, employee.getFull_name());
			stmt.setString(2, fullname);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
		}

	}
	
	
	
	
	
	
}
