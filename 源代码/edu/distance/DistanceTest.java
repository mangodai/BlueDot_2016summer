package edu.distance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DistanceTest {
	private final String LINE = System.getProperty("line.separator");
	private HashMap<Integer, HashMap<Integer,Integer>> stepLength = new HashMap<Integer, HashMap<Integer,Integer>>();  
	private StringBuilder str = new StringBuilder();
	
	public DistanceTest(){
		HashMap<Integer,Integer> step1 = new HashMap<Integer, Integer>();  
        stepLength.put(1, step1);  
        step1.put(6, 14);  
        step1.put(3, 9);  
        step1.put(2, 7);  
          
        HashMap<Integer,Integer> step2 = new HashMap<Integer, Integer>();  
        stepLength.put(2, step2);  
        step2.put(1, 7);  
        step2.put(3, 10);  
        step2.put(4, 15);  
          
        HashMap<Integer,Integer> step3 = new HashMap<Integer, Integer>();  
        stepLength.put(3, step3);  
        step3.put(1, 9);  
        step3.put(2, 10);  
        step3.put(4, 11);  
        step3.put(6, 2);  
          
        HashMap<Integer,Integer> step4 = new HashMap<Integer, Integer>();  
        stepLength.put(4, step4);  
        step4.put(2, 15);  
        step4.put(5, 5);  
        step4.put(3, 11);  
          
        HashMap<Integer,Integer> step5 = new HashMap<Integer, Integer>();  
        stepLength.put(5, step5);  
        step5.put(6, 9);  
        step5.put(4, 5);  
          
        HashMap<Integer,Integer> step6 = new HashMap<Integer, Integer>();  
        stepLength.put(6, step6);  
        step6.put(1, 14);  
        step6.put(5, 9);  
        step6.put(3, 2);  
        System.out.println(stepLength.keySet().toString());
	}
	/**
	 * 最短路径的演示
	 */
	public String getDemo(int start ,int end) {
		Distance distance = new DistanceDijkstraImpl();
        if((!stepLength.containsKey(start))||(!stepLength.containsKey(end))){
        	str.append("不包含这几个点");
        }
        else if(start==end){
        	str.append("起点和终点相同，距离为  0 "+LINE);
        }
        else{
        	str.append("你选择的起点："+start+"----终点:"+end+LINE);
        	MinStep step = distance.getMinStep(start, end, stepLength);  //数据输入处理是否包含该节点
        	str.append("所需最短距离:"+step.getMinStep()+LINE);
        	str.append("所走过最短节点:"+step.getStep().toString()+LINE);
//        	System.out.println("getMinDistance:"+step.getMinStep());
//        	System.out.println("getStep:"+step.getStep().toString());
        }
        
        return getStr().toString();
	}

	public HashMap<Integer, HashMap<Integer, Integer>> getStepLength() {
		return stepLength;
	}
	
	public void setStepLength(HashMap<Integer, HashMap<Integer, Integer>> stepLength) {
		this.stepLength = stepLength;
	}

	public StringBuilder getStr() {
        str.append("最短路径中 选择节点为：");
//		str.append(stepLength.keySet().toString()+LINE);
		return str;
	}

	public void setStr(StringBuilder str) {
		this.str = str;
	}
	
	public String getV(){
		StringBuilder v = new StringBuilder();
		Set<Integer> key =this.stepLength.keySet();
		for (Iterator<Integer> iterator = key.iterator(); iterator.hasNext();) {
			Integer i = iterator.next();
			v.append("节点："+i+LINE);
			Map<Integer,Integer>value =this.stepLength.get(i);
			Set<Map.Entry<Integer, Integer>> temp= value.entrySet();
			for (Iterator<Map.Entry<Integer, Integer>> iterator2 = temp.iterator(); iterator2.hasNext();) {
				Entry<Integer, Integer> me = iterator2.next();
				v.append("    "+me.getKey()+"    距离为"+me.getValue()+LINE);
			}
		}
		v.append(stepLength.keySet().toString()+LINE);
		return v.toString();
	}
	

}
