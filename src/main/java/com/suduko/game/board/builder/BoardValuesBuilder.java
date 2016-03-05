package com.suduko.game.board.builder;

import com.suduko.game.board.SudukoBoardImpl;

/**
 * Add values to the board
 */
public final class BoardValuesBuilder extends SudukoBuilderNode {

    @Override
    protected void processBoard(SudukoBoardImpl board, int[][] boardValues) {
        int totalLength = boardValues.length;

        for (int xIndex = 0; xIndex < totalLength; xIndex++) {

            for (int yIndex = 0; yIndex < totalLength; yIndex++) {
                int number = boardValues[xIndex][yIndex];
                if (number > 0) {
                    board.addNumber(number, xIndex + 1, yIndex + 1);
                }
            }
        }
    }
}
