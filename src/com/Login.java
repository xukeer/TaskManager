package com;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import UserClass.DBManage;

public class Login {
	static Frame frame = null;
	static Button button = null;
	static Checkbox checkBox = null;
	static TextField password = null;
	static TextField name = null;
	static DBManage dbManage = null;

	public static void main(String[] argv) {
		try {
			dbManage = new DBManage();
			// dbManage.queryData("insert into
			// currentUser(userName,passWord,isLogin)values('xqu','123456',1)");
			dbManage.insertData("insert into TaskMain(userId,taskName,startTime,endTime,planTime,userTime) values"
					+ "(2,'add','2015-10-12 12:23:30','2015-10-12 12:23:30',100,34) ");

			// ResultSet rs = dbManage.queryData("select * from CurrentUser");
			ResultSet rs = dbManage.queryData("select * from TaskMain");
			while (rs.next()) {
				String name = rs.getString("taskName");
				System.out.println(name);
			}
			init_login();
			init();
			// dbManage = new DBManage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void init() {
		frame = new Frame();
		name = new TextField(1);
		password = new TextField(1);
		checkBox = new Checkbox("¼Ç×¡ÃÜÂë");
		button = new Button("Log in");

		frame.setBounds(new Rectangle(200, 200, 450, 300));
		frame.setLayout(null);
		frame.setBackground(new Color(0X96, 0X96, 0x96));

		name.setBounds(new Rectangle(130, 130, 200, 20));

		password.setBounds(new Rectangle(130, 160, 200, 20));
		password.setEchoChar('*');

		checkBox.setBounds(new Rectangle(130, 190, 100, 20));

		button.setBounds(new Rectangle(140, 220, 180, 25));

		frame.add(name);
		frame.add(password);
		frame.add(checkBox);
		frame.add(button);
		frame.setVisible(true);
		init_event();
	}

	public void init11() {

	}

	private static void init_event() {
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (user_login()) {
						login(true, name.getText().trim(), password.getText().trim());
					}
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private static void login(boolean rememberCurrentUser, String userName, String passWord) {
		try {
			new Main(dbManage, userName);
			if (rememberCurrentUser) {
				remember_current_user();
			}
			frame.dispose();
		} catch (Throwable e) {
		}
	}

	private static boolean user_login() {
		boolean flag = false;
		try {
			String userName = name.getText().trim();
			String passWord = password.getText().trim();
			String sql = "";
			sql = "select COUNT(*) from UserMain where userName='" + userName + "' and passWord='" + passWord + "'";
			int count = dbManage.queryData(sql).getInt("COUNT");
			flag = count == 1 ? true : false;
		} catch (Exception e) {
		}
		return flag;
	}

	private static void init_login() {
		try {
			String sql = "select * from CurrentUser";
			ResultSet rs = dbManage.queryData(sql);
			int autoLogin = rs.getInt("autoLogin");
			String userName = rs.getString("userName");
			String passWord = rs.getString("passWord");
			if (autoLogin == 1) {
				login(false, userName, passWord);
			}
		} catch (Exception e) {
		}
	}

	// remember current user
	private static void remember_current_user() {
		String sql = "";
		try {
			boolean ishave = dbManage.queryData("select COUNT(*) as COUNT from CURRENTUSER").getInt("COUNT") == 0
					? false : true;
			String userName = name.getText().trim();
			String passWord = checkBox.getState() == true ? password.getText().trim() : "";
			boolean autoLogin = false;

			if (autoLogin) {
				if (ishave) {
					sql = "update CURRENTUSER Set userName='" + userName + "',passWord='" + passWord
							+ "',autoLogin='1'";
				} else {
					sql = "insert into CURRENTUSER(userName,passWord,autoLogin)values('" + userName + "','" + passWord
							+ "',1) ";
				}
			} else {
				if (ishave) {
					sql = "update CURRENTUSER Set userName='" + userName + "',passWord='" + passWord + "'";
				} else {
					sql = "insert into CURRENTUSER(userName,passWord)values('" + userName + "','" + passWord + "') ";
				}
			}
			dbManage.insertData(sql);
		} catch (Exception e) {
		}
	}
}
