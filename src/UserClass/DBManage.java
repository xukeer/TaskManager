package UserClass;

import java.io.File;
import java.sql.*;

public class DBManage {

	Statement stmt = null;
	Connection conn = null;

	public DBManage() {
		try {
			Class.forName("org.sqlite.JDBC");
			String connStr = "jdbc:sqlite:test.db";
			File f = new File("test.db");
			if (!f.exists()) {
				createDB(connStr);
			} else {
				conn = DriverManager.getConnection(connStr);
				stmt = conn.createStatement();
			}
		} catch (Exception e) {
		}
	}

	public void insertData(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
		}
	}

	public ResultSet queryData(String sql) {
		ResultSet re = null;
		try {
			re = stmt.executeQuery(sql);
			return re;
		} catch (Exception e) {
		}
		return re;
	}

	public void closeDB() {
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createDB(String connStr) {
		try {
			String sql = "";
			conn = DriverManager.getConnection(connStr);
			stmt = conn.createStatement();
			sql = "CREATE TABLE CurrentUser" + "(userName TEXT NOT NULL," + "passWord TEXT NULL," + "isLogin INT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE UserMain " + "(userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ " userName TEXT NOT NULL, " + " passWord TEXT NULL )";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE TaskMain " + "(TaskId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
					+ "userId INTEGER NOT NULL, taskName TEXT NOT NULL,"
					+ "startTime DATETIME NULL ,endTime DATETIME NULL,planTime INTEGER null,userTime INTEGER NOT NULL)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE TaskDetail"
					+ "(dealId INTEGER PRIMART KEY autoInceatement NOT NULL, remark TEXT NOT NULL" + " dateTime Date NULL)";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
		}
	}
}
