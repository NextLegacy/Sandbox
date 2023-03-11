package statemachine;

import java.util.HashMap;

public abstract class StateMachine<TKey, TState extends State<TKey, TState, TStateMachine>, TStateMachine extends StateMachine<TKey, TState, TStateMachine>>
{
    protected TState state;

    protected final HashMap<TKey, TState> states;

    public StateMachine()
    {
        states = new HashMap<>();
        generateStates(states);
    }
    
    public TState getState(TKey key)
    {
        return states.get(key);
    }

    public TState state()
    {
        return state;
    }

    public void setState(TState newState)
    {
        if (state != null)
            state.exit();

        state = newState;

        if (state != null)
            state.enter();
    }

    protected void generateStates(final HashMap<TKey, TState> states)
    {

    }
}
