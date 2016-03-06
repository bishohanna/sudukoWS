package com.suduko;

import com.suduko.game.board.SudukoBoard;
import com.suduko.game.board.SudukoBoardBuilder;
import com.suduko.game.response.BoardResult;
import org.junit.Assert;
import org.junit.Test;

public class SudukoBoardValidationTest {


    private final int[][] sudukoCompleteBoard = new int[][]
            {
                    {5, 3, 4, 6, 7, 8, 9, 1, 2},
                    {6, 7, 2, 1, 9, 5, 3, 4, 8},
                    {1, 9, 8, 3, 4, 2, 5, 6, 7},
                    {8, 5, 9, 7, 6, 1, 4, 2, 3},
                    {4, 2, 6, 8, 5, 3, 7, 9, 1},
                    {7, 1, 3, 9, 2, 4, 8, 5, 6},
                    {9, 6, 1, 5, 3, 7, 2, 8, 4},
                    {2, 8, 7, 4, 1, 9, 6, 3, 5},
                    {3, 4, 5, 2, 8, 6, 1, 7, 9},

            };

    private final int[][] sudukoUnCompleteBoard = new int[][]
            {
                    {5, 3, 4, 6, 7, 8, 9, 1, 2},
                    {6, 7, 2, 1, 9, 5, 3, 4, 8},
                    {1, 9, 8, 3, 4, 2, 5, 6, 0},//7 remaining
                    {8, 5, 9, 7, 6, 1, 4, 2, 3},
                    {4, 2, 6, 8, 5, 3, 7, 9, 1},
                    {7, 1, 3, 9, 2, 0, 8, 5, 6},//4 remaining
                    {9, 6, 1, 5, 3, 7, 2, 8, 4},
                    {2, 8, 7, 4, 1, 9, 6, 3, 5},
                    {3, 4, 5, 2, 8, 6, 1, 7, 9},

            };


    @Test
    public void testBoardValidation() {

        SudukoBoard unCompleteBoard = createUnCompleteBoard();
        BoardResult validateUncomplete = unCompleteBoard.validateBoard();
        //un complete board should be un valid
        Assert.assertFalse("Board should be unvalid", validateUncomplete.isBoardValid());


        SudukoBoard completeBoard = createCompleteBoard();
        BoardResult validateComplete = completeBoard.validateBoard();
        //complete board should be valid
        Assert.assertTrue("Board should be valid", validateComplete.isBoardValid());
    }


    @Test
    public void testFinalMoveValidation() {

        //create board
        SudukoBoard board = createUnCompleteBoard();

        BoardResult preFinalMoveResult = board.addNumber(7, 3, 9);
        //operation succeeds and board is not valid
        Assert.assertTrue("Prefinal operation should succeed", preFinalMoveResult.isOperationSuccess());
        Assert.assertFalse("Board should be not valid", preFinalMoveResult.isBoardValid());

        BoardResult finalMove = board.addNumber(4, 6, 6);
        //operation scceeds and board is valid
        Assert.assertTrue("Final operation should succeed", finalMove.isOperationSuccess());
        Assert.assertTrue("Board should be valid after final move", finalMove.isBoardValid());

    }


    private SudukoBoard createCompleteBoard() {
        return new SudukoBoardBuilder().buildBoard(sudukoCompleteBoard).getBoard();
    }

    private SudukoBoard createUnCompleteBoard() {
        return new SudukoBoardBuilder().buildBoard(sudukoUnCompleteBoard).getBoard();
    }

}
