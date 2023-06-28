package com.day12.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PannelTest  extends JFrame 
      implements ActionListener{
	private JTextField tf;
	public PannelTest() {
		//super("PannelTest");
		setTitle("JFrame");
		setLayout(new FlowLayout());
		JButton btn1 = new JButton("버튼1");
		JButton btn2 = new JButton("버튼2");
		JButton btn3 = new JButton("버튼3");
		 tf = new JTextField(20);
		add(btn1); 		add(btn2);		add(btn3);
		add(tf);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		setSize(300, 400);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		tf.setText(str);
		//System.out.println(str +" 이벤트 발생");
		if(str.equals("버튼1")) {
		    getContentPane().setBackground(Color.RED);
		}else if(str.equals("버튼2")) {
			getContentPane().setBackground(Color.PINK);
		}else {
			getContentPane().setBackground(Color.YELLOW);
		}
	
		
	}
	public static void main(String[] args) {
		new PannelTest();

	}

	

}






