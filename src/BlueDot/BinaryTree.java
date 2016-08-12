package BlueDot;

public class BinaryTree {  
	  
    private Node root;  
      
    private StringBuilder line=new StringBuilder();
    private static class Node {  
        Node left;  
        Node right;  
        int data;  
  
        Node(int newData) {  
            left = null;  
            right = null;  
            data = newData;  
        }  
  
    }  
  
    /** 
     * ����һ���յĶ����� 
     */  
  
    public BinaryTree() {  
        root = null;  
    }  
      
    /** 
     * �ݹ�Ĳ�����ֵ 
     * @param data  Ҫ�������ֵ 
     */  
  
    public void insert(int data) {  
        root = insert(root, data);  
    }  
      
    /** 
     * ����ֵ���뵽�������У��ȵ�ǰ���С����ڵ�ǰ���Ĳ��ڵ�ǰ������࣬�ȵ�ǰ����������ڵ�ǰ�����Ҳ࣬ÿ�δӸ���㿪ʼ�ݹ�Ƚ� 
     * @param node  ��ǰ�Ľ�㣬���Ǹ���㣬ֻ��ÿ�θ���������������� 
     * @param data  Ҫ�������ֵ 
     * @return  ���źõĶ����� 
     */  
  
    private Node insert(Node node, int data) {  
  
        if (node == null) {  
  
            node = new Node(data);  
  
        } else {  
            if (data <= node.data) {  
                node.left = insert(node.left, data);  
            } else {  
                node.right = insert(node.right, data);  
            }  
        }  
        return (node);  
    }  
      
    /** 
     * ����ֵ���빹�������� 
     * @param data  Ҫ�������ֵ 
     */  
  
    public void buildTree(int[] data) {  
  
        for (int i = 0; i < data.length; i++) {  
  
            insert(data[i]);  
  
        }  
  
    }  
      
    /** 
     * �ݹ��ӡ�������� 
     */  
  
    public StringBuilder printTree() {   	
    	line.append("\nǰ�������");
    	qianprintTree(root);  
    		
        line.append("\n���������");
        zhongprintTree(root); 
        
        line.append("\n���������");
        houprintTree(root); 
        
		return line;  
  
    }  
      
    /** 
     * �Ӹ���㿪ʼ��������������߲�Ҷ�ӽ�㿪ʼ������������� 
     * @param node  ��ǰ�Ľ�� 
     */  
  
    private void zhongprintTree(Node node) {  
    	  
        if (node == null)  
            return;    
        zhongprintTree(node.left);        
        line.append(node.data+"  ");  
        zhongprintTree(node.right);    
    }    
    private void qianprintTree(Node node) {  
    	  
        if (node == null)
            return;    
        line.append(node.data+"  ");  
        qianprintTree(node.left);          
        qianprintTree(node.right); 
    }
    private void houprintTree(Node node) {  
  
        if (node == null)  
            return;    
        houprintTree(node.left);        
        houprintTree(node.right);  
        line.append(node.data+"  ");
    }  
  
} 
