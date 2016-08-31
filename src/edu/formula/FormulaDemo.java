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
				JOptionPane.showMessageDialog(null, "命题变量个数：" + numbers
						+ ",不可以再多了,爆屏了");
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
				JOptionPane.showMessageDialog(null, "命题变量个数：" + numbers
						+ ",不可以再少了");
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
		head.setBorder(BorderFactory.createTitledBorder("增加或减少——命题变量"));
		head.add(new JLabel("命题变量:"));
		JButton jb1 = new JButton("增加");
		jb1.addActionListener(increase);
		JButton jb2 = new JButton("减少");
		jb2.addActionListener(reduse);
		head.add(jb1);
		head.add(jb2);
		return head;
	}

	private JPanel initi(int times) {
		if (center == null)
			center = new JPanel();
		addProposition(times);
		center.setBorder(BorderFactory.createTitledBorder("子面板"));
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
			// 初始化时，父容器大小为首选大小，实际大小为0
			containerwidth = jsp.getPreferredSize();
		} else {
			// 界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
			containerwidth = jsp.getSize();
		}
		// 计算表格总体宽度 getTable().
		int allwidth = table.getIntercellSpacing().width;
		for (int j = 0; j < table.getColumnCount(); j++) {
			// 计算该列中最长的宽度
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
			// 计算表头的宽度
			int headerwidth = table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(
							table,
							table.getColumnModel().getColumn(j).getIdentifier(),
							false, false, -1, j).getPreferredSize().width;
			// 列宽至少应为列头宽度
			max += headerwidth;
			// 设置列宽
			table.getColumnModel().getColumn(j).setPreferredWidth(max);
			// 给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
			allwidth += max + table.getIntercellSpacing().width;
		}
		allwidth += table.getIntercellSpacing().width;
		// 如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 自动调整尺寸模式
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
			// System.out.println("该合适公式是 "+str);
			String suffix[] = new String[str.length];
			int count = 0;
			for (String i : str)
				suffix[count++] = toSuffix(i + "=");// 追加=

			StringBuffer strBuf;
			boolean[] letterExist = new boolean[26];// 字母（变量）是否存在
			for (int i = 0; i < 26; i++)
				letterExist[i] = false;
			for (int i = 0; i < str[0].length(); i++) {
				int k = (str[0].charAt(i) - 'A');
				if (k >= 0 && k <= 25)
					letterExist[k] = true;
			}
			int letterCount = 0;// 变量的个数
			char[] letter = new char[26];// 字母表
			for (int i = 0; i < 26; i++) {
				if (letterExist[i]) {
					letter[letterCount] = (char) (i + 'A');
					letterCount++;
					System.out.print((char) (i + 'A') + " ");
				}
			}
			System.out.print("Key");
			System.out.println();
			int times = (int) Math.pow(2, letterCount);// 循环次数（为变量赋值次数）
			row = new String[times][letterCount + 3];
			for (count = 0; count < str.length; count++) {// 每次的数据 运行
				for (int i = 0; i < times; i++) {
					strBuf = new StringBuffer(suffix[count]);
					int num = i;// 当前赋的值
					for (int j = 0; j < letterCount; j++) {
						int bit = num % 2;// 当前位的值
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
			System.out.println("你输入的合适公式的错误的，请重试");
			if (e.getMessage().equals("") || e.getMessage() == null)
				System.out.println("这里是错误信息 发生未知错误");

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
					throw new Exception("合适公式出现错误（可能是缺少变量）");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(true) && num2.equals(true))
					stack.push(true);
				else
					stack.push(false);
			} else if (str.charAt(i) == '&') {
				if (stack.size() < 2)
					throw new Exception("合适公式出现错误（可能是缺少变量）");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(false) && num2.equals(false))
					stack.push(false);
				else
					stack.push(true);
			} else if (str.charAt(i) == '>') {
				if (stack.size() < 2)
					throw new Exception("合适公式出现错误（可能是缺少变量）");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(false) && num2.equals(true))
					stack.push(false);
				else
					stack.push(true);
			} else if (str.charAt(i) == '-') {
				if (stack.size() < 2)
					throw new Exception("合适公式出现错误（可能是缺少变量）");
				num1 = stack.pop();
				num2 = stack.pop();
				if (num1.equals(num2))
					stack.push(true);
				else
					stack.push(false);
			} else if (str.charAt(i) == '^') {
				if (stack.size() < 1)
					throw new Exception("合适公式出现错误（可能是缺少变量）");
				num1 = stack.pop();
				if (num1.equals(true))
					stack.push(false);
				else
					stack.push(true);
			}
			i++;
		}
		if (stack.size() != 1)
			throw new Exception("合适公式出现错误（可能是缺少连接词）");
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
		stack.push('=');// 压入栈底字符
		int i = 0;
		char ch = str.charAt(i);
		while (ch != '=') {
			if (ch == ' ')
				;// 忽略空格
			else if (ch == '(')
				stack.push('(');
			else if (ch == ')') {
				while (stack.peek() != '(') {
					if (stack.peek() == '=')
						throw new Exception("括号不匹配（可能是缺少“(”）");
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
					throw new Exception("出现不支持的符号（可能是括号等出现全角模式）");
				strBuf.append(ch);
			}
			ch = str.charAt(++i);
		}
		ch = stack.pop();
		while (ch != '=') {
			if (ch == '(')
				throw new Exception("括号不匹配（可能是缺少“)”）");
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
		// tableModel.setRowCount(0);// 清除原有行
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
						stb.append(" 析取");
					} else if (j == 1) {
						stb.append(" 合取");
					} else if (j == 2) {
						stb.append(" 蕴含");
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
