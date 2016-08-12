package edu.JTextArea;

import java.awt.Font;

import javax.swing.JTextArea;
/**
 * 创建自己的文本区域
 * @author MangoDai
 *	       2016年8月12日
 */
public class MyTextArea extends JTextArea {

	private static final long serialVersionUID = -4263626087573169630L;

	public MyTextArea(String text) {
		super();
		Font f = new Font("Serif",1,15);
		this.setFont(f);
		this.setText(text);
	}
}
