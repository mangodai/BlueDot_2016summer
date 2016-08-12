package edu.JTextArea;

import java.awt.Font;

import javax.swing.JTextArea;
/**
 * �����Լ����ı�����
 * @author MangoDai
 *	       2016��8��12��
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
