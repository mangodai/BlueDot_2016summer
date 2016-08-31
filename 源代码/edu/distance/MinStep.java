package edu.distance;

import java.util.List;

public class MinStep {
	private boolean reachable;//是否可以达到
	private int minStep;//最短步长
	private List<Integer> step;//保存始发节点到终结点的所有
	
	public MinStep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MinStep(boolean reachable, int minStep) {
		super();
		this.reachable = reachable;
		this.minStep = minStep;
	}
	
	public boolean isReachable() {
		return reachable;
	}
	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}
	public int getMinStep() {
		return minStep;
	}
	public void setMinStep(int minStep) {
		this.minStep = minStep;
	}
	public List<Integer> getStep() {
		return step;
	}
	public void setStep(List<Integer> step) {
		this.step = step;
	}
	
}
