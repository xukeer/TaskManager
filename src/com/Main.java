package com;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import UserClass.DBManage;

public class Main {
	private List list = null;
	private Frame frame = null;
	private Button button = null;
	public TaskDetail taskDeatail = null;
	private DBManage dbManage = null;
	private String userName = "";  

	public Main(DBManage dbManage, String userName) {
		init();
		this.userName = userName;
		this.dbManage = dbManage;
	}

	public void init() {
		frame = new Frame("Task Manage");
		frame.setBounds(new Rectangle(200, 200, 450, 450));
		frame.setLayout(null);
		button = new Button("+");
		button.setBounds(new Rectangle(70, 390, 300, 40));
		list = new List();
		list.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		list.setSize(300, 60);
		list.setBounds(new Rectangle(10, 30, 430, 350));
		list.add(get_all_task());
		initEvent();
		frame.add(list);
		frame.add(button);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private String get_all_task() {
		StringBuffer bf = new StringBuffer();
		try {
			String sql = "select * from TaskMain where userName='" + userName + "'";
			ResultSet rs = dbManage.queryData(sql);
			while (rs.next()) {
				bf.append(rs.getString("ID"));
				bf.append(rs.getString("taskName"));
			}
		}
		catch (Exception e) {
		}
		return bf.toString();
	}

	public String queryData(String sql) {
		String re = "";
		try {
			re = quertData(sql).getString("COUNT");
		}
		catch (Exception e) {
		}
		return re;
	}

	public void showMain() {
		frame.setVisible(true);
	}

	public ResultSet quertData(String sql) {
		ResultSet rs = null;
		try {
			rs = dbManage.queryData(sql);
		}
		catch (Exception e) {

		}
		return rs;
	}

	private void initEvent() {
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// if(taskDeatail==null){
					taskDeatail = new TaskDetail(2, Main.this);
					frame.setVisible(false);
					frame.dispose();
				}
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					list.remove(list.getSelectedItem());
				}
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String item = list.getSelectedItem();
				System.out.println(item + "  " + e.getClickCount());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("mouseMoved");
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// String item = list.getSelectedItem();
				System.out.println("button.mouseClicked");
			}

		});
	}
}
