/**
 * SortedTree
 * Author -
 * Last modified - 03/16/2019
 * copyright info - n/a
 * */

class TreeNode{
  public int info;
  public TreeNode left, right;

  public TreeNode(int x){
    info = x;
    left = right = null;
  }
}

public class SortedTree{
  public final static int SIZE_INPUT = 8;
  TreeNode root;

  /**
   * Receives a sorted sequence of integers to build
   * a binary tree. Given any node nd in this binary tree,
   * let its subtrees be denoted t1 and t2. The number of
   * nodes in t1 and t2 differ by no more than one.
   */
  public SortedTree(int[] input){
    root = null;
    if(!verifyInput(input)){//if input is not sorted return
      return;
    }else{
      root = buildTree(input, 0, input.length-1);
    }
  }

  /**
   * Builds a binary using the objects from input[start]
   * to input[end].
   *
   * See the constructor for the requirements for this binary tree
   *
   * First we find the middle index of the array passed in.
   * Then calculates the middle left subarray and make it root of the left subtree.
   * Then calcualtes the middle of right subarray and makes it root of the right subtree.
   */
  private TreeNode buildTree(int[] input, int start, int end) {

    //Base case, when you reach the end of the index of arrays.
    if (start > end) {

      return null;
    }

    //Finds the middle index of the array.
    int middle = (start + end)/2;
    TreeNode nd = new TreeNode(input[middle]);

    //Recursively sets the left child for the TreeNode
    nd.left = buildTree(input, start, middle -1);

    //Recursively sets the right child for the TreeNode.
    nd.right = buildTree(input, middle +1, end);

    return nd;
  }

  private boolean verifyInput(int [] input){
    if(input == null){
      return false;
    }

    for(int i=0; i < input.length - 1; i++){
      if(input[i] >= input[i+1]){
        return false;
      }
    }

    return true;
  }

  /**
   * gets the depth of the tree
   * @return the depth of the tree
   * */
  public int depth(){
    return _depth(root);
  }

  /**
   * Finds the depth(or the number of nodes along the longest path from the root
   * node not including the root node) of any tree.
   * @param root The tree being passed in to calculate the depth.
   * @return the depth of the tree.
   * */
  public int _depth(TreeNode root){

    //Base case
    if (root == null) {

      return -1;
    }

    //Finds each subtrees depth.
    int leftDepth = _depth(root.left);
    int rightDepth = _depth(root.right);

    // provides the counter and determines which side of tree the tree has the longest path.
    if (leftDepth > rightDepth) {

      return leftDepth + 1;
    } else {

      return rightDepth + 1;
    }
  }

  public void traverse(){
    traverseNodes(root);
  }

  /**
   * print outs the tree inorder form.
   * @param root the Tree that the program is traversing.
   * */
  private void traverseNodes(TreeNode root){
    if (root != null) {
      traverseNodes(root.left);
      System.out.print(root.info + " ");
      traverseNodes(root.right);
    }

  }

  public static void main(String args[]){

//    int[] input = new int[SIZE_INPUT];
//    for(int i = 0; i < SIZE_INPUT; i++){
//      input[i] = i;
//    }
    //another input array we saw in class:
    int[] input = {4, 9, 15, 20, 22, 24, 35, 87};

    //1 - create the binary search tree given the sorted input
    SortedTree st = new SortedTree(input);

    //2 - print its depth
    System.out.println("The depth of the tree is " + st.depth());

    //3 - print the tree nodes in ascending order
    st.traverse();
  }
}