package statemachine;

public abstract class State
    <TKey, TState extends State<TKey, TState, TStateMachine>, TStateMachine extends StateMachine<TKey, TState, TStateMachine>>
{
    public final TStateMachine stateMachine;

    public State(TStateMachine stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    public void enter () { }
    public void exit  () { }    
}