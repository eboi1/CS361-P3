package tm;

/**
 * Represents a transition in the Turing Machine
 * 
 * @author Andrew Kobus
 */
public class Transition {
    int nextState;
    int writeSymbol;
    char move;

    /**
     * Constructor for a Transition
     * 
     * @param nextState   the next state to transition to
     * @param writeSymbol the symbol to write on the tape
     * @param move        the direction to move the head (L or R)
     */
    public Transition(int nextState, int writeSymbol, char move) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.move = move;
    }
}
