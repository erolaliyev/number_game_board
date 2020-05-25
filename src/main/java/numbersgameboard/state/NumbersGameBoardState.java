package numbersgameboard.state;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the state of the puzzle.
 */
@Data
@Slf4j
public class NumbersGameBoardState implements Cloneable {
//
//    /**
//     * The array representing the initial configuration of the tray.
//     */
//    public static final int[][] INITIAL = {
//            {1, 1, 1},
//            {1, 0, 1},
//            {1, 1, 1}
//    };
//
//    /**
//     * The array representing a near-goal configuration of the tray.
//     */
//    public static final int[][] NEAR_GOAL = {
//            {1, 0, 2},
//            {3, 5, 2},
//            {6, 1, 5}
//    };
//
//    /**
//     * The array storing the current configuration of the tray.
//     */
//    @Setter(AccessLevel.NONE)
//    private Cube[][] tray;
//
//    /**
//     * The row of the empty space.
//     */
//    @Setter(AccessLevel.NONE)
//    private int emptyRow;
//
//    /**
//     * The column of the empty space.
//     */
//    @Setter(AccessLevel.NONE)
//    private int emptyCol;
//
//    /**
//     * Creates a {@code NumbersGameBoardState} object representing the (original)
//     * initial state of the puzzle.
//     */
//    public NumbersGameBoardState() {
//        this(INITIAL);
//    }
//
//    /**
//     * Creates a {@code NumbersGameBoardState} object that is initialized it with
//     * the specified array.
//     *
//     * @param a an array of size 3&#xd7;3 representing the initial configuration
//     *          of the tray
//     * @throws IllegalArgumentException if the array does not represent a valid
//     *                                  configuration of the tray
//     */
//    public NumbersGameBoardState(int[][] a) {
//        if (!isValidBoard(a)) {
//            throw new IllegalArgumentException();
//        }
//        initBoard(a);
//    }
//
//    private boolean isValidBoard(int[][] a) {
//        if (a == null || a.length != 3) {
//            return false;
//        }
//        boolean foundEmpty = false;
//        for (int[] row : a) {
//            if (row == null || row.length != 3) {
//                return false;
//            }
//            for (int space : row) {
//                if (space < 0 || space >= Cube.values().length) {
//                    return false;
//                }
//                if (space == Cube.EMPTY.getValue()) {
//                    if (foundEmpty) {
//                        return false;
//                    }
//                    foundEmpty = true;
//                }
//            }
//        }
//        return foundEmpty;
//    }
//
//    private void initBoard(int[][] a) {
//        this.tray = new Cube[3][3];
//        for (int i = 0; i < 3; ++i) {
//            for (int j = 0; j < 3; ++j) {
//                if ((this.tray[i][j] = Cube.of(a[i][j])) == Cube.EMPTY) {
//                    emptyRow = i;
//                    emptyCol = j;
//                }
//            }
//        }
//    }
//
//    /**
//     * Checks whether the puzzle is solved.
//     *
//     * @return {@code true} if the puzzle is solved, {@code false} otherwise
//     */
//    public boolean isSolved() {
//        for (Cube[] row : tray) {
//            for (Cube cube : row) {
//                if (cube != Cube.CUBE6 && cube != Cube.EMPTY) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Returns whether the cube at the specified position can be rolled to the
//     * empty space.
//     *
//     * @param row the row of the cube to be rolled
//     * @param col the column of the cube to be rolled
//     * @return {@code true} if the cube at the specified position can be rolled
//     * to the empty space, {@code false} otherwise
//     */
//    public boolean canRollToEmptySpace(int row, int col) {
//        return 0 <= row && row <= 2 && 0 <= col && col <= 2 &&
//                Math.abs(emptyRow - row) + Math.abs(emptyCol - col) == 1;
//    }
//
//    /**
//     * Returns the direction to which the cube at the specified position is
//     * rolled to the empty space.
//     *
//     * @param row the row of the cube to be rolled
//     * @param col the column of the cube to be rolled
//     * @return the direction to which the cube at the specified position is
//     * rolled to the empty space
//     * @throws IllegalArgumentException if the cube at the specified position
//     * can not be rolled to the empty space
//     */
//    public Direction getRollDirection(int row, int col) {
//        if (! canRollToEmptySpace(row, col)) {
//            throw new IllegalArgumentException();
//        }
//        return Direction.of(emptyRow - row, emptyCol - col);
//    }
//
//    /**
//     * Rolls the cube at the specified position to the empty space.
//     *
//     * @param row the row of the cube to be rolled
//     * @param col the column of the cube to be rolled
//     * @throws IllegalArgumentException if the cube at the specified position
//     * can not be rolled to the empty space
//     */
//    public void rollToEmptySpace(int row, int col) {
//        Direction direction = getRollDirection(row, col);
//        log.info("Cube at ({},{}) is rolled to {}", row, col, direction);
//        tray[emptyRow][emptyCol] = tray[row][col].rollTo(direction);
//        tray[row][col] = Cube.EMPTY;
//        emptyRow = row;
//        emptyCol = col;
//    }

    public void moveVertically(int row, int column){
    }
//
//    public NumbersGameBoardState clone() {
//        NumbersGameBoardState copy = null;
//        try {
//            copy = (NumbersGameBoardState) super.clone();
//        } catch (CloneNotSupportedException e) {
//        }
//        copy.tray = new Cube[tray.length][];
//        for (int i = 0; i < tray.length; ++i) {
//            copy.tray[i] = tray[i].clone();
//        }
//        return copy;
//    }
//
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Cube[] row : tray) {
//            for (Cube cube : row) {
//                sb.append(cube).append(' ');
//            }
//            sb.append('\n');
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        NumbersGameBoardState state = new NumbersGameBoardState();
//        System.out.println(state);
//        state.rollToEmptySpace(0, 1);
//        System.out.println(state);
//    }
}
