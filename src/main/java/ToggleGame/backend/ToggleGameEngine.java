package ToggleGame.backend;
import ToggleGame.frontend.ToggleGameInteraction;
import com.sun.jdi.event.StepEvent;

import java.util.*;

import static ToggleGame.backend.GameHelper.*;

/**
 * Buttons have the following order / placement on the screen
 *
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *
 * For the Colors: BLACK is 0 and WHITE = 1
 *
 * This file is incomplete and should be completed
 * Once it is completed please update this message
 */
public class ToggleGameEngine implements ToggleGameInteraction {

    private final HashMap<Integer, String> mask;

    /**
     * Constructor. Initialises the mask
     */
    public ToggleGameEngine() {
        mask = new HashMap<>();
        setMask();
    }
    /**
     * Initialize and return the game board for a ToggleGame (9x "1")
     * @return the String "111111111" to start a game with all white squares
     */
    @Override
    public String initializeGame() {
        //starter code ... replace the below code with a string containing all 1's
        setMask();
        return "111111111";
    }

    /**
     * Update the game board for the given button that was clicked.
     * Squares marked as 0 are black and 1 is white
     *
     * @param button the game board square button that was clicked (between 0 and 8)
     *
     * @return the updated game board as a String giving the button colors in order
     *         with "0" for black and "1" for white.
     *
     * @throws IllegalArgumentException when button is outside 0-8
     */
    @Override
    public String buttonClicked(String current, int button) {
        //starter code...replace the below code

        int binaryMask = stringToBinary(mask.get(button));
        int binaryCurrent = stringToBinary(current);

        return binaryToString(binaryCurrent ^ binaryMask);
    }


    /**
     * Return a sequence of moves that leads in the minimum number of moves
     * from the current board state to the target state
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the sequence of moves to advance the board from current to target.
     *         Each move is the number associated with a button on the board. If no moves are
     *         required to advance the currentBoard to the target an empty array is returned.
     */
    @Override
    public int[] movesToSolve(String current, String target) {
        //starter code ... replace the below
        if(current.equals(target))
            return new int[0];

        return findMoves(current, target);
    }

    /**
     * Return the minimum required number of required moves (button clicks)
     * to advance the current board to the target board.
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the minimum number of moves to advance the current board
     * to the target
     */
    @Override
    public int minNumberOfMoves(String current, String target) {
        //starter code ... replace the below
        if(current.equals(target))
            return 0;
        return movesToSolve(current, target).length;
    }

    /**
     * Sets the mask value for each button
     */
    private void setMask() {
        mask.put(0, "110100000");
        mask.put(1, "111010000");
        mask.put(2, "11001000");
        mask.put(3, "100110100");
        mask.put(4, "10111010");
        mask.put(5, "1011001");
        mask.put(6, "100110");
        mask.put(7, "10111");
        mask.put(8, "1011");
    }

    private void findMoves(ArrayList<Integer> minMoves, ArrayList<Integer> moves, String parent, String target, String current, Set<String> visited) {
        if(current.equals(target)) {
            if(minMoves.isEmpty() || minMoves.size() > moves.size()) {
                minMoves.clear();
                minMoves.addAll(moves);
            }
            return;
        }
        if(visited.contains(current))
            return;
        visited.add(current);
        for(int i = 0; i < 9; i++) {
            String nextState = buttonClicked(current, i);
            if(!nextState.equals(parent)) {
                moves.add(i);
                findMoves(minMoves, moves, current, target, nextState, visited);
                moves.removeLast();
            }
        }
    }

    /**
     * Finds minimum moves to reach the target
     * @param start Current state of the board
     * @param target Target to reach
     * @return An array with button numbers to reach the target
     */
    private int[] findMoves(String start, String target) {
        Queue<String> q = new LinkedList<>();
        HashMap<String, String> parent = new HashMap<>(); // child, parent
        HashMap<String, Integer> move = new HashMap<>(); // State, Button
        q.add(start);

        parent.put(start, start);

        while (!q.isEmpty()) {
            String current = q.poll();

            if(current.equals(target))
                return constructPath(parent, move, current, start);

            for(int i = 0; i < 9; i++){
                String nextState = buttonClicked(current, i);
                if(!parent.containsKey(nextState)) {
                    parent.put(nextState, current);
                    move.put(nextState, i);
                    q.add(nextState);
                }
            }
        }
        return new int[0];
    }

    /**
     * Constructs a path from starting state of the board to a target
     * @param parent A Hashmap representing parent and child relationship
     * @param move A Hashmap to representing a board state and the button number
     * @param current A string representing current state of the board
     * @param root A string representing target board state
     * @return An array with button numbers to reach the target
     */
    private int[] constructPath(HashMap<String, String> parent, HashMap<String, Integer> move, String current, String root) {
        List<Integer> path = new ArrayList<>();
        while(!current.equals(root)) {
            int button = move.get(current);
            path.add(button);
            current = parent.get(current);
        }
        Collections.reverse(path);
        return path.stream().mapToInt(Integer::intValue).toArray();
    }
}
