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
import javax.swing.JTextField;

public class DataSort extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel headPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	private JTextField jtf=new JTextField(20);
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
		headPanel.add(jtf);
		String text=jtf.getText();
		System.out.println(text);
		headPanel.add(buildSure());
		for(int i = 0;i<name.length;i++){
			headPanel.add(buildRadio(name[i],i));
		}
	}
    public class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton temp=(JRadioButton)e.getSource();
            if(temp.isSelected()){
                System.out.println(temp.getText());
	           	String string=new SortDemo().sorting(temp.getText(),sortData);
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
        }

    }
	private JRadioButton buildRadio(String name,int i) {
		// TODO Auto-generated method stub

		if(jr[i]==null){
			jr[i]=new JRadioButton(name);
			jr[i].addActionListener(radioButtonListener);
			group.add(jr[i]);
		}
//		jr[0].setSelected(true);//Ĭ��ѡ��,��������
	return jr[i];
	}
	private JButton buildSure(){
		sure = new JButton("ȷ��");
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String data[]=jtf.getText().split(" +");
				sortData=new int[data.length];
				for(int i=0;i<data.length;i++){
					try {	
					    sortData[i] = Integer.parseInt(data[i]);//���ݲ��Ի��׳��쳣
					    System.out.println(sortData[i]);                              
					} catch (RuntimeException e1) {
					    e1.printStackTrace();
					    JOptionPane.showMessageDialog(null, e1.getMessage()+"�����������д���", "�д���", JOptionPane.ERROR_MESSAGE);
					    jtf.setText("");
					}		
				}
			}
		});
	return sure;
	}
}
