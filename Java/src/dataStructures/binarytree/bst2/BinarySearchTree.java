package dataStructures.binarytree.bst2;

public class BinarySearchTree<T>
{
    private final Comparator<T> comparator;

    private Node<T> root;

    public BinarySearchTree(Comparator<T> comparator) 
    { 
        this.comparator = comparator;
    }

    public void insert(T data)
    {
        Node<T>[] nodes = traverseTo(data);

        if (nodes[1] != null) return;

        Node<T> newNode = new Node<T>(data);

        if (nodes[0] == null) { root = newNode; return; }

        if   (comparator.greater(nodes[0].data(), data)) nodes[0].setLeft (newNode); 
        else                                             nodes[0].setRight(newNode);
    }

    public void remove(T data)
    {
        Node<T>[] nodes = traverseTo(data);

        if (nodes[1] == null) return;

        if (nodes[0] == null) 
        {
            if (root.isLeaf())        { root = null;         return; }
            if (root.left () == null) { root = root.right(); return; }
            if (root.right() == null) { root = root.left (); return; }

            Node<T>[] minimum = traverseToMinimum(root.left());

            root.setData(minimum[1].data());

            minimum[0].setLeft(null);

            return;
        }

        if (nodes[1].left() != null && nodes[1].right() != null)
        {
            Node<T>[] maximum = traverseToMinimum(nodes[1].left());

            nodes[1].setData(maximum[1].data());

            maximum[0].setLeft(null); 

            return;
        }

        Node<T> newChild = null;

        if      (nodes[1].isLeaf())        newChild = null;
        else if (nodes[1].left () == null) newChild = nodes[1].right();
        else if (nodes[1].right() == null) newChild = nodes[1].left ();

        if   (comparator.greater(nodes[0].data(), nodes[1].data())) nodes[0].setLeft (newChild);
        else                                                        nodes[0].setRight(newChild);
    }

    public T search(T data)
    {
        Node<T>[] nodes = traverseTo(data);

        return nodes[1] == null ? null : nodes[1].data();
    }

    public String toString()
    {
        return root == null ? "" : root.toString();
    }

    @SuppressWarnings("unchecked")
    private Node<T>[] traverseTo(T data)
    {
        Node<T> previous = null;
        Node<T> current = root;

        while (current != null)
        {
            previous = current;

            if (current.data().equals(data)) break;

            if   (comparator.greater(current.data(), data)) current = current.left ();
            else                                            current = current.right();
        }

        return new Node[] { previous, current };
    }

    @SuppressWarnings("unchecked")
    private Node<T>[] traverseToMinimum(Node<T> data)
    {
        Node<T> previous = null;
        Node<T> current = data;

        while (current.left() != null)
        {
            previous = current;

            current = current.left();
        }

        return new Node[] { previous, current };
    }
}
