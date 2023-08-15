package Recursion;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    char currentPlayer;
    char[][] moves = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'},
    };

    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();
        game.play();

    }

    void play() {


        displayBoard();

        boolean gameOver = false;

        char[] players = {'X', 'O'};
        int turn = new Random().nextInt(2);
        currentPlayer = players[turn];
        int rows = 0, cols = 0;

        while (!gameOver) {

            System.out.println("Player " + currentPlayer + " Turn");
            if (currentPlayer == 'X') {
                System.out.println("Enter a move : ");
                int choice = new Scanner(System.in).nextInt();
                rows = getRow(choice);
                cols = getColumn(choice);
            } else if (currentPlayer == 'O') {
                int[] best = findBestMove(moves);
                rows = best[0];
                cols = best[1];
            }
            System.out.println(validMove(rows, cols));
            if (validMove(rows, cols)) {

                makeMove(rows, cols);
                displayBoard();

                if (evaluateBoard(moves) == 1) {
                    gameOver = true;
                    System.out.println("Player X wins");
                } else if (evaluateBoard(moves) == -1) {
                    gameOver = true;
                    System.out.println("Player O wins");
                } else if (isBoardFull()) {
                    gameOver = true;
                    System.out.println("Game Ends in a draw");
                } else
                    switchPlayer();

            } else System.out.println("Invalid move try again");

        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (moves[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    int getRow(int choice) {
        switch (choice) {
            case 1, 2, 3 -> {
                return (int) 0;
            }
            case 4, 5, 6 -> {
                return (int) 1;
            }
            case 7, 8, 9 -> {
                return (int) 2;
            }
            default -> {
                return 3;
            }
        }
    }

    int getColumn(int choice) {
        switch (choice) {
            case 1, 4, 7 -> {
                return (int) 0;
            }
            case 2, 5, 8 -> {
                return (int) 1;
            }
            case 3, 6, 9 -> {
                return (int) 2;
            }
            default -> {
                return 3;
            }
        }

    }

    boolean validMove(int row, int col) {
        if (row < 3 && row >= 0 && col < 3 && col >= 0 && moves[row][col] == '-')
            return true;
        return false;
    }

    void makeMove(int row, int col) {
        moves[row][col] = currentPlayer;
    }

    void displayBoard() {
        System.out.println("---------------------");
        System.out.println("| " + moves[0][0] + " | " + moves[0][1] + " | " + moves[0][2] + " | ");
        System.out.println("| " + moves[1][0] + " | " + moves[1][1] + " | " + moves[1][2] + " | ");
        System.out.println("| " + moves[2][0] + " | " + moves[2][1] + " | " + moves[2][2] + " | ");
        System.out.println("---------------------");
    }

    int evaluateBoard(char[][] moves) {
        //Check rows
        for (int i = 0; i < moves.length; i++) {
            if ((moves[i][0] == moves[i][1]) && (moves[i][1] == moves[i][2])) {
                if (moves[i][0] == 'X')
                    return 1;
                else if (moves[i][0] == 'O')
                    return -1;
            }
        }

        //Check columns
        for (int i = 0; i < moves.length; i++) {
            if ((moves[0][i] == moves[1][i]) && (moves[1][i] == moves[2][i])) {
                if (moves[0][i] == 'X')
                    return 1;
                else if (moves[0][i] == 'O')
                    return -1;
            }
        }
        if (moves[0][0] == moves[1][1] && moves[1][1] == moves[2][2]) {
            if (moves[0][0] == 'X')
                return 1;
            else if (moves[0][0] == 'O')
                return -1;
        }
        if (moves[0][2] == moves[1][1] && moves[1][1] == moves[2][0]) {
            if (moves[0][2] == 'X')
                return 1;
            else if (moves[0][2] == 'O')
                return -1;
        }
        return 0;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public int[] findBestMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                    int score = minimax(board, 0, false);
                    board[i][j] = '-';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    public int minimax(char[][] board, int depth, boolean isMaximizing) {
        int score = evaluateBoard(board);
        int SIZE = 3;
        char EMPTY = '-';
        char PLAYER_X = 'X';
        char PLAYER_O = 'O';

        if (score == 1 || score == -1 || score == 0) {
            return score;
        }
        if (isMaximizing) {
            int bestScore = -10000;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_O;
                        int currentScore = minimax(board, depth + 1, false);
                        board[i][j] = EMPTY;
                        bestScore = Math.max(currentScore, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 10000;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_X;
                        int currentScore = minimax(board, depth + 1, true);
                        board[i][j] = EMPTY;
                        bestScore = Math.min(currentScore, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}


