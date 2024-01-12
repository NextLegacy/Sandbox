package graph;

import java.lang.reflect.Array;

public final class Utils 
{
    @SuppressWarnings("unchecked") 
    public static <T> T[] newInstance(final Class<T> type, final int length)
    { 
        return (T[]) Array.newInstance(type, length); 
    }

    @SuppressWarnings("unchecked") 
    public static <T> Class<T> componentTypeOf(final T[] array) 
    {
        return (Class<T>) array.getClass().getComponentType(); 
    }

    @SafeVarargs 
    public static <T> T[] makeArray(final int length, final T... junk) 
    { 
        return newInstance(componentTypeOf(junk), length); 
    }
}
