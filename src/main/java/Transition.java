import java.util.Objects;

public class Transition {

    private final State fromState;
    private final State toState;
    private final Character input;

    public State getFromState() {
        return fromState;
    }

    public State getToState() {
        return toState;
    }

    public Character getInput() {
        return input;
    }

    public Transition(State fromState, State toState, Character input) {
        this.fromState = fromState;
        this.toState = toState;
        this.input = input;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "fromState=" + fromState +
                ", toState=" + toState +
                ", input=" + input +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return Objects.equals(fromState, that.fromState) && Objects.equals(toState, that.toState) && Objects.equals(input, that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromState, toState, input);
    }
}
