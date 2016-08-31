package BlueDot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.JTextField.myTextField;
import edu.distance.DistanceTest;

public class DistanceDemo extends JPanel{
	/**
	 * ���·����ʾ
	 */
	private static final long serialVersionUID = 1L;
	private JPanel head = new JPanel();
	private JPanel center = new JPanel();
	private JTextArea jt= new JTextArea();
	private myTextField jtf = null; 
	private JScrollPane jsp=new JScrollPane(jt);
	private DistanceTest path = new DistanceTest();
	private int upSelect=0;
	private int downSelect=0;
	public DistanceDemo() {
		super();
		this.setBorder(BorderFactory.createTitledBorder("���·���������ֱ��ѡ�������յ㣬��ȻҲԤ���˷��������ӽڵ�"));
		this.setLayout(new BorderLayout(5,10));
		this.add(centerIniti(),BorderLayout.CENTER);
		this.add(headIniti(),BorderLayout.NORTH);
		
	}
	private JPanel centerIniti() {
		center.setLayout(new GridLayout(1,2) );
		center.add(leftIniti());
		center.add(rightIniti());
		return center;
	}	
	/**
	 * ����һ�����������ȷ��
	 * textfile & button
	 * @return
	 */
	private JPanel headIniti() {
		jtf = new myTextField(20);
		JButton jb = new JButton("ȷ��");
//		jtf.addMouseListener(l);
		head.setLayout(new FlowLayout());
		jb.addActionListener(l);
		head.add(new JLabel("���㷨Ϊ˫���Ŷ,�������ݣ�"));
		head.add(jtf);
		head.add(jb);
		return head;
	}
	private ActionListener l = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			e.getSource();
			if(jtf.getText().equals("")){
				return ;
			}
			String s[]=jtf.getText().trim().split(" +");
			int data[]=new int[s.length];
			for(int i=0;i<s.length;i++){
				try {
					data[i] = Integer.parseInt(s[i]);	
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage()+"�����д���", "����", JOptionPane.ERROR_MESSAGE);
				    jtf.setText("");
				    return ;
				}
			}
//			System.out.println(data.toString());
			addJButton(data);
		}
		
	};
	private void addJButton(int data[]){
		HashMap<Integer,HashMap<Integer,Integer>> stepLength =path.getStepLength();
		HashMap<Integer,Integer> step1 =null;  
		if(stepLength.containsKey(data[0])){  //���ڷ��� ������Ӧ��ֵ
			step1 = stepLength.get(data[0]);
		}
		else{
			step1 = new HashMap<Integer,Integer>();//�����ھ��½�һ��ֵ
			stepLength.put(data[0], step1);  
		}
        step1.put(data[1], data[2]);  
		center.removeAll();
		centerIniti();
		this.updateUI();	
	}
	private JPanel leftIniti() {
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(2,1));
		left.add(up());
		left.add(down());
		return left;
	}
	
	private JPanel down() {
		JRadioButton jb[];
		JPanel down = new JPanel();
		down.setBorder(BorderFactory.createTitledBorder("�����յ�"));
		Set<Integer> key =path.getStepLength().keySet();
		jb= new JRadioButton[key.size()];
		ButtonGroup bg = new ButtonGroup();
		int i=0;
		for (Iterator<Integer> it=key.iterator();it.hasNext();i++) {
			jb[i]=new JRadioButton(it.next().toString());
			bg.add(jb[i]);
			jb[i].addActionListener(a2);
		} 
		for(JRadioButton j :jb){
			down.add(j);
		}
		
		return down;
	}
	private JPanel up() {
		JRadioButton jb[];
		JPanel up = new JPanel();
		up.setBorder(BorderFactory.createTitledBorder("�������"));

		Set<Integer> key =path.getStepLength().keySet();
		jb= new JRadioButton[key.size()];
		ButtonGroup bg = new ButtonGroup();
		int i=0;
		for (Iterator<Integer> it=key.iterator();it.hasNext();i++) {
			jb[i]=new JRadioButton(it.next().toString());
			bg.add(jb[i]);
			jb[i].addActionListener(a1);
		} 
		for(JRadioButton j :jb){
			up.add(j);
		}
		return up;
	}
	private JPanel rightIniti(){
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout(10,10));
		right.setBorder(BorderFactory.createTitledBorder("�ı���"));
		right.add(jsp,BorderLayout.CENTER);
		Font f = new Font("Serif",1,15);
		jt.setFont(f);
		jt.setText("");
		jt.append(path.getV());
		jt.append(path.getStr().toString());
		jt.setEditable(false);
		return right;
	}
	private ActionListener a1 = new ActionListener(){
		// up �ļ���
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton jb =(JRadioButton)e.getSource();
			System.out.println(jb.getText());
			upSelect = Integer.valueOf(jb.getText());
		}
	};
	private ActionListener a2 = new ActionListener(){
		// down �ļ���
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton jb =(JRadioButton)e.getSource();
			System.out.println(jb.getText());
			jt.setText("");
			downSelect = Integer.valueOf(jb.getText());
				jt.append(path.getV());
				jt.append(path.getDemo(upSelect, downSelect));
		}
		
	};
}
