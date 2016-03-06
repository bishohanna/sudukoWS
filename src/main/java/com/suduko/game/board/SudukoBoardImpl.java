package com.suduko.game.board;

import com.suduko.game.board.block.Block;
import com.suduko.game.response.BlockOperationResult;
import com.suduko.game.response.BoardResult;

import java.util.LinkedList;
import java.util.List;

public final class SudukoBoardImpl implements SudukoBoard {


    private final int[][] board;
    private final int totalLength;
    private final List<Block> blocks = new LinkedList<>();


    public SudukoBoardImpl(int totalLength) {
        this.totalLength = totalLength;
        this.board = new int[totalLength][totalLength];
    }


    /**
     * Validates if a number can be added to X Y cell , without adding it
     *
     * @param number number
     * @param x      X
     * @param y      Y
     * @return result explaining if the number can be added or not
     */
    @Override
    public BoardResult validateAddNumber(int number, int x, int y) {

        if (!validateXYValues(x, y)) {
            return new BoardResult(this, "X Y coordinates are invalid !", false);
        }

        boolean canBeAdded = true;

        //check blocks
        List<BlockOperationResult> validateResults = new LinkedList<>();
        for (Block block : blocks) {
            if (block.containsCell(x, y)) {

                BlockOperationResult validateResult = block.validateAddNumber(number);
                validateResults.add(validateResult);

                if (!validateResult.isOperationSuccess()) {
                    canBeAdded = false;
                }
            }
        }

        return new BoardResult(this, canBeAdded)
                .setMessage(canBeAdded ? "Number can be added" : "Number can't be added").setBoardValid(false);
    }


    /**
     * Adds number to the board - if valid
     *
     * @param number number to add
     * @param x      X
     * @param y      Y
     * @return result explaining if the number is added or not
     */
    @Override
    public BoardResult addNumber(int number, int x, int y) {

        if (!validateXYValues(x, y)) {
            return new BoardResult(this, "X Y coordinates are invalid !", false);
        }

        boolean addSuccess = true;

        //handle blocks
        List<BlockOperationResult> addResults = new LinkedList<>();
        for (Block block : blocks) {
            if (block.containsCell(x, y)) {

                BlockOperationResult addResult = block.addNumber(number);
                addResults.add(addResult);

                if (!addResult.isOperationSuccess()) {
                    addSuccess = false;
                }
            }
        }

        //handle board
        if (addSuccess) {
            board[x - 1][y - 1] = number;
        }

        //check if board valid - return the validation success result
        BoardResult boardValidation = validateBoard();
        if (boardValidation.isOperationSuccess()) {
            return boardValidation;
        }

        return new BoardResult(this, addSuccess)
                .setMessage(addSuccess ? "Number is added" : "Number can't be added")
                .setOperationResults(addResults).setBoardValid(false);
    }


    /**
     * Removes number from the given coordinates
     *
     * @param number number
     * @param x      x axis
     * @param y      y axis
     * @return board result containing the current board snapshot after removing the number
     */
    @Override
    public BoardResult removeNumber(int number, int x, int y) {

        if (!validateXYValues(x, y)) {
            return new BoardResult(this, "X Y coordinates are invalid !", false);
        }

        //remove from blocks
        List<BlockOperationResult> removeResults = new LinkedList<>();
        for (Block block : blocks) {
            if (block.containsCell(x, y)) {
                removeResults.add(block.removeNumber(number));
            }
        }

        //remove from board
        board[x - 1][y - 1] = 0;//reset the value

        return new BoardResult(this, "Number removed", true).setOperationResults(removeResults).setBoardValid(false);
    }

    /**
     * Validates every block in the board if it is valid
     *
     * @return board result containing the current board snapshot and all block results found
     */
    @Override
    public BoardResult validateBoard() {

        boolean boardValid = true;

        List<BlockOperationResult> blockResults = new LinkedList<>();
        for (Block block : blocks) {
            BlockOperationResult blockOperationResult = block.validateBlock();
            if (!blockOperationResult.isOperationSuccess()) {
                boardValid = false;
            }

            blockResults.add(blockOperationResult);
        }

        return new BoardResult(this, boardValid)
                .setOperationResults(blockResults)
                .setBoardValid(boardValid)
                .setMessage(boardValid ? "Board is Valid" : "Board is Invalid");
    }


    /**
     * Validates if X and Y values are found inside the board borders
     *
     * @param x X
     * @param y Y
     * @return true if values are valid
     */
    private boolean validateXYValues(int x, int y) {
        return (x >= 1 && x <= totalLength) && (y >= 1 && y <= totalLength);
    }

    @Override
    public int[][] getBoardValues() {
        return board.clone();
    }

    @Override
    public List<Block> getBoardBlocks() {
        return new LinkedList<>(blocks);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
}
