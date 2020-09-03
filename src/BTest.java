public class BTest {
    public static void main(String[] args) {/*
        BTree bush = new BTree();
        bush.add(50);
        System.out.println(bush.depth(50));
        bush.add(31);
        System.out.println(bush.depth(31));
        bush.add(105);
        System.out.println(bush.depth(105));
        bush.add(71);
        System.out.println(bush.depth(71));
        bush.add(4);
        System.out.println(bush.depth(4));
        System.out.println(bush.depth(15));
        bush.delete(71);
        System.out.println(bush.depth(71));
        System.out.println(bush);*/
        BTree maple=new BTree();
        maple.add(45);
        maple.add(23);
        maple.add(15);
        maple.add(27);
        maple.add(39);
        maple.add(35);
        maple.add(37);
        maple.add(164);
        maple.add(73);
        maple.add(48);
        maple.add(170);

        BTree spruce=new BTree();
        spruce.add(69);
        spruce.add(420);
        spruce.add(69);
        spruce.add(50);
        spruce.add(45);
        spruce.add(58);

        System.out.println(maple.depth(164));
        System.out.println(maple.depth(102));
        System.out.println(spruce.depth(69));
        System.out.println(spruce.depth(45));

        System.out.println(maple.countLeaves());
        System.out.println(spruce.countLeaves());

        System.out.println(maple.height());
        System.out.println(spruce.height());

        System.out.println(maple.isAncestor(23,37));
        System.out.println(maple.isAncestor(103, 37));
        System.out.println(maple.isAncestor(23,103));
        System.out.println(maple.isAncestor(12,93));
        System.out.println(spruce.isAncestor(69,69));

        System.out.println(maple.isBalanced());
        maple.delete(27);
        System.out.println(maple.isBalanced());

        System.out.println(spruce.isBalanced());
        spruce.delete(69);
        System.out.println(spruce.isBalanced());
        spruce.add(69);
        System.out.println(spruce.isBalanced());

        System.out.println(maple.display());
        System.out.println(spruce.display());
        spruce.add(maple);
        System.out.println(spruce.display());


    }



}
