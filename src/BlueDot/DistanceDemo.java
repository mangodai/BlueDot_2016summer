package BlueDot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.distance.DistanceTest;

public class DistanceDemo extends JPanel{
	/**
	 * 最短路径演示
	 */
	private static final long serialVersionUID = 1L;
	private JPanel head = new JPanel();
	private JPanel center = new JPanel();
	private JTextArea jt= new JTextArea();
	private JScrollPane jsp=new JScrollPane(jt);
	private DistanceTest path = new DistanceTest();
	private int upSelect=0;
	private int downSelect=0;
	public DistanceDemo() {
		super();
		this.setBorder(BorderFactory.createTitledBorder("最短路径"));
		this.setLayout(new BorderLayout(5,10));
		this.add(center,BorderLayout.CENTER);
		this.add(headIniti(),BorderLayout.NORTH);
		center.setLayout(new GridLayout(1,2) );
		center.add(rightIniti());
		center.add(leftIniti());
	}	
	/**
	 * 创建一个数据输入和确定
	 * textfile & button
	 * @return
	 */
	private JPanel headIniti() {
		JTextField jtf = new JTextField(20);
		JButton jb = new JButton("确定");
//		jtf.addMouseListener(l);
		head.setLayout(new FlowLayout());
		head.add(jtf);
		head.add(jb);
		return head;
	}
	private JPanel rightIniti() {
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(2,1));
		left.add(up());
		left.add(down());
		return left;
	}
	
	private JPanel down() {
		JRadioButton jb[];
		JPanel down = new JPanel();
		down.setBorder(BorderFactory.createTitledBorder("设置终点"));
		Set<Integer> key =path.getStepLength().keySet();
		jb= new JRadioButton[key.size()];
		ButtonGroup bg = new ButtonGroup();
		for (Integer i : key) {
			jb[i-1]=new JRadioButton(i.toString());
			bg.add(jb[i-1]);
			jb[i-1].addActionListener(a2);
		} 
		for(JRadioButton j :jb){
			down.add(j);
		}
		
		return down;
	}
	private JPanel up() {
		JRadioButton jb[];
		JPanel up = new JPanel();
		up.setBorder(BorderFactory.createTitledBorder("设置起点"));

		Set<Integer> key =path.getStepLength().keySet();
		jb= new JRadioButton[key.size()];
		ButtonGroup bg = new ButtonGroup();
		for (Integer i : key) {
			jb[i-1]=new JRadioButton(i.toString());
			bg.add(jb[i-1]);
			jb[i-1].addActionListener(a1);
		} 
		for(JRadioButton j :jb){
			up.add(j);
		}
		return up;
	}
	private JPanel leftIniti(){
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout(10,10));
		right.setBorder(BorderFactory.createTitledBorder("文本区"));
		right.add(jsp,BorderLayout.CENTER);
		Font f = new Font("Serif",1,15);
		jt.setFont(f);
		jt.append(path.getV());
		jt.append(path.getStr().toString());
//		jt.disable();
		return right;
	}
	private ActionListener a1 = new ActionListener(){
		// up 的监听
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton jb =(JRadioButton)e.getSource();
			System.out.println(jb.getText());
			upSelect = Integer.valueOf(jb.getText());
		}
		
	};
	private ActionListener a2 = new ActionListener(){
		// down 的监听
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
