package BlueDot;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotFound404 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public NotFound404() {
		super();
		if(getLabel()==null){
			label = new JLabel("正在开发");
		}
		this.add(getLabel());
	}
	

	public NotFound404(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public NotFound404(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public NotFound404(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}


	public JLabel getLabel() {
		return label;
	}


	public void setLabel(JLabel label) {
		this.label = label;
	}

	
	
}
