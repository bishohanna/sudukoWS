package com.suduko.game.board.builder;

import com.suduko.game.board.SudukoBoardImpl;

public abstract class SudukoBuilderNode {


    private SudukoBuilderNode nextNode;


    public void handle(SudukoBoardImpl board, int[][] boardValues) {

        //1 - process current node
        processBoard(board, boardValues);

        //2 - handles next node
        if (nextNode != null) {
            nextNode.handle(board, boardValues);
        }

    }


    /**
     * Does node processing on the suduko board
     *
     * @param board       board
     * @param boardValues initial values
     */
    protected abstract void processBoard(SudukoBoardImpl board, int[][] boardValues);

    /**
     * Sets next node in the chain
     *
     * @param nextNode next node
     * @return next node if found or this node if there is no next node
     */
    public SudukoBuilderNode setNextNode(SudukoBuilderNode nextNode) {
        if (nextNode != null) {
            this.nextNode = nextNode;

            return nextNode;
        }

        return this;
    }
}
