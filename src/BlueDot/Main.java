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
	private static final long serialVersionUID = 1L;// 版本代码
	private JToolBar toolBar;
	private PanelAction panelAction = new PanelAction(); // 内部类，实现事件响应
	private JPanel panel;

	public Main(String title) { // 构造函数重写
		this.setTitle(title);
		this.setSize(803, 668);
		// this.setResizable(false);
		this.setLocationRelativeTo(null); // 设置窗体在屏幕中央显示
		this.getContentPane().add(buildToolBar("功能区"), BorderLayout.NORTH); // 增加工具菜单
		this.getContentPane().add(buildPanel("主面板区"), BorderLayout.CENTER); // 新建默认面板
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 退出就是结束
		// ((JPanel)getContentPane()).setOpaque(false);
	}

	private static void addSkin() {
		// 设置外观
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
			UIManager
					.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());// 设置外观
//			SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());// 设置主题
//			SubstanceLookAndFeel.setSkin(new MySkin());
//			SubstanceLookAndFeel.setSkin("CremeSkin");
//			SubstanceLookAndFeel.setcur
			SubstanceLookAndFeel
					.setCurrentButtonShaper(new ClassicButtonShaper());// 设置按钮外观
//			SubstanceLookAndFeel
//			.setCurrentWatermark(new SubstanceBinaryWatermark());// 设置水印
			
			SubstanceLookAndFeel
					.setCurrentBorderPainter(new StandardBorderPainter());// 设置边框
			
			SubstanceLookAndFeel
					.setCurrentGradientPainter(new StandardGradientPainter());// 设置渐变渲染
			
//			SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());// 设置标题
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Main cut = new Main("2016_戴超_Java_暑假作业");
		addSkin();
		cut.setVisible(true);
	}

	public JToolBar buildToolBar(String title) {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBorder(BorderFactory.createTitledBorder(title));
			toolBar.setLayout(new FlowLayout());
			toolBar.add(buildButton("欢迎首页"));
			toolBar.add(buildButton("JavaSrc"));
			// toolBar.add(buildButton("离散lab4"));
			toolBar.add(buildButton("离散lab5"));
			toolBar.add(buildButton("离散lab6"));
			toolBar.add(buildButton("数据排序"));
			toolBar.add(buildButton("二叉树"));
			toolBar.add(buildButton("最短路径"));
			// toolBar.setEnabled(false); // 设置toolBar的可移动性为否
		}
		return toolBar;
	}

	public JPanel buildPanel(String title) {
		panel = new JPanel();
		/**
		 * 设置一个中间容器承载一个标签 标签承载一张图片
		 * 
		 */
		panel.setBorder(BorderFactory.createTitledBorder(title));
		panel.add(buildImage(), BorderLayout.CENTER); // 默认是欢迎首页
		panel.setOpaque(false);// panel设置透明
		return panel;
	}

	/**
	 * 用读包的方式读取背景图片
	 * 
	 * @return 承载图片的Label
	 */
	public JLabel buildImage() {
		URL is = this.getClass().getResource("/BackPhoto.png");
		ImageIcon img = new ImageIcon(is);
		img.setImage(img.getImage().getScaledInstance(img.getIconWidth(),
				img.getIconWidth(), Image.SCALE_DEFAULT));
		JLabel background = new JLabel(img);
		// bgp.setBounds(0,0,800,600);//承载这个图片容器设置大小
		return background;
	}

	public JButton buildButton(String name) {
		JButton btn = new JButton(name);
		// btn.setFont(new Font("粗体", Font.PLAIN, 15));
		// btn.setMargin(new Insets(0, 0, 0, 0));
		btn.addActionListener(panelAction);
		return btn;
	}

	/**
	 * 实现按钮事物响应
	 * 
	 * @author Deri
	 *
	 */
	private class PanelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand().substring(0); // 得到相应事物名字
			/**
			 * 核心处理 通过实现事件监听，并创建原来JFrame的引用 移除原来的板块 增加新来的板块，用JLabel 承接
			 */
			Main cp = Main.this;
			cp.panel.removeAll();
			cp.panel.updateUI(); // 原来面板清理干净
			panel.setLayout(new BorderLayout());// 由于清了所有，所以重新设置边界布局
			// cp.remove(panel);//清除默认面板
			// cp.repaint();//remove配套，重画框架
			cp.panel.validate(); // 验证功能, 使用 validate
									// 方法会使容器再次布置其子组件。已经显示容器后，在修改此容器的子组件的时候（在容器中添加或移除组件，或者更改与
									// 布局相关的信息），应该调用上述方法。如果此 Container
									// 无效，则此方法将调用 validateTree 方法，并将此 Container
									// 标记为有效。否则不执行任何动作。
			// System.out.println(buttonName.toString());
			if (buttonName.equals("欢迎首页")) {
				cp.panel.add(buildImage(), BorderLayout.CENTER);
			} else if (buttonName.equals("JavaSrc")) {
				new UnJar();
				cp.panel.add(new JTreeDemo(), BorderLayout.CENTER);
			} else if (buttonName.equals("数据排序")) {
				cp.panel.add(new DataSort(), BorderLayout.CENTER);

			} else if (buttonName.equals("二叉树")) {
				cp.panel.add(new BTree(), BorderLayout.CENTER);
			} else if (buttonName.equals("离散lab6")) {
				cp.panel.add(new SetLab(), BorderLayout.CENTER);
			} else if (buttonName.equals("离散lab5")) {
				cp.panel.add(new FormulaDemo(), BorderLayout.CENTER);
			} else if (buttonName.equals("最短路径")) {
				cp.panel.add(new DistanceDemo(), BorderLayout.CENTER);
			} else {
				cp.panel.add(new NotFound404(), BorderLayout.CENTER);
			}
			cp.panel.updateUI();
		}
	}
}