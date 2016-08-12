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
	private JLabel label=new JLabel("�������ݣ��ÿո���������ȷ��");
	private JPanel dataText=new JPanel();
	private int dataA[]=null;
	private int dataB[]=null;
	private JButton sure;
	protected  final String LINE=System.getProperty("line.separator");
	
	SetLab(){
		super();
		this.setBorder(BorderFactory.createTitledBorder("��ɢʵ��"));
		this.setLayout(new BorderLayout());
		this.add(headPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.Initi();
	}
	private void Initi(){		
		headPanel.setBorder(BorderFactory.createTitledBorder("��������"));
		centerPanel.setBorder(BorderFactory.createTitledBorder("�����"));
		headIniti();
		centerIniti();
	}
	private void headIniti() {
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jtA.setBorder(BorderFactory.createTitledBorder("����A"));
		headPanel.add(jtA);
		jtB.setBorder(BorderFactory.createTitledBorder("����B"));
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
		Font f = new Font("Serif",1,20);//1 Ϊ�Ӵ֣�20Ϊ�����С
		jt.setFont(f);
		jt.setText("");
        dataText.setLayout(new BorderLayout(50,50));
        dataText.add(jt,BorderLayout.CENTER);
        jt.setCaretPosition(0);//�ӽ�����
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
			StringBuffer dataStr=new StringBuffer("����Ԫ��A:");
			addData(strA,strB, dataStr);
			jt.append(LINE+dataStr.toString()+LINE);
			jt.append(new SetDemo(dataA,dataB).getStr());
		}

		private void addData(String[] strA,String[] strB, StringBuffer dataStr) {
			for(int i=0;i<strA.length;i++){
				try {	
				    dataA[i] = Integer.parseInt(strA[i]);//���ݲ��Ի��׳��쳣
				    dataStr.append(dataA[i]+"  ");                             
				} catch (RuntimeException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, e1.getMessage()+"�����������д���", "�д���", JOptionPane.ERROR_MESSAGE);
				    jtA.setText("");
				}	
			}
			dataStr.append(LINE+"����Ԫ��B:"); 
			for(int i=0;i<strB.length;i++){
				try {	
				    dataB[i] = Integer.parseInt(strB[i]);//���ݲ��Ի��׳��쳣
				    dataStr.append(dataB[i]+"  ");                             
				} catch (RuntimeException e1) {
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null, e1.getMessage()+"�����������д���", "�д���", JOptionPane.ERROR_MESSAGE);
				    jtB.setText("");
				}	
			}			
		}
	};
	private JButton buildSure() {
		// TODO Auto-generated method stub
		sure = new JButton("ȷ��");
		sure.addActionListener(act);
	return sure;
	}
}
