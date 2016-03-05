package com.suduko.game.response;

import com.suduko.game.board.block.Block;

import java.io.Serializable;

/**
 * Represents a single response from block operation
 */
public class BlockOperationResult implements Serializable {


    private final boolean operationSuccess;
    private final String message;
    private final Block block;


    public BlockOperationResult(boolean operationSuccess, Block block, String message) {
        this.operationSuccess = operationSuccess;
        this.message = message;
        this.block = block;
    }

    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    public String getMessage() {
        return message;
    }

    public Block getBlock() {
        return block;
    }
}
