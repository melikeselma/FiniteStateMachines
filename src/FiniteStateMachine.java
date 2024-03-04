import java.util.ArrayList;
import java.util.List;

public class FiniteStateMachine {
    private final List<State> states;
    private final List<Character> inputs;
    private final List<State> initStates;
    private final List<State> finalStates;
    private final List<Transition> transitions;
    public FiniteStateMachine(List<State> states, List<Character> inputs, List<State> initStates, List<State> finalStates){
        this.states = states;
        this.inputs= inputs;
        this.initStates = initStates;
        this.finalStates = finalStates;
        this.transitions = new ArrayList<>();
    }

    public void assign (Transition transition){
        if (transitions.contains(transition)) {
            throw new InvalidTransition("This transition is already assigned");
        }

        if (!states.contains(transition.getFromState())){
            throw new InvalidTransition("This transition contains invalid from state");
        }

        if (!states.contains(transition.getToState())){
            throw new InvalidTransition("This transition contains invalid to state");
        }

        if (!inputs.contains(transition.getInput())){
            throw new InvalidTransition("This transition contains invalid input");
        }

        transitions.add(transition);
    }

    public List<State> transition(State fromState, Character input) {
        List<State> toStates = new ArrayList<>();

        for (Transition transition :
                transitions) {
            if (transition.getFromState().equals(fromState) &&  transition.getInput().equals(input)){
                toStates.add(transition.getToState());
            }
        }

        return toStates;
    }

    public List<State> lastStates(String word){
        List<State> _lastStates = new ArrayList<>();

        List<State> fromStates = initStates;
        for (int i = 0; i < word.length(); i++) {
            List<State> reachedStates = new ArrayList<>();
            char input = word.charAt(i);
            for (State fromState: fromStates) {
                reachedStates.addAll(transition(fromState, input));
                fromStates = reachedStates;
            }
            _lastStates = reachedStates;
        }

        return _lastStates;
    }

    public boolean check(String word) {
        List<State> _lastStates = lastStates(word);

        for (State lastState : _lastStates) {
            for (State finalState: finalStates) {
                if (lastState.equals(finalState)) {
                    return true;
                }
            }
        }
        return false;
    }
}
