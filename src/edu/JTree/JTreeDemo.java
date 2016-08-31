package edu.JTree;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import edu.File.MyFile;
import edu.FileFilter.MyFilter;
import edu.JTextArea.MyTextArea;

public class JTreeDemo extends JPanel{

	private static final long serialVersionUID = 1L;
//	private JLabel test = new JLabel("正在开发");
	private MyTextArea jt = new MyTextArea("请选择"); 
//	private final String filePath ="D:\\WorkSpace\\BlueDot_2016summer\\src";
	private final String filePath =System.getProperty("user.dir")+File.separator+"src";
	private JPanel left =new JPanel();
	private JPanel right =new JPanel();
	private DefaultMutableTreeNode treeRoot;
	private BufferedReader bfr;
	public JTreeDemo() {
		super();
		BorderL();
		leftIniti();
		textIniti();
	}
	private void BorderL() {
		this.setLayout(new GridLayout(1,2));
	}
	private void leftIniti() {
		Border border1= BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"源文件");
		left.setBorder(border1);
		
//		left.add(test);
		treeRoot =new DefaultMutableTreeNode(this.getClass().getName());
		addFile();
		final JTree tree = new JTree(treeRoot);// to do create jtree		
		addLisence(tree);
		JScrollPane jsp = new JScrollPane(tree);
		left.setLayout(new BorderLayout());
		left.add(jsp,BorderLayout.CENTER);
		this.add(left);
	}
	/**x
	 * 为树增加鼠标监听
	 * @param tree
	 */
	private void addLisence(final JTree tree) {
		
		tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath treep = tree.getSelectionPath();
				if(treep==null)	return ;
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) treep
						.getLastPathComponent();//返回指定索引位置的路径组件
				File file_select = ((MyFile) selectNode.getUserObject()).getFile();
				if (!selectNode.isLeaf()) {//是否含有子节点，有子节点
					if (!(selectNode.getUserObject() instanceof MyFile)) {
						return;
					}
					selectNode.removeAllChildren();
					readDict(file_select,selectNode);//继续读取文件
				}
				if(file_select.isFile()){
					addText(file_select.getAbsolutePath());	
				}
			}
			
		});
	}
	/**
	 * 传来的路径，读取文件输出代码
	 * @param path
	 */
	private void addText(String path){
		jt.setText("");
		File select = new File(path);

		System.out.println(path);
		try {
			bfr = new BufferedReader(new FileReader(select.getCanonicalFile()));
			String line ;
			while((line=bfr.readLine())!=null){
//			System.out.println(line);
			jt.append(line+"\n");
			}
			jt.setCaretPosition(0);
			jt.setEditable(false);		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void addFile() {
		File path = new File (filePath);
		if(path.exists()){
			DefaultMutableTreeNode dict = new DefaultMutableTreeNode(new MyFile(path));
			treeRoot.add(dict);
			readDict(path,dict);
		}
	}
	
	private void readDict(File path, DefaultMutableTreeNode dict) {
		MyFilter filter = new MyFilter(".java");
		File list[] = path.listFiles(filter);
		if (list == null)
			return;
		for (int i = 0; i < list.length; i++) {
			File file_inlist = list[i];
			// String filename = file_inlist.getName();
			if (file_inlist.isDirectory()) {
				DefaultMutableTreeNode parent;
				parent = new DefaultMutableTreeNode(new MyFile(file_inlist));
				// 添加空白文件夹节点 使子节点显示为文件夹
				File stubadd = null;
				DefaultMutableTreeNode stub = new DefaultMutableTreeNode(
						stubadd);
				parent.add(stub);
				dict.add(parent);
			} else {
				DefaultMutableTreeNode son = new DefaultMutableTreeNode(
						new MyFile(file_inlist));
				dict.add(son);
			}
		}
	}
	
	private void textIniti() {
		Border border1= BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"代码区");
		right.setBorder(border1);
		right.setLayout(new BorderLayout());
		JScrollPane jsp = new JScrollPane(jt);
		right.add(jsp,BorderLayout.CENTER);
		this.add(right);
	}


}
