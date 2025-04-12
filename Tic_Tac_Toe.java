import java.util.Scanner;

public class Tic_Tac_Toe {

    static char[][] board = new char[3][3];

    // Initialize board with empty spaces and show cell numbers
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        System.out.println("\n\n\t Cell Numbers:");
        int count = 1;
        System.out.print("\t ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(count++);
                if (j < 2)
                    System.out.print(" | ");
            }
            if (i < 2)
                System.out.print("\n\t---------\n\t");
        }
        System.out.println("\n");
    }

    // Display the current game board
    public static void showBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2)
                    System.out.print(" | ");
            }
            if (i < 2)
                System.out.println("\n---------");
        }
        System.out.println();
    }

    // Update the board if the cell is empty
    public static boolean updateBoard(int cell, char playerSign) {
        int row = (cell - 1) / 3;
        int col = (cell - 1) % 3;

        if (board[row][col] != ' ') {
            System.out.println("\nInvalid: Cell is already filled!");
            return false;
        }

        board[row][col] = playerSign;
        showBoard();
        return true;
    }

    // Check for a winner
    public static boolean checkWinner(char sg) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == sg && board[i][1] == sg && board[i][2] == sg) || // row
                    (board[0][i] == sg && board[1][i] == sg && board[2][i] == sg)) { // column
                return true;
            }
        }

        // Diagonals
        if ((board[0][0] == sg && board[1][1] == sg && board[2][2] == sg) ||
                (board[0][2] == sg && board[1][1] == sg && board[2][0] == sg)) {
            return true;
        }

        return false;
    }

    // Game loop
    public static void playTicTacToe(Scanner sc) {
        boolean gameResult = false;
        int playCount = 0;
        char playerSign;

        while (!gameResult && playCount < 9) {
            if (playCount % 2 == 0) {
                System.out.print("\nPlayer 1 [X]: ");
                playerSign = 'X';
            } else {
                System.out.print("\nPlayer 2 [O]: ");
                playerSign = 'O';
            }

            int cell;
            try {
                cell = sc.nextInt();
            } catch (Exception e) {
                sc.next(); // clear invalid input
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (cell == -1) {
                System.out.println("\n\tGame Terminated\n");
                return;
            }

            if (cell >= 1 && cell <= 9) {
                boolean updated = updateBoard(cell, playerSign);
                if (updated) {
                    gameResult = checkWinner(playerSign);
                    if (gameResult) {
                        System.out.println("\t*** Player " + (playerSign == 'X' ? "1" : "2") + " Won!! ***");
                        break;
                    }
                    playCount++;
                }
            } else {
                System.out.println("\nPlease enter a valid cell value (1-9 or -1 to exit)");
            }
        }

        if (!gameResult && playCount == 9) {
            System.out.println("\n\t *** Draw.... ***");
        }
        System.out.println("\n\t *** Game Over.... ***");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Tic Tac Toe -----\n");
        System.out.println("* Instructions\n");
        System.out.println("\tPlayer 1, Sign = X");
        System.out.println("\tPlayer 2, Sign = O");
        System.out.println("\n\tTo exit from the game, Enter -1\n");

        initializeBoard();
        System.out.println("\n> Press Enter to start.... <");
        sc.nextLine();

        int userChoice = 1;

        while (userChoice != 0) {
            playTicTacToe(sc);
            System.out.println("\n* Menu");
            System.out.println("Press 1 to Restart");
            System.out.println("Press 0 to Exit");
            System.out.print("Choice: ");

            try {
                userChoice = sc.nextInt();
            } catch (Exception e) {
                sc.next(); // clear invalid input
                userChoice = 0;
            }

            if (userChoice == 1) {
                initializeBoard();
            }
        }

        System.out.println("\n :: Thanks for playing Tic Tac Toe! :: ");
        sc.close();
    }
}