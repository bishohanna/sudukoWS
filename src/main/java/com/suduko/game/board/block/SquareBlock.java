package com.suduko.game.board.block;

public class SquareBlock extends AbstractBlock {

    /**
     * A square block is that one that has variable x and y value ranges from 1 to the total size
     *
     * @param fromX     from X
     * @param toX       to X
     * @param fromY     from y
     * @param toY       to y
     * @param totalSize total length of the board
     */
    public SquareBlock(int fromX, int toX, int fromY, int toY, int totalSize) {
        super(fromX, toX, fromY, toY, totalSize);
    }
}
