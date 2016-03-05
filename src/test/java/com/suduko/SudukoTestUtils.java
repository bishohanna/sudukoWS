package com.suduko;

public class SudukoTestUtils {

    public static void printBoard(int[][] board) {

        int length = board.length;

        for (int x = 0; x < length; x++) {

            for (int y = 0; y < length; y++) {
                System.out.print(" || ");
                System.out.print(board[x][y]);
            }

            System.out.println();
        }
    }
}
