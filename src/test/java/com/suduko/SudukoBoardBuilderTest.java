package com.suduko;

import com.suduko.game.board.SudukoBoardBuilder;
import com.suduko.game.response.BoardResult;
import org.junit.Assert;
import org.junit.Test;

public class SudukoBoardBuilderTest {

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
    public void testBuildingBlocks() {
        //build board
        BoardResult boardResult = new SudukoBoardBuilder(boardValues).buildBoard();

        //board should be created successfully
        Assert.assertTrue("Board Creation error", boardResult.isOperationSuccess());

        //blocks should be added to board
        Assert.assertTrue("Blocks shouldn't be empty", !boardResult.getBoard().getBoardBlocks().isEmpty());

        //blocks should not be valid
        Assert.assertTrue("All blocks shouldn't be valid", !boardResult.getBoard().validateBoard().isOperationSuccess());


        //print board state
        SudukoTestUtils.printBoard(boardResult.getBoard().getBoardValues());
    }

}
