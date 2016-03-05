package com.suduko.game.board.block;

import com.suduko.game.response.BlockOperationResult;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBlock implements Block {

    //main coordinates range of the block
    private final int fromX;
    private final int toX;
    private final int fromY;
    private final int toY;
    private final int totalSize;

    //contains the values of numbers inside this block
    private final Set<Integer> values = new HashSet<>();

    public AbstractBlock(int fromX, int toX, int fromY, int toY, int totalSize) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.totalSize = totalSize;
    }


    /**
     * Tells if this block contains this index
     *
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     * @return true if the coordinates X&Y are contained inside this block
     */
    @Override
    public boolean containsCell(int x, int y) {

        boolean xInRange = (x >= fromX && x <= toX);
        boolean yInRange = (y >= fromY && y <= toY);

        return xInRange && yInRange;
    }


    /**
     * Validates adding a number (without adding)
     *
     * @param number number to validate
     * @return operation result of validation
     */
    @Override
    public BlockOperationResult validateAddNumber(int number) {

        if (number < 1 || number > totalSize) {
            return new BlockOperationResult(false, this, "Out of bounds number !");
        }

        return values.contains(number) ? new BlockOperationResult(false, this, "Number is already there") :
                new BlockOperationResult(true, this, "Number can be added");

    }

    /**
     * Adds a number to this block
     *
     * @param number number to add
     * @return operation result of adding
     */
    @Override
    public BlockOperationResult addNumber(int number) {
        //validate before add
        BlockOperationResult validation = validateAddNumber(number);
        if (validation.isOperationSuccess()) {
            values.add(number);
            return new BlockOperationResult(true, this, "Number is added");
        } else {
            return validation;
        }
    }


    /**
     * Removes a number from this block
     *
     * @param number number to remove
     */
    @Override
    public BlockOperationResult removeNumber(int number) {
        boolean numberRemoved = values.remove(number);

        return new BlockOperationResult(true, this, numberRemoved ? "Number is removed" : "Number was not found");
    }

    /**
     * Validates block numbers if they count to the total size
     *
     * @return true if the numbers are valid
     */
    @Override
    public BlockOperationResult validateBlock() {
        for (int num = 1; num <= 9; num++) {
            if (!values.contains(num)) {
                return new BlockOperationResult(false, this, "Number ".concat(String.valueOf(num)).concat(" is still missing !"));
            }
        }
        return new BlockOperationResult(true, this, "Block is valid !");
    }

    @Override
    public Set<Integer> getBlockValues() {
        return new HashSet<>(values);
    }

    @Override
    public int getFromX() {
        return fromX;
    }

    @Override
    public int getFromY() {
        return fromY;
    }

    @Override
    public int getToX() {
        return toX;
    }

    @Override
    public int getToY() {
        return toY;
    }
}
