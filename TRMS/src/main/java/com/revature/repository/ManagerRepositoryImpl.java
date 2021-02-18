package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Manager;
import com.revature.model.Manager;
import com.revature.util.ConnectionClosers;
import com.revature.util.ConnectionFactory;

public class ManagerRepositoryImpl implements ManagerRepository {

	@Override
	public Manager findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Manager> findAll() {
		List<Manager> managers = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;

		final String SQL = "select * from manager";

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);

			while (set.next()) {
				// extract data from the row the cursor is positioned at
				managers.add(new Manager(set.getInt(1), set.getInt(2), set.getString(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionClosers.close(conn);
			ConnectionClosers.close(stmt);
			ConnectionClosers.close(set);
		}

		return managers;
	}

}
