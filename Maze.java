import java.util.Scanner;

public class Maze {
    private static final int WALL = 0;
    private static final int PATH = 1;
    private static final int PLAYER = 2;
    private static final int EXIT = 3;

    private static int[][] maze = {
        {WALL, PATH, WALL, WALL, WALL},
        {WALL, PATH, PATH, PATH, WALL},
        {WALL, WALL, WALL, PATH, WALL},
        {PATH, PATH, PATH, PATH, WALL},
        {WALL, WALL, WALL, EXIT, WALL}
    };

    private static int playerRow = 0;
    private static int playerCol = 1;
    private static int last = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;

        while (!gameWon) {
            printMaze();
            System.out.print("Enter your move (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            switch (move) {
                case 'W':
                    movePlayer(-1, 0);
                    break;
                case 'A':
                    movePlayer(0, -1);
                    break;
                case 'S':
                    movePlayer(1, 0);
                    break;
                case 'D':
                    movePlayer(0, 1);
                    break;
                default:
                    System.out.println("Invalid move. Use W, A, S, or D.");
                    break;
            }

            gameWon = checkWin();
        }

        System.out.println("Congratulations! You've reached the exit!");
        scanner.close();
    }

    private static void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("P ");
                } else {
                    switch (maze[i][j]) {
                        case WALL:
                            System.out.print("# ");
                            break;
                        case PATH:
                            System.out.print(". ");
                            break;
                        case EXIT:
                            System.out.print("E ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void movePlayer(int rowChange, int colChange) {
        if (maze[playerRow + rowChange][playerCol + colChange] == WALL) {
            return;
        }
        maze[playerRow][playerCol] = PATH;
        playerRow += rowChange;
        playerCol += colChange;
        last = maze[playerRow][playerCol];
        maze[playerRow][playerCol] = PLAYER;
    }

    private static boolean checkWin() {
        return last == EXIT;
    }
}


