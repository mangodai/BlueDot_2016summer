package edu.distance;

import java.util.HashMap;

/**
 * 创建一个接口，获得最短路径
 * @author Administrator
 *
 */
public interface Distance {
	public static final MinStep UNREACHABLE = new MinStep(false, -1);
	public MinStep getMinStep(int start, int end, final HashMap<Integer, HashMap<Integer, Integer>> stepLength);
	

}
