package edu.distance;

public class PreNode {
	private int preNodeNum;//���Žڵ�
	private int nodeStep;//��С����
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
