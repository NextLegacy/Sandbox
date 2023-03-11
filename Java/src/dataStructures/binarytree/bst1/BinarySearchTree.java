package dataStructures.binarytree.bst1;

/**
 * A binary search tree.
 * 
 * @param <T> The type of data stored in the tree.
 * 
 * @author NextLegacy
 * 
 * @see Node
 * @see Comparable
 */
public class BinarySearchTree<T extends Comparable<T>>
{
    /** The root of the tree. */
    private Node<T> root;

    /**
     * Creates a new binary search tree.
     */
    public BinarySearchTree() { }

    /**
     * Inserts a new node into the tree.
     * 
     * @param data The data to be inserted.
     */
    public void insert(T data)
    {
        // data is null, do nothing
        if (data == null) return;

        Node<T>[] nodes = traverseTo(data);

        // node already exists, do nothing
        if (nodes[1] != null) return;

        Node<T> newNode = new Node<T>(data);

        // root is null, set the root to the new node
        if (nodes[0] == null) { root = newNode; return; }

        // set the left or right child to the new node
        if   (data.lessThan(nodes[0].data())) nodes[0].setLeft (newNode); 
        else                                  nodes[0].setRight(newNode);
    }

    /**
     * Removes a node from the tree.
     * 
     * @param data The data to be removed.
     */
    public void remove(T data)
    {
        // If the data is null, do nothing.
        if (data == null) return;
        
        Node<T>[] nodes = traverseTo(data);

        // node does not exist
        if (nodes[1] == null) return;

        if (nodes[0] == null) // node is the root
        {
            if (root.isLeaf()       ) { root = null;         return; } // root is  a leaf        | set root to null
            if (root.left () == null) { root = root.right(); return; } // root has a right child | set root to right child
            if (root.right() == null) { root = root.left (); return; } // root has a left  child | set root to left child

            // root has two children

            Node<T>[] minimum = traverseToMinimum(root.left());

            root.setData(minimum[1].data());

            minimum[0].setLeft(minimum[1].right());

            return;
        }

        //  Node to be removed is an internal node
        if (nodes[1].left() != null && nodes[1].right() != null)
        {
            Node<T>[] minimum = traverseToMinimum(nodes[1].left());

            nodes[1].setData(minimum[1].data());

            minimum[0].setLeft(minimum[1].right()); 

            return;
        }

        // the new child is the left or right child of the node to be removed
        Node<T> newChild = null;

        if      (nodes[1].isLeaf()       ) newChild = null;             // node is  a leaf        
        else if (nodes[1].left () == null) newChild = nodes[1].right(); // node has a right child
        else if (nodes[1].right() == null) newChild = nodes[1].left (); // node has a left  child

        // set the left or right child of the parent to the new child
        if   (nodes[1].data().lessThan(nodes[0].data())) nodes[0].setLeft (newChild);
        else                                             nodes[0].setRight(newChild);
    }

    /**
     * Searches for a node in the tree.
     * 
     * @param data The data to be searched for.
     * 
     * @return The node containing the data, or null if the node does not exist.
     */
    public T search(T data)
    {
        Node<T>[] nodes = traverseTo(data);

        return nodes[1] == null ? null : nodes[1].data();
    }

    /**
     * Returns a string representation of the tree.
     * 
     * @return A string representation of the tree.
     */
    public String toString()
    {
        return root == null ? "" : root.toString();
    }

    /**
     * Traverses the tree to the node containing the data.
     * 
     * @param data The data to be searched for.
     * 
     * @return An array of length 2 containing the parent and node containing the data. [parent, node]
     */
    @SuppressWarnings("unchecked")
    private Node<T>[] traverseTo(T data)
    {
        Node<T> previous = null;
        Node<T> current = root;
        
        while (current != null && !data.isEqual(current.data()))
        {
            previous = current;

            current = data.lessThan(current.data()) ? current.left () : 
                                                      current.right();
        }

        return new Node[] { previous, current };
    }

    /**
     * Traverses the tree to the minimum node in the subtree.
     * 
     * @param data The root of the subtree.
     * 
     * @return An array of length 2 containing the parent and minimum node of the subtree. [parent, node]
     */
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
