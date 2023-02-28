package bettergenerics;

import bettergenerics.generics.*;

@$({ Test.class, float.class })
public class Test extends TypeArguments
{
    public Test() { this(0.1f); }
    public Test(float f)
    {
        System.out.println(T(0));
    } 
}
