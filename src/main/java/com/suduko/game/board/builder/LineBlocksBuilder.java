package com.suduko.game.board.builder;

import com.suduko.game.board.SudukoBoardImpl;
import com.suduko.game.board.block.HorizontalBlock;
import com.suduko.game.board.block.VerticalBlock;

/**
 * Adds vertical and horizontal blocks
 */
public final class LineBlocksBuilder extends SudukoBuilderNode {


    @Override
    protected void processBoard(SudukoBoardImpl board, int[][] boardValues) {

        int blockNumbers = boardValues.length;

        for (int index = 0; index < blockNumbers; index++) {

            board.addBlock(new HorizontalBlock(index + 1, blockNumbers));
            board.addBlock(new VerticalBlock(index + 1, blockNumbers));

        }
    }
}
