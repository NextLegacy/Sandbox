package bettergenerics.generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings({ "unused" })
public class TypeArguments
{
    private Class<?> clazz;
    private Method method;
    private $ generic;
    private Class<?>[] classes;
    
    public TypeArguments()               { initialize(getClass()); }
    public TypeArguments(Class<?> clazz) { initialize(clazz     ); }
    public TypeArguments(Method method)  { initialize(method    ); }
    
    public final void initialize(Method method)
    {
        this.method = method;

        this.initialize(method.getDeclaredAnnotation($.class));
    }

    public final void initialize(Class<?> clazz)
    {
        this.clazz = clazz;

        this.initialize(clazz.getDeclaredAnnotation($.class));
    }

    public final void initialize($ generic)
    {
        this.generic = generic;
        this.classes = this.generic.value();
    }

    @SuppressWarnings("unchecked")
    public <TValue> Class<TValue> T(int n)
    {
        return (Class<TValue>) this.classes[n];
    }

    @SuppressWarnings("unchecked")
    public <TValue> TValue T_Instantiate(int n, Object... parameters)
    {
        Class<?>[] parameterTypes = new Class[parameters.length];
        
        for (int i = 0, length = parameters.length; i < length; i++)
        {
            Class<?> c = parameters[i].getClass();

            parameterTypes[i] = c == Integer  .class ? int    .class :
                                c == Float    .class ? float  .class :
                                c == Double   .class ? double .class :
                                c == Character.class ? char   .class :
                                c == Short    .class ? short  .class :
                                c == Long     .class ? long   .class :
                                c == Boolean  .class ? boolean.class :
                                c == Byte     .class ? byte   .class :
                                c;
        }
        
        try 
        {
            Constructor<?> constructor = T(n).getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return (TValue) constructor.newInstance(parameters);
        } 
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
        {
            e.printStackTrace();
        }

        return null;
    }
}
