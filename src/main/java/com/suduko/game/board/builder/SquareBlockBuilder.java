package com.suduko.game.board.builder;

import com.suduko.game.board.SudukoBoardImpl;
import com.suduko.game.board.block.SquareBlock;

/**
 * Builds a Square block in the suduko board
 */
public final class SquareBlockBuilder extends SudukoBuilderNode {


    @Override
    protected void processBoard(SudukoBoardImpl board, int[][] boardValues) {

        int blockNumbers = boardValues.length;
        int blockLength = Double.valueOf(Math.sqrt(Double.valueOf(String.valueOf(blockNumbers)))).intValue();

        int yCounter = 1;
        for (int row = 0; row < blockLength; row++) {

            int fromYValue = yCounter;
            int toYValue = yCounter + blockLength - 1;

            int xCounter = 1;
            for (int column = 0; column < blockLength; column++) {

                int fromXValue = xCounter;
                int toXValue = xCounter + blockLength - 1;

                //create the block
                SquareBlock squareBlock = new SquareBlock(fromXValue, toXValue, fromYValue, toYValue, blockNumbers);
                board.addBlock(squareBlock);

                xCounter += blockLength;
            }

            yCounter += blockLength;
        }
    }
}
