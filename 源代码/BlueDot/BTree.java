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

import edu.JTextField.myTextField;


public class BTree extends JPanel {
	private static final long serialVersionUID = 1L;
	private  String string = null;
	private JPanel headPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	
	private JTextArea jt = new JTextArea();
	
	private myTextField jtf=new myTextField(20);
	private JLabel label=new JLabel("输入数据，用空格隔开，点击确定");
	private JPanel dataText=new JPanel();
	private int newData[]=null;
	private JButton sure;

	//构造函数默认是public 其他的不一定可以new
	BTree(){
		super();
		this.setBorder(BorderFactory.createTitledBorder("二叉树"));
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
        
        jt.setCaretPosition(0);
        jt.setEnabled(false);
        jt.updateUI();
	}
	private void headIniti(){
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		headPanel.add(jtf);
		String text=jtf.getText();
		System.out.println(text);
		headPanel.add(buildSure());
	}	
	private JButton buildSure(){
		sure = new JButton("确定");
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtf.getText().equals("")){
					return ;
				}
				String data[]=jtf.getText().trim().split(" +");
				newData=new int[data.length];
				jt.setText("");
				StringBuffer dataStr=new StringBuffer("数据元素：");
				for(int i=0;i<data.length;i++){
					try {	
					    newData[i] = Integer.parseInt(data[i]);//数据不对会抛出异常
					    dataStr.append(newData[i]+"  ");
					    System.out.println(newData[i]);                              
					} catch (RuntimeException e1) {
					    e1.printStackTrace();
					    JOptionPane.showMessageDialog(null, e1.getMessage()+"待排序数据有错误", "有错误", JOptionPane.ERROR_MESSAGE);
					    jtf.setText("");
					}	
				}
				jt.append(dataStr.toString());
				creatBTree(newData);
			}

			private void creatBTree(int[] newData) {
				// TODO Auto-generated method stub
				BinaryTree bt = new BinaryTree();
				bt.buildTree(newData);
//				System.out.println(bt.printTree().toString());
				string = bt.printTree().toString();
				jt.append(string);
				jtf.setText("");
			}
		});
	return sure;
	}
}