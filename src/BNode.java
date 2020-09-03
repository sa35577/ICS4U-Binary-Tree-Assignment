public class BNode {
    private int val,depth;
    private BNode left, right;

    public BNode(int v) {
        val = v;
        left = right = null;
    }
    public int getVal() {return val;}
    public void setVal(int v) {val = v;}
    public BNode getLeft() {return left;}
    public BNode getRight() {return right;}
    public void setLeft(BNode lef) {left = lef;}
    public void setRight(BNode r) {right = r;}

}
