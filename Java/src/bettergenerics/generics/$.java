package bettergenerics.generics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * The Class {@code $} is used to define the Type Argumants for Methods or/and Classes
 * 
 * BetterGenerics was a just a try to implement a better way to define Type Arguments
 * And also to learn something about Annotations and Reflection
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface $
{
    Class<?>[] value();
}