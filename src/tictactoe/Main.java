package tictactoe;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    static String player = "X";

    public static void main(String[] args) {

        printBoard(board);

        while (!gameOver()) {
            takeTurn();
        }

        if (checkWin("X")) {
            System.out.println("X wins");
        } else if (checkWin("O")) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }

    public static boolean gameOver() {
        boolean xWins = checkWin("X");
        boolean oWins = checkWin("O");

        return xWins || oWins || !movesAvailable();
    }

    public static boolean takeTurn (){

        int x = 0;
        int y = 0;

        while (x < 1 && y < 1){
            System.out.print("Enter coordinates: ");
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
                x = scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
        }

        if (x > 3 || x < 0 || y > 3 || y < 0) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (validMove(board, x, y)) {
            updateBoard(x, y, player);
            printBoard(board);
            player = player.equals("X") ? "O" : "X";
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    public static void updateBoard(int x, int y, String player) {
        x = 3 - x;
        y--;
        board[x][y] = player;
    }

    public static boolean validMove(String[][] board, int x, int y) {
        x = 3 - x;
        y--;

        if (board[x][y].equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    public static void printBoard(String[][] arr) {
        System.out.println("---------");
        System.out.println("| " + arr[0][0] + " " + arr[0][1] + " " + arr[0][2] + " |");
        System.out.println("| " + arr[1][0] + " " + arr[1][1] + " " + arr[1][2] + " |");
        System.out.println("| " + arr[2][0] + " " + arr[2][1] + " " + arr[2][2] + " |");
        System.out.println("---------");
    }

    public static boolean checkWin(String player) {
        int[][][] winningCombos = {
                {{0,0}, {0,1}, {0,2}},
                {{1,0}, {1,1}, {1,2}},
                {{2,0}, {2,1}, {2,2}},
                {{0,0}, {1,0}, {2,0}},
                {{0,1}, {1,1}, {2,1}},
                {{0,2}, {1,2}, {2,2}},
                {{0,0}, {1,1}, {2,2}},
                {{0,2}, {1,1}, {2,0}}
        };

        for (int[][] r : winningCombos) {
            if (board[r[0][0]][r[0][1]].equals(player)
                    && board[r[1][0]][r[1][1]].equals(player)
                    && board[r[2][0]][r[2][1]].equals(player)
            ) { return true; }
        }
        return false;
    }

    public static boolean movesAvailable() {
        for (String[] row : board) {
            for (String cell: row) {
                if (!cell.equals("X") && !cell.equals("O")) {
                    return true;
                }
            }
        }

        return false;
    }
}
