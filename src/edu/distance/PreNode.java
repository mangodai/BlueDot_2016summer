package edu.distance;

public class PreNode {
	private int preNodeNum;//最优节点
	private int nodeStep;//最小步长
	public int getPreNodeNum() {
		return preNodeNum;
	}
	public void setPreNodeNum(int preNodeNum) {
		this.preNodeNum = preNodeNum;
	}
	public int getNodeStep() {
		return nodeStep;
	}
	public void setNodeStep(int nodeStep) {
		this.nodeStep = nodeStep;
	}
	public PreNode(int preNodeNum, int nodeStep) {
		super();
		this.preNodeNum = preNodeNum;
		this.nodeStep = nodeStep;
	}
	


	
}
