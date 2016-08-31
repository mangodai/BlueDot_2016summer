package BlueDot;

import javax.swing.JOptionPane;

public class SortDemo {
    private int i=1;
	private String funData="";
	public String sorting(String name,int oldData[]){
		int[] data = dataCopy(oldData);//防止原来数据被修改
		String str=name+"---数据:";
		str += oldData(oldData);
		if(name.equals(DataSort.name[0])){
			str +=quickSort(data,0,data.length-1)+"\n";
		}
		else if(name.equals(DataSort.name[1])){
			str +=bubbleSort(data)+"\n";
		}
		else if(name.equals(DataSort.name[2])){
			str +=RadixSort(data)+"\n";
		}
		else if(name.equals(DataSort.name[3])){
			str +=shellSort(data)+"\n";
		}
		else {
			return "code error";
		}
		return str;
	}

	private String RadixSort(int[] data) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			int a1[] = new int[1000];
			int a2[] = new int[data.length];
			int index=0;
			for(int i:data){
				try {
					a1[i]++;					
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "待排序的单个数据的值超过1000", "有错误", JOptionPane.ERROR_MESSAGE);					
				}
			}
			for(int i=0;i<a1.length;i++){
				for(int j=0;j<a1[i];j++){
					a2[index++]=i;
					System.out.println(i+" ");
				}
			}
//		funData +="第"+i+"次"+":";
		funData +="有趣的桶排序\n";
		funData +=oldData(a2);
		return funData;
	}

	private String shellSort(int[] data) {

	        int out, in, tmp;  
	        int len = data.length;  
	        int h = 1;   
	        int i=1;
	        while(h < len / 3) // 计算间隔h最大值  
	            h = h * 3 + 1;  
	        while(h > 0){ // 能否继续通过缩小间隔h来分割数据列的判定   
	            for(out = h; out < len; out++){ // 外层通过out确定每组插入排序的第二个数据项  
	                // 以下代码就是对子序列进行的插入排序算法  
	                tmp = data[out];  
	                in = out;  
	                /* 
	                 * 比较插入排序while循环的写法，这里的while循环与h有关，所以判定就与h有关，包括 in -= h语句 
	                 * while(in > 0 && array[in - 1] > tmp){ 
	                 * array[in] = array[in - 1]; 
	                 * in--; 
	                 * } 
	                 * array[in] = tmp; 
	                 *  
	                 */  
	                while(in > h -1 && data[in - h] >= tmp){  
	                    data[in] = data[in - h];  
	                    in -= h;  
	                }  
	                data[in] = tmp;                                
	            }  
	            h = (h - 1) / 3;  
				funData +="第"+i+"次"+":";
				funData +=oldData(data);
				i++;
	        }  
		return funData;
	}

	private String bubbleSort(int[] data) {
		// TODO Auto-generated method stub
		int i,j;
		boolean flag=true;
		int tamp;
		for(i=1;i<data.length&&flag;i++){
			flag=false;
			for(j=0;j<data.length-1;j++){
				if(data[j]>data[j+1]){
					flag=true;
					tamp=data[j];
					data[j]=data[j+1];
					data[j+1]=tamp;
				}
			}
			funData +="第"+i+"次"+":";
			funData +=oldData(data);
		}
		return funData;
	}
	
	private String quickSort(int n[], int left, int right) {
        int dp;

        if (left < right) {
            dp = partition(n, left, right);
            quickSort(n, left, dp - 1);;
            quickSort(n, dp + 1, right);i++;
		funData +="第"+(i)+"次"+":";
		funData +=oldData(n);
        }
        return funData;
    }
	
	public int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
	private int[] dataCopy(int[] oldData) {
		int data[]=new int[oldData.length];
		for(int i=0;i<oldData.length;i++){
			data[i]=oldData[i];
		}
		return data;
	}

	private String oldData(int[] data) {
		String str="";
		for(int i:data){
			str +=i+" ";			
		}
		str +="\n";
		return str;
	}
}