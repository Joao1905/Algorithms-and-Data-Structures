import packages.ArrayQueue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {

        private Key key;
        private Value value;
        private Node left, right;
        boolean color;              //Color of parent's link
        //private int count;
    
        //Key and value are generic types, key is Comparable
        public Node (Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            //this.count = count;
        }
    
    }

    public Value get(Key key) {
        Node n = root;
        while (n != null) {
            int comp = key.compareTo(n.key);
            if (comp < 0)
                n = n.left;
            else if (comp > 0)
                n = n.left;
            else
                return n.value;
        }
        return null;
    }

    public void put (Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node n, Key key, Value value) {
        //This first part is exactly the same as BSTs, so basically just insert the node where it belongs
        if (n == null)
            return new Node(key, value, RED);   //As in 2-3 Search trees, new keys  are always 3-link nodes (or 4 links, but temporarily)

        int comp = key.compareTo(n.key);
        if (comp < 0)
            n.left = put(n.left, key, value);
        else if (comp > 0)
            n.right = put(n.right, key, value);
        else
            n.value = value;

        //Now, on a worst case we'll need to rotate left, rotate right and flip colors. On a better case, only rotate right and flip colors,
        // on an even better case, we'll need only to flip colors, and the best case is nothing should be done. These are the only possible scenarios
        if (isRed(n.right) && !isRed(n.left))       //This line may happen at the first recurisve pass from bottom to top, when we're recreating links
            n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left))    //This would probably happen at the second recursive pass from bottom to top, after the operation above
            n = rotateRight(n);                     // has been made for the child of the node we are analyzing now.
        if (isRed(n.left) && isRed(n.right))
            flipColors(n);                                        

        return n;
    }

    private Node rotateLeft(Node n) {
        assert isRed(n.right);
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }

    //We will need this temporarily
    private Node rotateRight(Node n) {
        assert isRed(n.left);
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        x.color = n.color;
        n.color = RED;
        return x;
    }

    //This will be useful when we're doing the equivalent of taking the middle key of a temporary 4 node and making it the parent of the
    // smaller and larger keys, which will both have red links, so we want to make them both black, and the link between the middle key and its parent red
    private void flipColors(Node n) {
        assert !isRed(n);
        assert isRed(n.left);
        assert isRed(n.right);
        n.left.color = BLACK;
        n.right.color = BLACK;
        n.color = RED;
    }

    private boolean isRed(Node n) {
        if (n == null)
            return false; 
        return n.color == RED;
    }

    //In order traversal
    public Iterable<Key> keys() {
        ArrayQueue<Key> queue = new ArrayQueue<Key>();
        traverse(root, queue);
        return queue;
    }

    private void traverse(Node n, ArrayQueue<Key> queue) {
        if (n == null)
            return;
        traverse(n.left, queue);
        queue.enqueue(n.key);
        traverse(n.right, queue);
    }

    public static void main(String[] args) {

        RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
        bst.put("S", 1); bst.put("E", 3); bst.put("R", 2);
        bst.put("X", 4); bst.put("A", 7); bst.put("C", 2);
        bst.put("H", 4); bst.put("M", 7);

        for(String s : bst.keys())
            System.out.print(s+" ");
        System.out.print("\n");
        

    }

}
