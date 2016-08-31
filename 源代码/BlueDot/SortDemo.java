package BlueDot;

import javax.swing.JOptionPane;

public class SortDemo {
    private int i=1;
	private String funData="";
	public String sorting(String name,int oldData[]){
		int[] data = dataCopy(oldData);//��ֹԭ�����ݱ��޸�
		String str=name+"---����:";
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
					JOptionPane.showMessageDialog(null, "������ĵ������ݵ�ֵ����1000", "�д���", JOptionPane.ERROR_MESSAGE);					
				}
			}
			for(int i=0;i<a1.length;i++){
				for(int j=0;j<a1[i];j++){
					a2[index++]=i;
					System.out.println(i+" ");
				}
			}
//		funData +="��"+i+"��"+":";
		funData +="��Ȥ��Ͱ����\n";
		funData +=oldData(a2);
		return funData;
	}

	private String shellSort(int[] data) {

	        int out, in, tmp;  
	        int len = data.length;  
	        int h = 1;   
	        int i=1;
	        while(h < len / 3) // ������h���ֵ  
	            h = h * 3 + 1;  
	        while(h > 0){ // �ܷ����ͨ����С���h���ָ������е��ж�   
	            for(out = h; out < len; out++){ // ���ͨ��outȷ��ÿ���������ĵڶ���������  
	                // ���´�����Ƕ������н��еĲ��������㷨  
	                tmp = data[out];  
	                in = out;  
	                /* 
	                 * �Ƚϲ�������whileѭ����д���������whileѭ����h�йأ������ж�����h�йأ����� in -= h��� 
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
				funData +="��"+i+"��"+":";
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
			funData +="��"+i+"��"+":";
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
		funData +="��"+(i)+"��"+":";
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