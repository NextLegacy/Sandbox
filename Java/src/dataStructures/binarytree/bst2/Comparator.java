package dataStructures.binarytree.bst2;

@FunctionalInterface
public interface Comparator<T>
{
    int compare(T left, T right);

    default boolean greater(T left, T right) { return compare(left, right) >  0; }
    default boolean less   (T left, T right) { return compare(left, right) <  0; }
    default boolean equal  (T left, T right) { return compare(left, right) == 0; }
}
