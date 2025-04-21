package tm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TMSimulator simulates a bi-infinite Turing Machine
 * 
 * @author Andrew Kobus, Eric Johnson
 */
public class TMSimulator {

    /**
     * Main method that reads the TM description from a file and simulates its
     * execution
     * 
     * @param args command line arguments, expects the input file name as the first
     *             argument
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java tm.TMSimulator <input_file>");
            System.exit(1);
        }

        String filename = args[0];
        TuringMachine tm = null;

        try {
            tm = parseTM(filename);
            String result = tm.run();
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Parses the Turing Machine description from a file
     * 
     * @param filename the name of the file containing the TM description
     * @return the parsed TuringMachine object
     * @throws IOException if an error occurs while reading the file
     */
    private static TuringMachine parseTM(String filename) throws IOException {

        // Check if the file exists and is readable
        File file = new File(filename);
        if (!file.exists() || !file.canRead()) {
            System.err.println("Error: Cannot read file '" + filename + "'");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // Parse number of states
        String line = reader.readLine();
        if (line == null) {
            reader.close();
            throw new IOException("Missing number of states line.");
        }
        int numStates = Integer.parseInt(line.trim());

        // Parse number of symbols in Sigma
        line = reader.readLine();
        if (line == null) {
            reader.close();
            throw new IOException("Missing number of symbols line.");
        }
        int numSymbols = Integer.parseInt(line.trim());

        // Create a new Turing Machine
        TuringMachine tm = new TuringMachine(numStates, numSymbols);

        // Parse transitions
        for (int state = 0; state < numStates - 1; state++) {
            for (int symbol = 0; symbol <= numSymbols; symbol++) {
                String transitionLine = reader.readLine().trim();
                String[] parts = transitionLine.split(",");

                try {
                    int nextState = Integer.parseInt(parts[0]);
                    int writeSymbol = Integer.parseInt(parts[1]);
                    char move = parts[2].charAt(0);
                
                    // Validate direction
                    if (move != 'L' && move != 'R' && move != 'S') {
                        throw new IOException("Invalid move direction '" + move + "' at state " + state + ", symbol " + symbol);
                    }
                
                    tm.addTransition(state, symbol, nextState, writeSymbol, move);
                } catch (NumberFormatException e) {
                    reader.close();
                    throw new IOException("Invalid number format in transition: " + transitionLine, e);
                }
            }
        }

        // Parse input string
        String inputLine = reader.readLine();
        String input = (inputLine != null) ? inputLine.trim() : "";
        tm.setInput(input);

        reader.close();
        return tm;
    }
}