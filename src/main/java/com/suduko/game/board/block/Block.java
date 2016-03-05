package com.suduko.game.board.block;

import com.suduko.game.response.BlockOperationResult;

import java.io.Serializable;
import java.util.Set;

/**
 * Identifies a block of cells , each containing a number
 * A block must have range of indices it has, validate adding, removing a number
 * and knowing if it contains a certain cell or not by its indices
 */
public interface Block extends Serializable {

    /**
     * Returns true of this block contains the cell with this X and Y coordinates
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return true if this cell is inside this block
     */
    boolean containsCell(int x, int y);


    /**
     * Tries to add a number to the block,
     *
     * @param number number to validate
     * @return true if the number can be added to this block
     */
    BlockOperationResult validateAddNumber(int number);


    /**
     * Adds a number to this block
     *
     * @param number number to add
     * @return result showing if the number is added and expressing the reason of failure if not
     */
    BlockOperationResult addNumber(int number);


    /**
     * Removes number from the block
     *
     * @param number number to remove
     * @return result showing if the number is removed, result always succeeds as the number will be not found on this
     * cell after removal whether it was found or not before removal
     */
    BlockOperationResult removeNumber(int number);


    /**
     * Validates block against the game rules
     * the block should be having numbers from 1 - 9
     *
     * @return operation result whether success or failure expressing the validation status
     */
    BlockOperationResult validateBlock();


    Set<Integer> getBlockValues();

    int getFromX();

    int getFromY();

    int getToX();

    int getToY();
}
