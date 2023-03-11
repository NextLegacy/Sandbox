package dataStructures.binarytree.bst2;

class Node<T>
{
    private T data;

    private Node<T> left;
    private Node<T> right;

    public Node() { this(null, null, null); }

    public Node(T data) { this(data, null, null); }

    public Node(T data, Node<T> left, Node<T> right)
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
