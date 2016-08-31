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
 *����������
 */
public class DataSort extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel headPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
//	private JTextField jtf=new JTextField();
	private myTextField jtf=new myTextField();
	/**
	 * 2016��8��22�� 22:45:18
	 * ����һ������������
	 */
//	private JFormattedTextField jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JRadioButton jr[]=new JRadioButton[4];
	private JLabel label=new JLabel("�������ݣ��ÿո���������ȷ����ѡ���㷨");
	public static String name[]={"��������","ð������","Ͱ����","ϣ������"};
	private JPanel dataText=new JPanel();
	private ButtonGroup group = new ButtonGroup();
	private int sortData[]=null;
	private JButton sure;

	private RadioButtonListener radioButtonListener=new RadioButtonListener();;
	//���캯��Ĭ����public �����Ĳ�һ������new
	DataSort(){
		super();
		this.setBorder(BorderFactory.createTitledBorder("��������"));
		this.setLayout(new BorderLayout());
		this.add(headPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.Initi();
	}
	private void Initi(){		
		headPanel.setBorder(BorderFactory.createTitledBorder("�������ݺ�ѡ������"));
		centerPanel.setBorder(BorderFactory.createTitledBorder("�����"));
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
//		jtfKeyListener();//�Ѿ�����
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
    	centerPanel.updateUI();//���ظ���
    	
    	JTextArea jt = new JTextArea();
    	Font f = new Font("Serif",1,20);//1 Ϊ�Ӵ֣�20Ϊ�����С
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
		if(name.equals("ð������")){
			jr[i].setSelected(true);//Ĭ��ѡ��,��������`			
		}
	return jr[i];
	}
	private JButton buildSure(){
		sure = new JButton("ȷ��");
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
						    sortData[i] = Integer.parseInt(data[i]);//���ݲ��Ի��׳��쳣
						    System.out.println(sortData[i]);                              
						} catch (RuntimeException e1) {
						    e1.printStackTrace();
						    if(e1.getMessage()==""){
						    	JOptionPane.showMessageDialog(null, "��ͷ����һ���ո�,�� �� �� ��");
						    }else{
						    	JOptionPane.showMessageDialog(null, e1.getMessage()+"�����������д���", "�д���", JOptionPane.ERROR_MESSAGE);
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
