package com.suduko.game.board.block;

public class HorizontalBlock extends AbstractBlock {

    /**
     * Horizontal block is the block that has fixed y value and variable x value defined from 1 to total size
     *
     * @param yValue    y value
     * @param totalSize total size of block
     */
    public HorizontalBlock(int yValue, int totalSize) {
        super(1, totalSize, yValue, yValue, totalSize);
    }
}
