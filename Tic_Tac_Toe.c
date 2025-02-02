#include <stdio.h>

// Globally declared 2D-array
char board[3][3];

// Function to initialize the game board
void initializeBoard()
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            board[i][j] = ' ';
        }
    }
    int count = 1;
    printf("\n\n\t ");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%d", count++);
            if (j < 2)
            {
                printf(" | ");
            }
        }
        if (i < 2)
        {
            printf("\n\t----------\n\t");
        }
    }
    printf("\n\n\n");
}

// Function to display the game board
void showBoard()
{
    printf("\n\n");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%c", board[i][j]);
            if (j < 2)
            {
                printf(" | ");
            }
        }
        if (i < 2)
        {
            printf("\n-----------\n");
        }
    }
    printf("\n\n");
}

// Function to update the game board
int updateBoard(int cell, char playerSign)
{
    int row = (cell - 1) / 3;
    int col = (cell - 1) % 3;

    // Check if the cell is already filled
    if (board[row][col] != ' ')
    {
        printf("\nInvalid: Cell is already filled!\n");
        return 0;
    }

    // Update board
    board[row][col] = playerSign;
    showBoard();
    return 1;
}

// Function to check the winner
int checkWinner(char sg)
{
    // Check all rows and columns
    for (int i = 0; i < 3; i++)
    {
        if ((board[i][0] == sg && board[i][1] == sg && board[i][2] == sg) ||
            (board[0][i] == sg && board[1][i] == sg && board[2][i] == sg))
        {
            return 1;
        }
    }

    // Check both diagonals
    if ((board[0][0] == sg && board[1][1] == sg && board[2][2] == sg) ||
        (board[0][2] == sg && board[1][1] == sg && board[2][0] == sg))
    {
        return 1;
    }
    return 0;
}

// Function to play Tic Tac Toe
void playTicTacToe()
{
    int gameResult = 0;
    int cell = 0;
    int playCount = 0;
    int updateResult = 1;
    char playerSign;

    // Start playing the game
    while (!gameResult && playCount < 9)
    {
        if (playCount % 2 == 0)
        {
            // Player 1
            printf("\nPlayer 1 [X]: ");
            playerSign = 'X';
        }
        else
        {
            // Player 2
            printf("\nPlayer 2 [O]: ");
            playerSign = 'O';
        }
        scanf("%d", &cell);

        if (cell > 0 && cell < 10)
        {
            updateResult = updateBoard(cell, playerSign);

            // If updating is successful
            if (updateResult)
            {
                gameResult = checkWinner(playerSign);
                if (gameResult)
                {
                    printf("\t*** Player %d Won!! ***\n", playerSign == 'X' ? 1 : 2);
                }
                playCount++;
            }
        }
        else if (cell == -1)
        {
            printf("\n\tGame Terminated\n");
            return;
        }
        else
        {
            printf("\nPlease enter a valid cell value\n");
        }
    }

    // If no one won the game
    if (!gameResult && playCount == 9)
    {
        printf("\n\t *** Draw.... ***\n");
    }
    printf("\n\t *** Game Over.... ***\n");
}

int main()
{
    printf("----- Tic Tac Toe -----\n\n");

    printf("\n* Instructions \n\n");
    printf("\tPlayer 1, Sign = X\n");
    printf("\tPlayer 2, Sign = O\n");
    printf("\n\tTo exit from the game, Enter -1\n");

    printf("\n\n* Cell Numbers on Board\n");
    initializeBoard();

    printf("\n> Press Enter to start.... <");
    getchar();
    getchar();

    int userChoice = 1;

    // Restart the game
    while (userChoice)
    {
        playTicTacToe();
        printf("\n* Menu\n");
        printf("\nPress 1 to Restart");
        printf("\nPress 0 for exit");
        printf("\n\nChoice: ");
        scanf("%d", &userChoice);
        if (userChoice)
        {
            initializeBoard();
        }
    }
    printf("\n :: Thanks for playing Tic Tac Toe! :: \n");
    return 0;
}