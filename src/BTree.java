/*
BTree.java
Sat Arora
This is the simulation of a binary tree. It uses no seperate data structures, and provides
useful methods that can be used however one wishes.
 */

public class BTree {
    private BNode root;
    private final int PRE = 0,IN = 1, POST = 2;
    private final int GETPARENT = 0, GETNODE = 1;
    public BTree() {
        root = null;
    }
    //add method adds an integer passed in to the tree
    public void add(int v) {
        BNode tmp = new BNode(v); //creating a new BNode holding that value
        if (root == null) {
            root = tmp;
        }
        else {
            add(root,tmp);
        }
    }
    //add method recursively finds a spot to insert the node, and does so
    public void add(BNode branch, BNode tmp) {
        if (tmp.getVal() < branch.getVal()) { //if the value has to be placed to the left of the current node
            if (branch.getLeft() == null) { //if there is nothing to the left of the current node
                branch.setLeft(tmp);
            }
            else add(branch.getLeft(),tmp); //searches at the left child
        }
        else if (tmp.getVal() > branch.getVal()) {//if the value has to be placed to the right of the current node
            if (branch.getRight() == null) { //if there is nothing to the right of the current node
                branch.setRight(tmp);
            }
            else add(branch.getRight(),tmp); //searches at the right child
        }
    }
    //stringify method takes a type of printout (pre, in, post), and helps with the display (returns the string that is printed out)
    public String stringify(BNode branch, int type) {
        if (branch != null && type == IN) { //current node is not null, the type of printout was in
            return stringify(branch.getLeft(),type) + branch.getVal() + ", " + stringify(branch.getRight(),type);
        }
        if (branch != null && type == PRE) { //current node is not null, the type of printout was pre
            return branch.getVal() + ", " + stringify(branch.getLeft(),type) + stringify(branch.getRight(),type);
        }
        if (branch != null && type == POST) { //current node is not null, the type of printout was post
            return stringify(branch.getLeft(),type) + stringify(branch.getRight(),type) + branch.getVal() + ", ";
        }
        return "";
    }
    //depth method returns the depth of a node
    public int depth(int v) {
        return depth(v,root);
    }
    //overloaded recursive depth that checks for the depth from a current BNode
    public int depth(int v, BNode tmp) {
        if (tmp.getVal() == v) { //we found the node with the value
            return 1; //adding the values on to the recursive calls
        }
        if (tmp.getVal() < v && tmp.getRight() != null) { //if v would be on the right subtree, and the right subtree is actually existing
            int val = depth(v,tmp.getRight()); //recursive call for total depth
            if (val == -1) return -1; //pushing the -1 all the way to the top, so it returns -1 if the value is not found
            return 1 + val; //otherwise the node is found, current depth + the depth below
        }
        if (tmp.getVal() > v && tmp.getLeft() != null) { //if v would be on the left subtree, and the left subtree is actually existing
            int val = depth(v,tmp.getLeft()); //recursive call for total depth
            if (val == -1) return -1; //pushing the -1 all the way to the top, so it returns -1 if the value is not found
            return 1 + val; //otherwise the node is found, current depth + the depth below
        }
        return -1; //the node was not found (at lowest depth), pushing -1 all the way up
    }
    //display method that takes no parameter, meaning it defaults to IN printout
    public String display() {
        String ans = stringify(root,IN);
        if (ans.length() > 0) {
            ans = ans.substring(0,ans.length()-2);
        }
        return "{" + ans + "}";
    }
    @Override
    public String toString() {
        return display();
    }
    //display method that takes a parameter for type of printout
    public String display(int type) {
        String ans = stringify(root,type);
        if (ans.length() > 0) {
            ans = ans.substring(0,ans.length()-2);
        }
        return "{" + ans + "}";
    }
    //countLeaves returns the number of leaves in the tree
    public int countLeaves() {
        return countLeaves(root);
    }
    //overloaded recursive countLeaves which takes the current node to check from
    public int countLeaves(BNode tmp) {
        if (tmp == null) return 0; //went to a null even though the parent is not a leaf, should not impact the total
        if (tmp.getLeft() == null && tmp.getRight() == null) return 1; //if there is nothing below it, it is a leaf (add 1 to total)
        return countLeaves(tmp.getLeft()) + countLeaves(tmp.getRight()); //the number of leaves from one node is the sum of the two subtrees
    }
    //height returns the height of the tree
    public int height() { return height(root); }
    //overloaded recursive height from a current node, keeps adding up to the top
    public int height(BNode tmp) {
        if (tmp == null) return 0; //no node does not contribute to height
        int leftHeight = height(tmp.getLeft()); //adding the height of the left subtree
        int rightHeight = height(tmp.getRight()); //adding the height of the right subtree
        return Math.max(leftHeight,rightHeight) + 1; //the height is the maximum of the height the subtrees plus 1 (the current node)
    }
    //minHeight returns the minimum height of a tree
    public int minHeight() { return minHeight(root); }
    //overloaded recursive minHeight from a current node, keeps adding up to the top
    public int minHeight (BNode tmp){
        if (tmp == null) return 0; //no node does not contribute to height
        int leftHeight = minHeight(tmp.getLeft()); //adding the height of the left subtree
        int rightHeight = minHeight(tmp.getRight()); //adding the height of the right subtree
        return Math.min(leftHeight,rightHeight) + 1; //the height is the minimum of the height the subtrees plus 1 (the current node)
    }
    //isAncestor method returns if the first integer is an ancestor of the second
    public boolean isAncestor(int above, int below) {
        if (above == below) return false; //if the numbers are the same, they are the same child (which is not ancestral behavior)
        BNode ancestorLoc = fnd(above,root,GETNODE); //ancestorLoc is the BNode that holds the integer that is supposed to be the ancestor, and will hold null if it does not
        BNode childLoc = fnd(below,ancestorLoc,GETNODE); //childLoc is the BNode that holds the integer that is supposed to be the descendent, and will hold null if it does not
        return childLoc != null && ancestorLoc != null; //returns true if both nodes have values
    }
    //find method returns a BNode which holds the value, searches from the current root (cur), and also works for finding the parent of the node with the value
    public BNode fnd(int val, BNode cur, int status) {
        if (cur == null || (cur.getVal() == val && status == GETNODE)) return cur; //stops searching if the node is null (returns cur, which is null) or the value is found when searching for the node, and returns that node
        if (status == GETPARENT && ((cur.getLeft() != null && cur.getLeft().getVal() == val) || (cur.getRight() != null && cur.getRight().getVal() == val))) { //if looking for the parent, and the existing left child has the value or the existing right child has the value, returns that parent
            return cur;
        }
        if (val < cur.getVal()) { //if the value less than the BNode's value, search to the left
            return fnd(val, cur.getLeft(), status);
        }
        return fnd(val,cur.getRight(),status); //otherwise, search to the right
    }
    public BNode findParent(int val, BNode cur) {
        if (cur == null || cur.getLeft().getVal() == val || cur.getRight().getVal() == val) {
            return cur;
        }
        if (val < cur.getVal()) { //if the value less than the BNode's value, search to the left
            return findParent(val, cur.getLeft());
        }
        return findParent(val,cur.getRight()); //otherwise, search to the right
    }
    //delete method takes an integer and deletes it from the tree (if possible), while maintaining the tree structure+
    public void delete(int val) {
        BNode del; //will hold the parent of the node that has the desired value to delete
        BNode left=null,right=null; //left and right will hold the children of the parent node, one of them will hold the desired value to delete (unless the root has to be deleted)
        if (root.getVal() == val) { //there is no parent of the root, so this is the exception to the approach
            left = root.getLeft();
            right = root.getRight();
            root = null;
            add(left); //adding everything on the left
            add(right); //adding the entire subtree that was not accounted for
            return; //successfully removed root value, must terminate from method
        }
        del = fnd(val,root,GETPARENT); //del will hold the parent of the node that needs to be deleted
        if (del == null) return; //if no node has the value, then there is nothing to delete
        if (del.getLeft().getVal() == val) { //if the desired value was the left child
            left = del.getLeft().getLeft(); //root of the left subtree under the node that will be deleted
            right = del.getLeft().getRight(); //root of the right subtree under the node that will be deleted
            del.setLeft(null); //removing the node with the value to delete
        }
        else if (del.getRight().getVal() == val) {
            left = del.getRight().getLeft(); //root of the left subtree under the node that will be deleted
            right = del.getRight().getRight(); //root of the right subtree under the node that will be deleted
            del.setRight(null); //removing the node with the value to delete
        }
        add(left); //adding the left subtree that was removed before back into the tree
        add(right); //adding the right subtree that was removed before back into the tree
    }
    //isBalanced returns whether the tree is balanced or not
    public boolean isBalanced() {
        return height() - minHeight() <= 1;
    }
    //add method that adds all values in a BTree to the current tree
    public void add(BTree newTree) {
        add(newTree.root); //calling the overloaded add with the root of the tree passed in
    }
    //overloaded recursive add that adds the value of the BNode, then calls itself for the node's children
    public void add(BNode cur) {
        if (cur == null) return; //if the element does not exist, there is nothing to be done
        add(cur.getVal()); //adding the value to the tree
        add(cur.getLeft()); //traversing left
        add(cur.getRight()); //traversing right
    }
}