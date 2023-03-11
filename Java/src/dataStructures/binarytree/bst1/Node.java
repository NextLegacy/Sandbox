package dataStructures.binarytree.bst1;

/**
 * A node in a binary search tree.
 * 
 * @param <T> The type of data stored in the node.
 * 
 * @author NextLegacy
 * 
 * @see BinarySearchTree
 */
class Node<T>
{
    /** The data stored in the node. */
    private T data;

    /** The left child of the node. */
    private Node<T> left;

    /** The right child of the node. */
    private Node<T> right;

    /** 
     * Creates a new node with no data. 
     */
    Node() { this(null, null, null); }

    /** Creates a new node with the specified data. */
    Node(T data) { this(data, null, null); }

    /** 
     * Creates a new node with the specified data and children. 
     */
    Node(T data, Node<T> left, Node<T> right)
    {
        this.data  = data;
        this.left  = left;
        this.right = right;
    }

    public boolean isLeaf() { return left == null && right == null; }

    public T data() { return data; }

    public Node<T> left () { return left; }

    public Node<T> right() { return right; }

    public void setData (T       data ) { this.data  = data ; }
    public void setLeft (Node<T> left ) { this.left  = left ; }
    public void setRight(Node<T> right) { this.right = right; }

    @Override
    public String toString()
    {
        String result = data.toString() + "\n";

        if (left  != null) result += left .toString(1);
        if (right != null) result += right.toString(1);

        return result;
    }

    private String toString(int depth)
    {
        String indent = "";

        for (int j = 0; j < depth; j++) indent += "-";
        
        depth++;

        String result = indent + data.toString() + "\n";

        if (left  != null) result += left .toString(depth);
        if (right != null) result += right.toString(depth);

        return result;
    }
}
