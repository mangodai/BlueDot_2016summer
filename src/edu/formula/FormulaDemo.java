package edu.Formula;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FormulaDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Proposition> list = null;
	private JPanel center = null;
	private JTable table = null;
	private JScrollPane jsp = null;
	private DefaultTableModel tableModel = null;
	private Object[][] data = null;

	public FormulaDemo() {
		super();
		this.setLayout(new BorderLayout(10, 10));
		this.add(ButtonIniti(), BorderLayout.NORTH);
		this.add(initi(0), BorderLayout.CENTER);
	}

	ActionListener increase = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int numbers = list.size();
			if (numbers < 7) {
				numbers++;
				thansform(numbers);
			} else {
				JOptionPane.showMessageDialog(null, "�������������" + numbers
						+ ",�������ٶ���,������");
			}
		}
	};
	ActionListener reduse = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int numbers = list.size();
			if (numbers > 2) {
				numbers--;
				thansform(numbers);
			} else {
				JOptionPane.showMessageDialog(null, "�������������" + numbers
						+ ",������������");
			}
		}
	};

	private void thansform(int numbers) {
		this.center.removeAll();
		// jt.remove(center);
		this.add(initi(numbers), BorderLayout.CENTER);
		this.center.updateUI();
		// this.center.disable();
	}

	private JPanel ButtonIniti() {
		JPanel head = new JPanel();
		head.setLayout(new FlowLayout());
		head.setBorder(BorderFactory.createTitledBorder("���ӻ���١����������"));
		head.add(new JLabel("�������:"));
		JButton jb1 = new JButton("����");
		jb1.addActionListener(increase);
		JButton jb2 = new JButton("����");
		jb2.addActionListener(reduse);
		head.add(jb1);
		head.add(jb2);
		return head;
	}

	private JPanel initi(int times) {
		if (center == null)
			center = new JPanel();
		addProposition(times);
		center.setBorder(BorderFactory.createTitledBorder("�����"));
		jsp = new JScrollPane(addTable());
		jsp.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizeTable(true);
			}
		});
		center.setLayout(new BorderLayout(0 , 0));
		center.add(jsp, BorderLayout.CENTER);
		// tableHead();
		return center;
	}

	public void resizeTable(boolean bool) {
		Dimension containerwidth = null;
		if (!bool) {
			// ��ʼ��ʱ����������СΪ��ѡ��С��ʵ�ʴ�СΪ0
			containerwidth = jsp.getPreferredSize();
		} else {
			// ������ʾ�������������С�ı䣬ʹ��ʵ�ʴ�С��������ѡ��С
			containerwidth = jsp.getSize();
		}
		// ������������ getTable().
		int allwidth = table.getIntercellSpacing().width;
		for (int j = 0; j < table.getColumnCount(); j++) {
			// �����������Ŀ��
			int max = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				int width = table
						.getCellRenderer(i, j)
						.getTableCellRendererComponent(table,
								table.getValueAt(i, j), false, false, i, j)
						.getPreferredSize().width;
				if (width > max) {
					max = width;
				}
			}
			// �����ͷ�Ŀ��
			int headerwidth = table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(
							table,
							table.getColumnModel().getColumn(j).getIdentifier(),
							false, false, -1, j).getPreferredSize().width;
			// �п�����ӦΪ��ͷ���
			max += headerwidth;
			// �����п�
			table.getColumnModel().getColumn(j).setPreferredWidth(max);
			// �����������ȸ�ֵ���ǵ�Ҫ���ϵ�Ԫ��֮����������1������
			allwidth += max + table.getIntercellSpacing().width;
		}
		allwidth += table.getIntercellSpacing().width;
		// ������ʵ�ʿ�ȴ�С�������Ŀ�ȣ�����Ҫ�����ֶ���Ӧ�������ñ������Ӧ
		if (allwidth > containerwidth.width) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} else {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		}
	}

	private JTable addTable() {
		String[] rowNum = tableHead();
		tableModel = new DefaultTableModel(data, rowNum);
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// �Զ������ߴ�ģʽ
		tableModel.addRow(tableHead());
		addRow();
		// table.disable();
		table.setEnabled(false);
		return table;
	}

	private void addRow() {
		String s[] = getProposition();
		String temp[][] = showTruthTable(s);
		for (String i[] : temp) {
			// for(String k:i){
			// System.out.print(k.toString());
			// }
			tableModel.addRow(i);
			// System.out.println();
		}

	}

	public String[][] showTruthTable(String str[]) {
		String row[][] = null;
		try {
			// System.out.println("�ú��ʹ�ʽ�� "+str);
			String suffix[] = new String[str.length];
			int count = 0;
			for (String i : str)
				suffix[count++] = toSuffix(i + "=");// ׷��=

			StringBuffer strBuf;
			boolean[] letterExist = new boolean[26];// ��ĸ���������Ƿ����
			for (int i = 0; i < 26; i++)
				letterExist[i] = false;
			for (int i = 0; i < str[0].length(); i++) {
				int k = (str[0].charAt(i) - 'A');
				if (k >= 0 && k <= 25)
					letterExist[k] = true;
			}
			int letterCount = 0;// �����ĸ���
			char[] letter = new char[26];// ��ĸ��
			for (int i = 0; i < 26; i++) {
				if (letterExist[i]) {
					letter[letterCount] = (char) (i + 'A');
					letterCount++;
					System.out.print((char) (i + 'A') + " ");
				}
			}
			System.out.print("Key");
			System.out.println();
			int times = (int) Math.pow(2, letterCount);// ѭ��������Ϊ������ֵ������
			row = new String[times][letterCount + 3];
			for (count = 0; count < str.length; count++) {// ÿ�ε����� ����
				for (int i = 0; i < times; i++) {
					strBuf = new StringBuffer(suffix[count]);
					int num = i;// ��ǰ����ֵ
					for (int j = 0; j < letterCount; j++) {
						int bit = num % 2;// ��ǰλ��ֵ
						num /= 2;
						System.out.print((char) (bit + '0') + " ");
						if (row[i][j] == null) {
							row[i][j] = (char) (bit + '0') + "";
						}
						for (int k = 0; k < strBuf.length(); k++) {
							if (strBuf.charAt(k) == letter[j])
								strBuf.setCharAt(k, (char) (bit + '0'));
						}
					}
					if (runSuffix(strBuf.toString())) {
						System.out.print(" 1");
						if (row[i][letterCount + count] == null) {
							row[i][letterCount + count] = "1";
						}
					} else {
						System.out.print(" 0");
						if (row[i][letterCount + count] == null) {
							row[i][letterCount + count] = "0";
						}
					}
					System.out.println();
					// System.out.println(strBuf.toString());
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println("������ĺ��ʹ�ʽ�Ĵ���ģ�������");
			if (e.getMessage().equals("") || e.getMessage() == null)
				System.out.println("�����Ǵ�����Ϣ ����δ֪����");

		}
		// return
		return row;
	}

	private boolean runSuffix(String str) throws Exception {
		Stack<Boolean> stack = new Stack<Boolean>();
		int i = 0;
		Boolean num1, num2;
		while (str.charAt(i) != '=') {
			if (str.charAt(i) == '0')
				stack.push(false);
			else if (str.charAt(i) == '1')
				stack.push(true);
			else if (str.charAt(i) == '^') {
				if (stack.size() < 2)
					throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�ٱ�����");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(true) && num2.equals(true))
					stack.push(true);
				else
					stack.push(false);
			} else if (str.charAt(i) == '&') {
				if (stack.size() < 2)
					throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�ٱ�����");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(false) && num2.equals(false))
					stack.push(false);
				else
					stack.push(true);
			} else if (str.charAt(i) == '>') {
				if (stack.size() < 2)
					throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�ٱ�����");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(false) && num2.equals(true))
					stack.push(false);
				else
					stack.push(true);
			} else if (str.charAt(i) == '-') {
				if (stack.size() < 2)
					throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�ٱ�����");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(num2))
					stack.push(true);
				else
					stack.push(false);
			} else if (str.charAt(i) == '^') {
				if (stack.size() < 1)
					throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�ٱ�����");
				num1 = stack.pop();
				if (num1.equals(true))
					stack.push(false);
				else
					stack.push(true);
			}
			i++;
		}
		if (stack.size() != 1)
			throw new Exception("���ʹ�ʽ���ִ��󣨿�����ȱ�����Ӵʣ�");
		return stack.pop();
	}

	private int Precedence(char op) {
		switch (op) {
		case '-':
		case '>':
			return 1;
		case '&':
			return 2;
		case '^':
			return 3;
		case '~':
			return 4;
		case '(':
		case '=':
		default:
			return 0;
		}
	}

	private String toSuffix(String str) throws Exception {
		StringBuffer strBuf = new StringBuffer();
		Stack<Character> stack = new Stack<Character>();
		stack.push('=');// ѹ��ջ���ַ�
		int i = 0;
		char ch = str.charAt(i);
		while (ch != '=') {
			if (ch == ' ')
				;// ���Կո�
			else if (ch == '(')
				stack.push('(');
			else if (ch == ')') {
				while (stack.peek() != '(') {
					if (stack.peek() == '=')
						throw new Exception("���Ų�ƥ�䣨������ȱ�١�(����");
					strBuf.append(stack.pop());
				}
				stack.pop();
			} else if (ch == '^' || ch == '&' || ch == '~' || ch == '-'
					|| ch == '>') {
				char w = stack.peek();
				while (Precedence(w) >= Precedence(ch)) {
					strBuf.append(w);
					stack.pop();
					w = stack.peek();
				}
				stack.push(ch);
			} else {
				if (!((ch >= 'A' && ch <= 'Z') || ch == '0' || ch == '1'))
					throw new Exception("���ֲ�֧�ֵķ��ţ����������ŵȳ���ȫ��ģʽ��");
				strBuf.append(ch);
			}
			ch = str.charAt(++i);
		}
		ch = stack.pop();
		while (ch != '=') {
			if (ch == '(')
				throw new Exception("���Ų�ƥ�䣨������ȱ�١�)����");
			else {
				strBuf.append(ch);
				ch = stack.pop();
			}
		}
		strBuf.append('=');
		// System.out.println(strBuf);
		return strBuf.toString();
	}

	private String[] getProposition() {
		String s[] = new String[3];
		for (int j = 0; j < 3; j++) {
			String c[] = { "&", "^", ">" };
			StringBuilder stb = new StringBuilder();
			for (int k = 0; k < list.size(); k++) {
				Proposition p = list.get(k);
				stb.append(Character.valueOf(p.getName()).toString());
				if (k != list.size() - 1) {
					stb.append(c[j]);
				}
			}
			s[j] = stb.toString();
		}
		return s;
	}

	public String[] tableHead() {
		// int row = (int) Math.pow(2, list.size());
		// DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		// tableModel.setRowCount(0);// ���ԭ����
		final String s[] = new String[list.size() + 3];
		int i = 0;
		for (Proposition p : list) {
			s[i++] = Character.valueOf(p.getName()).toString();
		}
		for (int j = 0; j < 3; j++) {
			String c[] = { "V", "^", "->" };
			StringBuilder stb = new StringBuilder();
			for (int k = 0; k < list.size(); k++) {
				Proposition p = list.get(k);
				stb.append(Character.valueOf(p.getName()).toString());
				if (k != list.size() - 1) {
					stb.append(c[j]);
				} else {
					if (j == 0) {
						stb.append(" ��ȡ");
					} else if (j == 1) {
						stb.append(" ��ȡ");
					} else if (j == 2) {
						stb.append(" �̺�");
					} else {
					}
				}
			}
			s[i] = stb.toString();
			i++;
		}
		// tableModel.addRow(s);
		// table.invalidate();
		return s;
	}

	private void addProposition(int times) {
		char c = 'P';// P
		list = new ArrayList<Proposition>();
		if (times == 0) {
			list.add(new Proposition(false, c));
			list.add(new Proposition(false, (char) (c + 1)));
		} else {
			for (int i = 0; i < times; i++) {
				list.add(new Proposition(false, (char) (c + i)));
			}
		}
	}

}
