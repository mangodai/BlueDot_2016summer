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
     * 创建一个空的二叉树 
     */  
  
    public BinaryTree() {  
        root = null;  
    }  
      
    /** 
     * 递归的插入数值 
     * @param data  要插入的数值 
     */  
  
    public void insert(int data) {  
        root = insert(root, data);  
    }  
      
    /** 
     * 将数值插入到二叉树中，比当前结点小或等于当前结点的插在当前结点的左侧，比当前结点大的数插在当前结点的右侧，每次从根结点开始递归比较 
     * @param node  当前的结点，就是根结点，只是每次根结点的左右子孙更新 
     * @param data  要插入的数值 
     * @return  新排好的二叉树 
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
     * 将数值输入构建二叉树 
     * @param data  要输入的数值 
     */  
  
    public void buildTree(int[] data) {  
  
        for (int i = 0; i < data.length; i++) {  
  
            insert(data[i]);  
  
        }  
  
    }  
      
    /** 
     * 递归打印出二叉树 
     */  
  
    public StringBuilder printTree() {   	
    	line.append("\n前序遍历：");
    	qianprintTree(root);  
    		
        line.append("\n中序遍历：");
        zhongprintTree(root); 
        
        line.append("\n后序遍历：");
        houprintTree(root); 
        
		return line;  
  
    }  
      
    /** 
     * 从根结点开始遍历，从树的最高层叶子结点开始输出，从左至右 
     * @param node  当前的结点 
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
