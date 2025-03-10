import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FiniteStateMachineTest {
    State A;
    State B;
    State C;
    State D;
    FiniteStateMachine fsm;

    @BeforeEach
    public void setUp() {
        State A = new State("A");
        State B = new State("B");
        State C = new State("C");
        State D = new State("D");

        ArrayList<State> states = new ArrayList<>();
        states.add(A);
        states.add(B);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('0');
        inputs.add('1');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(A);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(B);
    }

    @Test
    public void Test_DuplicateAssignedTransitionsReturnsInvalidTransition() {
        State A = new State("A");
        State B = new State("B");

        ArrayList<State> states = new ArrayList<>();
        states.add(A);
        states.add(B);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('0');
        inputs.add('1');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(A);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(B);

        FiniteStateMachine fsm = new FiniteStateMachine(states, inputs, initStates, finalStates);

        Assertions.assertThrows(
                InvalidTransition.class,
                () -> {
                    fsm.assign(new Transition(A, B, '0'));
                    fsm.assign(new Transition(A, B, '0'));
                }
        );
    }

    @Test
    public void Test_FromStateOfTransitionIsNotValidForFSM_ReturnsInvalidTransition() {
        State A = new State("A");
        State B = new State("B");
        State C = new State("C");

        ArrayList<State> states = new ArrayList<>();
        states.add(A);
        states.add(B);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('0');
        inputs.add('1');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(A);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(B);

        FiniteStateMachine fsm = new FiniteStateMachine(states, inputs, initStates, finalStates);

        Assertions.assertThrows(
                InvalidTransition.class,
                () -> {
                    fsm.assign(new Transition(C, B, '0'));
                }
        );
    }

//    @Test
//    public void Test_ToStateOfTransitionIsNotValidForFSM_ReturnsInvalidTransition() {
//        Assertions.assertThrows(
//                InvalidTransition.class,
//                () -> {
//                    fsm.assign(new Transition(B, C, '0'));
//                }
//        );
//    }

    @Test
    public void Test_InputOfTransitionIsNotValidForFSM_ReturnsInvalidTransition() {
        State A = new State("A");
        State B = new State("B");

        ArrayList<State> states = new ArrayList<>();
        states.add(A);
        states.add(B);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('0');
        inputs.add('1');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(A);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(B);

        FiniteStateMachine fsm = new FiniteStateMachine(states, inputs, initStates, finalStates);

        Assertions.assertThrows(
                InvalidTransition.class,
                () -> {
                    fsm.assign(new Transition(A, B, '2'));
                }
        );
    }

    @Test
    public void Test_TransitionReturnsValidToStates() {
        State A = new State("A");
        State B = new State("B");
        State C = new State("C");

        ArrayList<State> states = new ArrayList<>();
        states.add(A);
        states.add(B);
        states.add(C);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('0');
        inputs.add('1');
        inputs.add('2');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(A);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(B);

        FiniteStateMachine fsm = new FiniteStateMachine(states, inputs, initStates, finalStates);


        fsm.assign(new Transition(A, B, '0'));
        fsm.assign(new Transition(A, B, '1'));
        fsm.assign(new Transition(A, C,'0'));

        ArrayList<State> transitionStates = new ArrayList<>();
        transitionStates.add(B);
        transitionStates.add(C);

        assert fsm.transition(A, '0').equals(transitionStates);
    }
}