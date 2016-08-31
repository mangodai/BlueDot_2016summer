package BlueDot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import edu.JTextField.myTextField;
/**
 * DataSort.java
 * @author Deri Aug 26, 201612:06:12 PM
 *对数据排序
 */
public class DataSort extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel headPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
//	private JTextField jtf=new JTextField();
	private myTextField jtf=new myTextField();
	/**
	 * 2016年8月22日 22:45:18
	 * 创建一个数据输入类
	 */
//	private JFormattedTextField jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JRadioButton jr[]=new JRadioButton[4];
	private JLabel label=new JLabel("输入数据，用空格隔开，点击确定，选择算法");
	public static String name[]={"快速排序","冒泡排序","桶排序","希尔排序"};
	private JPanel dataText=new JPanel();
	private ButtonGroup group = new ButtonGroup();
	private int sortData[]=null;
	private JButton sure;

	private RadioButtonListener radioButtonListener=new RadioButtonListener();;
	//构造函数默认是public 其他的不一定可以new
	DataSort(){
		super();
		this.setBorder(BorderFactory.createTitledBorder("数据排序"));
		this.setLayout(new BorderLayout());
		this.add(headPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.Initi();
	}
	private void Initi(){		
		headPanel.setBorder(BorderFactory.createTitledBorder("增加数据和选择排序"));
		centerPanel.setBorder(BorderFactory.createTitledBorder("子面板"));
		headIniti();
		centerIniti();
	}
	private void centerIniti() {
		// TODO Auto-generated method stub
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(label,BorderLayout.NORTH);
		centerPanel.add(dataText,BorderLayout.CENTER);
	}
	private void headIniti(){
		headPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		jtf.setColumns(20);
//		jtfKeyListener();//已经抽象
		headPanel.add(jtf);
		String text=jtf.getText();
		System.out.println(text);
		headPanel.add(buildSure());
		for(int i = 0;i<name.length;i++){
			headPanel.add(buildRadio(name[i],i));
		}
//		group.setSelected(m, b);
	}

	
	public class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton temp=(JRadioButton)e.getSource();
            if(temp.isSelected()){
                System.out.println(temp.getText());
	           	startData(temp.getText());
            }       
        }


    }
    private void startData(String name) {
    	String string=new SortDemo().sorting(name,sortData);
//	           	System.out.println(sortData[0]);
    	dataText.removeAll();
    	centerPanel.updateUI();//加载更新
    	
    	JTextArea jt = new JTextArea();
    	Font f = new Font("Serif",1,20);//1 为加粗，20为字体大小
    	jt.setFont(f);
    	jt.setText("");
    	dataText.setLayout(new BorderLayout(50,50));
    	dataText.add(jt,BorderLayout.CENTER);
    	jt.append(string);
    	jt.setCaretPosition(0);
    	jt.setEnabled(false);
    }
	private JRadioButton buildRadio(String name,int i) {
		// TODO Auto-generated method stub

		if(jr[i]==null){
			jr[i]=new JRadioButton(name);
			jr[i].setName(name);
			jr[i].addActionListener(radioButtonListener);
			group.add(jr[i]);
		}
		if(name.equals("冒泡排序")){
			jr[i].setSelected(true);//默认选择,后续开发`			
		}
	return jr[i];
	}
	private JButton buildSure(){
		sure = new JButton("确定");
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = jtf.getText().trim();
				if(temp.equals("")){
					return ;
				}
				String data[]=temp.split(" +");
				sortData=new int[data.length];
				for(int i=0;i<data.length;i++){
					if(data[i]==null){
						continue;
					}else{
						try {	
						    sortData[i] = Integer.parseInt(data[i]);//数据不对会抛出异常
						    System.out.println(sortData[i]);                              
						} catch (RuntimeException e1) {
						    e1.printStackTrace();
						    if(e1.getMessage()==""){
						    	JOptionPane.showMessageDialog(null, "开头多了一个空格,无 数 据 ！");
						    }else{
						    	JOptionPane.showMessageDialog(null, e1.getMessage()+"待排序数据有错误", "有错误", JOptionPane.ERROR_MESSAGE);
						    }
						    jtf.setText("");
						    throw new RuntimeException();
						}		
					}
				}
				String s = getSelectJRadio();
				System.out.println(s);
				startData(s);
			}

			private String getSelectJRadio() {
				String s=null;
				for(JRadioButton i:jr){
					if(i.isSelected()){
						s=i.getName();
						//to do setname
					}
				}
				return s;
			}
		});
	return sure;
	}
}
