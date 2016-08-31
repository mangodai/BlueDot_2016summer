package BlueDot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import edu.Formula.FormulaDemo;
import edu.JTree.JTreeDemo;
import edu.Jar.UnJar;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;// �汾����
	private JToolBar toolBar;
	private PanelAction panelAction = new PanelAction(); // �ڲ��࣬ʵ���¼���Ӧ
	private JPanel panel;

	public Main(String title) { // ���캯����д
		this.setTitle(title);
		this.setSize(803, 668);
		// this.setResizable(false);
		this.setLocationRelativeTo(null); // ���ô�������Ļ������ʾ
		this.getContentPane().add(buildToolBar("������"), BorderLayout.NORTH); // ���ӹ��߲˵�
		this.getContentPane().add(buildPanel("�������"), BorderLayout.CENTER); // �½�Ĭ�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �˳����ǽ���
		// ((JPanel)getContentPane()).setOpaque(false);
	}

	private static void addSkin() {
		// �������
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
			UIManager
					.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());// �������
//			SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());// ��������
//			SubstanceLookAndFeel.setSkin(new MySkin());
//			SubstanceLookAndFeel.setSkin("CremeSkin");
//			SubstanceLookAndFeel.setcur
			SubstanceLookAndFeel
					.setCurrentButtonShaper(new ClassicButtonShaper());// ���ð�ť���
//			SubstanceLookAndFeel
//			.setCurrentWatermark(new SubstanceBinaryWatermark());// ����ˮӡ
			
			SubstanceLookAndFeel
					.setCurrentBorderPainter(new StandardBorderPainter());// ���ñ߿�
			
			SubstanceLookAndFeel
					.setCurrentGradientPainter(new StandardGradientPainter());// ���ý�����Ⱦ
			
//			SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());// ���ñ���
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main cut = new Main("2016_����_Java_�����ҵ");
		addSkin();
		cut.setVisible(true);
	}

	public JToolBar buildToolBar(String title) {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBorder(BorderFactory.createTitledBorder(title));
			toolBar.setLayout(new FlowLayout());
			toolBar.add(buildButton("��ӭ��ҳ"));
			toolBar.add(buildButton("JavaSrc"));
			// toolBar.add(buildButton("��ɢlab4"));
			toolBar.add(buildButton("��ɢlab5"));
			toolBar.add(buildButton("��ɢlab6"));
			toolBar.add(buildButton("��������"));
			toolBar.add(buildButton("������"));
			toolBar.add(buildButton("���·��"));
			// toolBar.setEnabled(false); // ����toolBar�Ŀ��ƶ���Ϊ��
		}
		return toolBar;
	}

	public JPanel buildPanel(String title) {
		panel = new JPanel();
		/**
		 * ����һ���м���������һ����ǩ ��ǩ����һ��ͼƬ
		 * 
		 */
		panel.setBorder(BorderFactory.createTitledBorder(title));
		panel.add(buildImage(), BorderLayout.CENTER); // Ĭ���ǻ�ӭ��ҳ
		panel.setOpaque(false);// panel����͸��
		return panel;
	}

	/**
	 * �ö����ķ�ʽ��ȡ����ͼƬ
	 * 
	 * @return ����ͼƬ��Label
	 */
	public JLabel buildImage() {
		URL is = this.getClass().getResource("/BackPhoto.png");
		ImageIcon img = new ImageIcon(is);
		img.setImage(img.getImage().getScaledInstance(img.getIconWidth(),
				img.getIconWidth(), Image.SCALE_DEFAULT));
		JLabel background = new JLabel(img);
		// bgp.setBounds(0,0,800,600);//�������ͼƬ�������ô�С
		return background;
	}

	public JButton buildButton(String name) {
		JButton btn = new JButton(name);
		// btn.setFont(new Font("����", Font.PLAIN, 15));
		// btn.setMargin(new Insets(0, 0, 0, 0));
		btn.addActionListener(panelAction);
		return btn;
	}

	/**
	 * ʵ�ְ�ť������Ӧ
	 * 
	 * @author Deri
	 *
	 */
	private class PanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand().substring(0); // �õ���Ӧ��������
			/**
			 * ���Ĵ��� ͨ��ʵ���¼�������������ԭ��JFrame������ �Ƴ�ԭ���İ�� ���������İ�飬��JLabel �н�
			 */
			Main cp = Main.this;
			cp.panel.removeAll();
			cp.panel.updateUI(); // ԭ���������ɾ�
			panel.setLayout(new BorderLayout());// �����������У������������ñ߽粼��
			// cp.remove(panel);//���Ĭ�����
			// cp.repaint();//remove���ף��ػ����
			cp.panel.validate(); // ��֤����, ʹ�� validate
									// ������ʹ�����ٴβ�������������Ѿ���ʾ���������޸Ĵ��������������ʱ������������ӻ��Ƴ���������߸�����
									// ������ص���Ϣ����Ӧ�õ������������������ Container
									// ��Ч����˷��������� validateTree ������������ Container
									// ���Ϊ��Ч������ִ���κζ�����
			// System.out.println(buttonName.toString());
			if (buttonName.equals("��ӭ��ҳ")) {
				cp.panel.add(buildImage(), BorderLayout.CENTER);
			} else if (buttonName.equals("JavaSrc")) {
				new UnJar();
				cp.panel.add(new JTreeDemo(), BorderLayout.CENTER);
			} else if (buttonName.equals("��������")) {
				cp.panel.add(new DataSort(), BorderLayout.CENTER);

			} else if (buttonName.equals("������")) {
				cp.panel.add(new BTree(), BorderLayout.CENTER);
			} else if (buttonName.equals("��ɢlab6")) {
				cp.panel.add(new SetLab(), BorderLayout.CENTER);
			} else if (buttonName.equals("��ɢlab5")) {
				cp.panel.add(new FormulaDemo(), BorderLayout.CENTER);
			} else if (buttonName.equals("���·��")) {
				cp.panel.add(new DistanceDemo(), BorderLayout.CENTER);
			} else {
				cp.panel.add(new NotFound404(), BorderLayout.CENTER);
			}
			cp.panel.updateUI();
		}
	}
}