package BlueDot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SetLab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel headPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	private JTextArea jt = new JTextArea();
	private JTextField jtA=new JTextField(20);
	private JTextField jtB=new JTextField(20);
	private JLabel label=new JLabel("输入数据，用空格隔开，点击确定");
	private JPanel dataText=new JPanel();
	private int dataA[]=null;
	private int dataB[]=null;
	private JButton sure;
	protected  final String LINE=System.getProperty("line.separator");
	
	SetLab(){
		super();
		this.setBorder(BorderFactory.createTitledBorder("离散实验"));
		this.setLayout(new BorderLayout());
		this.add(headPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.Initi();
	}
	private void Initi(){		
		headPanel.setBorder(BorderFactory.createTitledBorder("增加数据"));
		centerPanel.setBorder(BorderFactory.createTitledBorder("子面板"));
		headIniti();
		centerIniti();
	}
	private void headIniti() {
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jtA.setBorder(BorderFactory.createTitledBorder("集合A"));
		headPanel.add(jtA);
		jtB.setBorder(BorderFactory.createTitledBorder("集合B"));
		headPanel.add(jtB);
		String textA=jtA.getText();
//		headPanel.add(buildSure());
		String textB=jtB.getText();
		headPanel.add(buildSure());
		System.out.println(textA);
		System.out.println(textB);
	}
	private void centerIniti() {
		// TODO Auto-generated method stub
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(label,BorderLayout.NORTH);
		centerPanel.add(dataText,BorderLayout.CENTER);
		Font f = new Font("Serif",1,20);//1 为加粗，20为字体大小
		jt.setFont(f);
		jt.setText("");
        dataText.setLayout(new BorderLayout(50,50));
        dataText.add(jt,BorderLayout.CENTER);
        jt.setCaretPosition(0);//视角跳上
        jt.setEnabled(false);
        jt.updateUI();
    }
	ActionListener act = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String strA[]=jtA.getText().split(" +");
			String strB[]=jtB.getText().split(" +");
			dataA=new int[strA.length];
			dataB=new int[strB.length];
			jt.setText("");
			StringBuffer dataStr=new StringBuffer("集合元素A:");
			addData(strA,strB, dataStr);
			jt.append(LINE+dataStr.toString()+LINE);
			jt.append(new SetDemo(dataA,dataB).getStr());
		}

		private void addData(String[] strA,String[] strB, StringBuffer dataStr) {
			for(int i=0;i<strA.length;i++){
				try {	
				    dataA[i] = Integer.parseInt(strA[i]);//数据不对会抛出异常
				    dataStr.append(dataA[i]+"  ");                             
				} catch (RuntimeException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, e1.getMessage()+"待排序数据有错误", "有错误", JOptionPane.ERROR_MESSAGE);
				    jtA.setText("");
				}	
			}
			dataStr.append(LINE+"集合元素B:"); 
			for(int i=0;i<strB.length;i++){
				try {	
				    dataB[i] = Integer.parseInt(strB[i]);//数据不对会抛出异常
				    dataStr.append(dataB[i]+"  ");                             
				} catch (RuntimeException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, e1.getMessage()+"待排序数据有错误", "有错误", JOptionPane.ERROR_MESSAGE);
				    jtB.setText("");
				}	
			}			
		}
	};
	private JButton buildSure() {
		// TODO Auto-generated method stub
		sure = new JButton("确定");
		sure.addActionListener(act);
	return sure;
	}
}
