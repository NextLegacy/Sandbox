package bettergenerics;

import bettergenerics.generics.*;

@$({ App.class, int.class }) 
class App
{
    public static void main(String[] args)
    {
        new App().run();
    }
    
    public App() { }
    public App(float f) { System.out.println(f); }

    private final void run()
    {
        Test test = new Test().<Test>T_Instantiate(0, 0.1f);
        System.out.println(test);
    }
}
