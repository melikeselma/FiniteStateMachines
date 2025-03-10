import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        State X = new State("X");
        State Y = new State("Y");
        State Z = new State("Z");
        State T = new State("T");
        State R = new State("R");
        State U = new State("U");


        ArrayList<State> states = new ArrayList<>();
        states.add(X);
        states.add(Y);
        states.add(Z);
        states.add(T);
        states.add(R);
        states.add(U);

        ArrayList<Character> inputs = new ArrayList<>();
        inputs.add('a');
        inputs.add('b');
        inputs.add('c');

        ArrayList<State> initStates = new ArrayList<>();
        initStates.add(X);

        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(T);

        FiniteStateMachine fsm = new FiniteStateMachine(states, inputs, initStates, finalStates);
        fsm.assign(new Transition(X, X, 'a'));
        fsm.assign(new Transition(X, X, 'b'));
        fsm.assign(new Transition(X, X, 'c'));
        fsm.assign(new Transition(X, Y, 'c'));
        fsm.assign(new Transition(X, R, 'a'));
        fsm.assign(new Transition(Y, Z, 'b'));
        fsm.assign(new Transition(Z, T, 'a'));
        fsm.assign(new Transition(T, T, 'a'));
        fsm.assign(new Transition(T, T, 'b'));
        fsm.assign(new Transition(T, T, 'c'));
        fsm.assign(new Transition(R, U, 'b'));
        fsm.assign(new Transition(U, T, 'c'));

        boolean result = fsm.check("abaccacba");
        System.out.println(result);
    }
}