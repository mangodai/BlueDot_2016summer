package edu.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;
	/**
	 * myTextField.java
	 * 
	 * @author Deri Aug 22, 20168:06:27 PM 自定义一个只能输入数字的文本类
	 */
	public class myTextField extends JTextField {

		private static final long serialVersionUID = 1L;

		public myTextField() {
			super();
			myListener();
		}

		public myTextField(Document doc, String text, int columns) {
			super(doc, text, columns);
			myListener();
		}

		public myTextField(int columns) {
			super(columns);
			myListener();
		}

		public myTextField(String text, int columns) {
			super(text, columns);
			myListener();
		}

		public myTextField(String text) {
			super(text);
			myListener();
		}

		private void myListener() {
			this.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e){
					int keyChar=e.getKeyChar();
					if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

					}else if(keyChar==KeyEvent.VK_SPACE){
						
					}else{
					e.consume(); 
					}
					
				}
			});
		}
		
		

		/*
		 * protected Document createDefaultModel() { return new NumberDocument(); }
		 * 
		 * private static class NumberDocument extends PlainDocument {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * public void insertString(int offs, String str, AttributeSet a) throws
		 * BadLocationException {
		 * 
		 * if (str == null) { return; } char[] upper = str.toCharArray(); char[]
		 * temp = new char[upper.length]; for (int i = 0; i < upper.length; i++) {
		 * if (upper[i] >= '0' && upper[i] <= '9') { temp[i] = upper[i]; }
		 * 
		 * } super.insertString(offs, new String(temp).trim(), a); } }
		 */

}
