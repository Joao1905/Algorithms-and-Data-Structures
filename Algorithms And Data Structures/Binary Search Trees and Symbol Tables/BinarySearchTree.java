import packages.ArrayQueue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    
    private Node root;

    private class Node {

        private Key key;
        private Value value;
        private Node left, right;
        private int count;
    
        //Key and value are generic types, key is Comparable
        public Node (Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null)
            return 0;
        return n.count;
    }

    public Key min() {
        if (isEmpty())
            return null;

        return min(root).key;
    }

    private Node min(Node n) {
        if (isEmpty())
            return null;

        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public Key max() {
        if (isEmpty())
            return null;

        return max(root).key;
    }

    private Node max(Node n) {
        if (isEmpty())
            return null;

        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    //How many keys are < than searchKey
    public int rank(Key searchKey) {
        return rank(root, searchKey);
    }

    private int rank(Node n, Key searchKey) {

        if (n == null)
            return 0;
        
        int comp = searchKey.compareTo(n.key);
        if (comp < 0)
            return rank(n.left, searchKey);
        else if (comp > 0)
            return 1 + size(n.left) + rank(n.right, searchKey);
        else
            return size(n.left);

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

    //Largest key <= searchKey
    public Key floor(Key searchKey) {
        Node n = root;
        if (isEmpty())
            return null;

        Key result = null;
        while (n != null) {
            if (result == null && n.key.compareTo(searchKey) <= 0) {
                result = n.key;
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
            } else if (result == null) {
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
            } else {
                if (n.key.compareTo(result) > 0 && n.key.compareTo(searchKey) <= 0) { //n.key > result and n.key <= searchKey
                    result = n.key;
                }
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
                else
                    return result;
            }
        }    
        return result;
    }

    //Smallest key >= searchKey
    public Key ceiling(Key searchKey) {
        Node n = root;
        if (isEmpty())
            return null;

        Key result = null;
        while (n != null) {
            if (result == null && n.key.compareTo(searchKey) >= 0) {
                result = n.key;
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
            } else if (result == null) {
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
            } else {
                if (n.key.compareTo(result) < 0 && n.key.compareTo(searchKey) >= 0) { //n.key > result and n.key <= searchKey
                    result = n.key;
                }
                int comp = searchKey.compareTo(n.key);
                if (comp < 0)
                    n = n.left;
                else if (comp > 0)
                    n = n.right;
                else
                    return result;
            }
        }    
        return result;
    }

    public Value get(Key key) {
        Node n = root;
        while(n != null) {
            int comp = key.compareTo(n.key);
            if (comp < 0)
                n = n.left;
            else if (comp > 0)
                n = n.right;
            else
                return n.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node n, Key key, Value value) {
        //Pretty tricky recursive code, but basically we will check if the next node exists or is null
        if (n == null)
            return new Node(key, value, 1); //If it's null, we create our new node, but its parent still has no link to it. If we're creating the root node, execution ends here
        
        //If it's not null, we'll dive a layer and eventually find a null spot, or the exact key we're looking for
        int comp = key.compareTo(n.key);
        if (comp < 0)
            n.left = put(n.left, key, value);   //Lets say we've found a null space for our new node and it has been created. Now execution will resume from
        else if (comp > 0)                      // here all the way up, and we are basically saying that what's on the left of someOldNode is still someOldNode,
            n.right = put(n.right, key, value); // recreating links all the way up, but also linking our new node to the three right at the beginning of out way back
        else
            n.value = value;

        n.count = 1 + size(n.left) + size(n.right);  //At the end we go from bottom to top, so we can compute node count recursively
        return n;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    //By ending links going to and from a node, it's available to be collected by the garbage collector
    public Node deleteMin(Node n) {
        if (n.left == null){
            return n.right;
        }
        n.left = deleteMin(n.left);
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node n, Key searchKey){
        if (n == null)
            return null;

        //Here we search for our key
        int comp = searchKey.compareTo(n.key);
        if (comp < 0)
            n.left = delete(n.left, searchKey);
        else if (comp > 0)
            n.right = delete(n.right, searchKey);
        else {  //And here we do our operations when we find it

            //We start by checking if the node to be removed has only one or no link, which makes it much easier to remove the node
            if (n.right == null)
                return n.left;  //Simply return the link to its child to the node above it, as it was done in deleteMin()
            if (n.left == null)
                return n.right;

            //So the node does have 2 children. We'll find the smallest node on the right tree of the node to be deleted
            // then, it'll take the deleted node's position, and the deleted node will lose all it's links
            Node t = n;
            n = min(t.right);   //Node is now identical to the smallest node on the right tree
            n.right = deleteMin(t.right);   //This updates n's right link, and also deletes t right link
            n.left = t.left;                //Finally, we update n's left link
        }

        //Now we update our node counts recursivelly
        n.count = size(n.left) + size(n.right) +1;
        return n;
    }

    public static void main(String[] args) {
        
        /*
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        System.out.println(bst.min());
        bst.put("S", 1); bst.put("E", 3); bst.put("R", 2);
        bst.put("X", 4); bst.put("A", 7); bst.put("C", 2);
        bst.put("H", 4); bst.put("M", 7);
        System.out.println(bst.floor("G"));
        System.out.println(bst.ceiling("G"));
        System.out.println(bst.size());
        System.out.println(bst.rank("E"));
        
        for(String s : bst.keys())
            System.out.print(s+" ");
        System.out.print("\n");

        bst.deleteMin();

        for(String s : bst.keys())
            System.out.print(s+" ");
        System.out.print("\n");

        System.out.println(bst.floor("G"));
        System.out.println(bst.ceiling("G"));
        System.out.println(bst.size());
        System.out.println(bst.rank("E"));

        bst.delete("E");

        for(String s : bst.keys())
            System.out.print(s+" ");
        System.out.print("\n");

        System.out.println(bst.floor("G"));
        System.out.println(bst.ceiling("G"));
        System.out.println(bst.size());
        System.out.println(bst.rank("E"));
        */

    }

    /* PERFORMANCE 
    Execution Time proportional to (Worst, Avg):
        Search: N, 1.39 lg N
        Insert: N, 1.39 lg N
        Delete: N, sqrt(N)

    Everytime we use delete, it introduces assimetry to our tree, reducing its general performance
    Worst case would be a totally unbalanced tree (N), while best case would be perfectly balanced tree (log N)
    */

    //Interesting fact: BST work very simillarly to quicksort's partition method, since both keep everything
    // larger than X to the right, and everything lower than X to the right

}
