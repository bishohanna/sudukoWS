package com.suduko;

import com.suduko.game.board.SudukoBoard;
import com.suduko.game.board.SudukoBoardBuilder;
import com.suduko.game.board.block.Block;
import com.suduko.game.response.BlockOperationResult;
import com.suduko.game.response.BoardResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SudukoBoardMovementTest {


    private final int[][] boardValues = new int[][]
            {
                    {7, 0, 0, 0, 4, 0, 5, 3, 0},
                    {0, 0, 5, 0, 0, 8, 0, 1, 0},
                    {0, 0, 8, 5, 0, 9, 0, 4, 0},
                    {5, 3, 9, 0, 6, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 5},
                    {8, 0, 0, 7, 2, 0, 9, 0, 0},
                    {9, 0, 7, 4, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 5, 7, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 5, 0},

            };


    @Test
    public void testAddNumber() {
        //create board
        SudukoBoard board = createSampleBoard();

        BoardResult adding1 = board.addNumber(1, 1, 2);

        Assert.assertTrue("Adding number 1 at (1,2) should succeed", adding1.isOperationSuccess());

        //print output
        printBlockResults(adding1.getOperationResults());
        SudukoTestUtils.printBoard(adding1.getBoard().getBoardValues());
    }

    @Test
    public void testAddWrongNumber() {
        //create board
        SudukoBoard board = createSampleBoard();

        //4 is invalid in this place
        BoardResult adding4 = board.addNumber(4, 1, 4);

        Assert.assertFalse("Adding the number 4 should fail", adding4.isOperationSuccess());

        //print output
        printBlockResults(adding4.getOperationResults());
        SudukoTestUtils.printBoard(adding4.getBoard().getBoardValues());
    }

    @Test
    public void testRemoveNumber() {
        //create board
        SudukoBoard board = createSampleBoard();

        //add a number
        BoardResult adding1 = board.addNumber(1, 1, 2);
        Assert.assertTrue("Adding number 1 at (1,2) should succeed", adding1.isOperationSuccess());
        printBlockResults(adding1.getOperationResults());
        SudukoTestUtils.printBoard(adding1.getBoard().getBoardValues());

        //remove it
        BoardResult remove1 = board.removeNumber(1, 1, 2);
        Assert.assertTrue("Removing number 1 at (1,2) should succeed", remove1.isOperationSuccess());

        printBlockResults(remove1.getOperationResults());
        SudukoTestUtils.printBoard(remove1.getBoard().getBoardValues());
    }


    private SudukoBoard createSampleBoard() {
        return new SudukoBoardBuilder(boardValues).buildBoard().getBoard();
    }

    private void printBlockResults(List<BlockOperationResult> blockOperationResults) {
        for (BlockOperationResult result : blockOperationResults) {
            Block block = result.getBlock();
            System.out.println(String.format("Block [%s,%s],[%s,%s], Success: %s, Message: %s",
                    block.getFromX(), block.getToX(), block.getFromY(), block.getToY(), result.isOperationSuccess(), result.getMessage()));
        }
    }
}
