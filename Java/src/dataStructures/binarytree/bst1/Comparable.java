package dataStructures.binarytree.bst1;

/**
 * A interface for comparing objects.
 * 
 * @param <T> The type of data to be compared.
 * 
 * @author NextLegacy
 */

public interface Comparable<T> 
{
    /**
     * Compares this object to another object.
     * 
     * @param other The object to be compared to.
     * 
     * @return A negative number if this object is less than the other object, a positive number if this object is greater than the other object, and 0 if the objects are equal.
     */
    public int compareTo(T other);

    /**
     * Determines if this object is greater than another object.
     * 
     * @param other The object to be compared to.
     * 
     * @return True if this object is greater than the other object, false otherwise.
     */
    default boolean greaterThan(T other) { return compareTo(other) > 0; }
    
    /**
     * Determines if this object is less than another object.
     * 
     * @param other The object to be compared to.
     * 
     * @return True if this object is less than the other object, false otherwise.
     */
    default boolean lessThan(T other) { return compareTo(other) < 0; }
    
    /**
     * Determines if this object is equal to another object.
     * 
     * @param other The object to be compared to.
     * 
     * @return True if this object is equal to the other object, false otherwise.
     */
    default boolean isEqual(T other) { return compareTo(other) == 0; }
}
