package com.suduko;

import com.suduko.game.board.block.Block;
import com.suduko.game.response.BlockOperationResult;

import java.util.List;

public class SudukoTestUtils {


    public static void printBlockResults(List<BlockOperationResult> blockOperationResults) {
        for (BlockOperationResult result : blockOperationResults) {
            Block block = result.getBlock();
            System.out.println(String.format("Block [%s,%s],[%s,%s], Success: %s, Message: %s",
                    block.getFromX(), block.getToX(), block.getFromY(), block.getToY(), result.isOperationSuccess(), result.getMessage()));
        }
    }


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
