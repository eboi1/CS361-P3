package tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a bi-infinite Turing Machine
 * 
 * @author Andrew Kobus, Eric Johnson
 */
public class TuringMachine {
    private int numStates;
    private int currentState;
    private Map<String, Transition> transitions;
    private BiInfiniteTape tape;

    /**
     * Constructor for a TuringMachine
     * 
     * @param numStates  the number of states in the TM
     * @param numSymbols the number of symbols in the input alphabet
     */
    public TuringMachine(int numStates, int numSymbols) {
        this.numStates = numStates;
        this.currentState = 0; // Start state is 0
        this.transitions = new HashMap<>();
        this.tape = new BiInfiniteTape();
    }

    /**
     * Adds a transition to the TM
     * 
     * @param fromState   the state to transition from
     * @param onSymbol    the symbol under the head
     * @param nextState   the state to transition to
     * @param writeSymbol the symbol to write on the tape
     * @param move        the direction to move the head (L or R)
     */
    public void addTransition(int fromState, int onSymbol, int nextState, int writeSymbol, char move) {
        if (fromState < 0 || fromState >= numStates || nextState < 0 || nextState >= numStates) {
            throw new IllegalArgumentException("Invalid state: must be in range 0 to " + (numStates - 1));
        }

        if (onSymbol < 0 || writeSymbol < 0) {
            throw new IllegalArgumentException("Symbols must be non-negative integers.");
        }

        if (move != 'L' && move != 'R') {
            throw new IllegalArgumentException("Invalid move direction: " + move + ". Must be 'L' or 'R'.");
        }

        String key = fromState + "," + onSymbol;
        if (transitions.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate transition for key: " + key);
        }
    
        transitions.put(key, new Transition(nextState, writeSymbol, move));
    }

    /**
     * Sets the input string on the tape
     * 
     * @param input the input string to set on the tape
     */
    public void setInput(String input) {
        tape.resetHead();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Invalid input character: '" + c + "'. Only digits are allowed.");
            }
    
            int symbol = Character.getNumericValue(c);
            tape.write(symbol);
            tape.moveRight();
        }

        tape.resetHead(); // Reset the head to the beginning of the input
    }

    /**
     * Runs the TM simulation
     * 
     * @return the content of the visited tape cells
     */
    public String run() {
        currentState = 0; // Start at state 0
        int haltingState = numStates - 1;

        while (currentState != haltingState) {
            int symbol = tape.read();
            String key = currentState + "," + symbol;

            Transition transition = transitions.get(key);
            if (transition == null) {
                throw new RuntimeException("No transition defined for state " + currentState +
                        " and symbol " + symbol);
            }

            // Apply the transition
            tape.write(transition.writeSymbol);

            if (transition.move == 'L') {
                tape.moveLeft();
            } else if (transition.move == 'R') {
                tape.moveRight();
            } else {
                throw new RuntimeException("Invalid move direction: " + transition.move);
            }

            currentState = transition.nextState;
        }

        return tape.getVisitedContent();
    }
}
