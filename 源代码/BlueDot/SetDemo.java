package BlueDot;


import java.util.Set;
import java.util.TreeSet;


public class SetDemo {
	private Set<Integer> setA = new TreeSet<Integer>();
	private Set<Integer> setB = new TreeSet<Integer>();
	private StringBuilder str = new StringBuilder();
	private String LINE =System.getProperty("line.separator");
	
	public SetDemo(int[] dataA,int[] dataB) {
		super();
		// TODO Auto-generated constructor stub
		addData(setA, dataA);
		addData(setB, dataB);
		
		str.append("并集:   "+bingji(setA, setB)+LINE);
		str.append("交集:   "+jiaoji(setA, setB)+LINE);
		str.append("差集:   "+chaji(setA, setB)+LINE);
		str.append("补集:   "+buji(setA, setB)+LINE);
		str.append("对称差集:"+duichengji(setA, setB)+LINE);
	}
	public String getStr(){
		return this.str.toString();
	}
	private static String bingji(final Set<Integer> setA,final Set<Integer> setB) {
		Set<Integer> setC = new TreeSet<Integer>();
		setC.addAll(setA);
		setC.addAll(setB);
		return setC.toString();
	}
	
	private static String chaji(final Set<Integer> setA,final Set<Integer> setB) {
		Set<Integer> setC = new TreeSet<Integer>();
		setC.addAll(setA);
		setC.removeAll(setB);
		
		return setC.toString();
	}
	
	private static String buji(final Set<Integer> setA,final Set<Integer> setB) {
		Set<Integer> setC = new TreeSet<Integer>();
		setB.removeAll(setA);
		setC.addAll(setB);
		
		return setC.toString();
	}
	
	private static String jiaoji(final Set<Integer> setA,final Set<Integer> setB) {
		Set<Integer> setC = new TreeSet<Integer>();
		setC.addAll(setA);
		setC.retainAll(setB);
		return setC.toString();
	}
	
	private static String duichengji(final Set<Integer> setA,final Set<Integer> setB) {
		Set<Integer> setC = new TreeSet<Integer>();
		setC.addAll(setA);
		setC.retainAll(setB);
		setA.removeAll(setC);
		setB.removeAll(setC);
		setC.clear();
		setC.addAll(setA);
		setC.addAll(setB);
		return setC.toString();
	}

	private static void addData(Set<Integer> set, int[] dataA) {
		for(int i=0;i<dataA.length;i++){
			set.add(dataA[i]);
		}
	}
	

}
