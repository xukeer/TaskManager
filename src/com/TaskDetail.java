package com;

import java.awt.*;
import java.awt.event.*;


public class TaskDetail {
	int taskID;

	private Frame frame = null;
	private Button button = null;
	private Button btnShow=null;
	private TextArea text=null;
	private Main main=null;

	public TaskDetail(int id) {
		this.taskID = id;
		initComponent();
	}

	public TaskDetail(int id,Main main) {
		this.taskID = id;
		initComponent();
		this.main=main;
	}
	
	
	public void initComponent() {
		frame = new Frame("TaskDetail");
		frame.setBounds(new Rectangle(200, 200, 450, 490));
		frame.setLayout(null);
		text=new TextArea("",20, 20,TextArea.SCROLLBARS_VERTICAL_ONLY);
		text.setText("dsds");
		//	text.set
		text.setBounds(new Rectangle(10,38,400,400));
		button = new Button("start");
		button.setBounds(new Rectangle(100,450,100,20));
		
		button.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				String str=main.queryData("select COUNT(*)as COUNT from Student");
				System.out.println(str);
			}
		});
		btnShow=new Button("Show Main");
		btnShow.setBounds(new Rectangle(220,450,100,20));
		btnShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				main.showMain();
			}
		});
		frame.add(btnShow);
		frame.add(text);
		frame.add(button);
		frame.setVisible(true);
	}

	public void showTaskDetail() {
		frame.setVisible(true);
	}

	public void HideTaskDeatail() {
		frame.setVisible(false);
	}
}
