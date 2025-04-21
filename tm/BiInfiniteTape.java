package tm;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a bi-infinite tape for the Turing Machine
 * 
 * @author Andrew Kobus, Eric Johnson
 */
public class BiInfiniteTape {
    private Map<Integer, Integer> tape; // Position -> Symbol
    private int headPosition;
    private int minVisited;
    private int maxVisited;

    /**
     * Constructor for a BiInfiniteTape
     */
    public BiInfiniteTape() {
        this.tape = new HashMap<>();
        this.headPosition = 0;
        this.minVisited = 0;
        this.maxVisited = 0;
    }

    /**
     * Resets the head position to the beginning
     */
    public void resetHead() {
        this.headPosition = 0;
    }

    /**
     * Reads the symbol at the current head position
     * 
     * @return the symbol at the current head position (0 for blank)
     */
    public int read() {
        return this.tape.getOrDefault(headPosition, 0); // 0 is blank symbol
    }

    /**
     * Writes a symbol at the current head position
     * 
     * @param symbol the symbol to write
     */
    public void write(int symbol) {
        this.tape.put(headPosition, symbol);
        updateVisitedRange();
    }

    /**
     * Moves the head to the left
     */
    public void moveLeft() {
        this.headPosition--;
        updateVisitedRange();
    }

    /**
     * Moves the head to the right
     */
    public void moveRight() {
        this.headPosition++;
        updateVisitedRange();
    }

    /**
     * Updates the range of visited tape cells
     */
    private void updateVisitedRange() {
        this.minVisited = Math.min(minVisited, headPosition);
        this.maxVisited = Math.max(maxVisited, headPosition);
    }

    /**
     * Gets the content of all visited tape cells
     * 
     * @return a string representation of the visited tape cells
     */
    public String getVisitedContent() {
        if (minVisited > maxVisited) {
            throw new IllegalStateException("Invalid tape range: minVisited > maxVisited");
        }
        StringBuilder result = new StringBuilder();

        for (int i = minVisited; i <= maxVisited; i++) {
            result.append(tape.getOrDefault(i, 0));
        }

        return result.toString();
    }
}