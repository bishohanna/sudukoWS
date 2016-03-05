package com.suduko.game.board.block;

public class VerticalBlock extends AbstractBlock {


    /**
     * Vertical block is a block that has fixed x value and different y value identified from 1 to total size
     *
     * @param xValue    xValue
     * @param totalSize total size of block (vertically)
     */
    public VerticalBlock(int xValue, int totalSize) {
        super(xValue, xValue, 1, totalSize, totalSize);
    }

}
