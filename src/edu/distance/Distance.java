package edu.distance;

import java.util.HashMap;

/**
 * ����һ���ӿڣ�������·��
 * @author Administrator
 *
 */
public interface Distance {
	public static final MinStep UNREACHABLE = new MinStep(false, -1);
	public MinStep getMinStep(int start, int end, final HashMap<Integer, HashMap<Integer, Integer>> stepLength);
	

}
