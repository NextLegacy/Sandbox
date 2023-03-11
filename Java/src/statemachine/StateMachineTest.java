package statemachine;

import java.util.HashMap;

public class StateMachineTest 
{
    public class Apple 
    {
        float eaten;
        float y;
    }

    public class AppleState extends State<String, AppleState, AppleStateMachine>
    {
        public AppleState(AppleStateMachine stateMachine)
        {
            super(stateMachine);
        }
    }

    public class AppleStateMachine extends StateMachine<String, AppleState, AppleStateMachine>
    {
        public Apple apple;

        public AppleStateMachine()
        {
            super();
        }

        @Override
        protected void generateStates(final HashMap<String, AppleState> states)
        {
            
        }
    }


    public static void main(String[] args) 
    {

    }
}
