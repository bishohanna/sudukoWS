package com.suduko.game.response;

import com.suduko.game.board.SudukoBoard;

import java.io.Serializable;
import java.util.List;

public class BoardResult implements Serializable {


    //express the current board state
    private final SudukoBoard board;

    //express if the last operation succeeded
    private boolean operationSuccess;

    //express the message attached to final operation , failure message if the operation failed
    private String message;

    //express the operation results for each block - optional
    private List<BlockOperationResult> operationResults;

    //express if the board valid after the final move
    private boolean boardValid;

    public BoardResult(SudukoBoard board, boolean operationSuccess) {
        this(board, null, operationSuccess);
    }

    public BoardResult(SudukoBoard board, String message, boolean operationSuccess) {
        this.board = board;
        this.operationSuccess = operationSuccess;
        this.message = message;
    }

    public BoardResult setMessage(String message) {
        this.message = message;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public SudukoBoard getBoard() {
        return board;
    }

    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    public BoardResult setOperationResults(List<BlockOperationResult> operationResults) {
        this.operationResults = operationResults;

        return this;
    }

    public List<BlockOperationResult> getOperationResults() {
        return operationResults;
    }

    public boolean isBoardValid() {
        return boardValid;
    }

    public BoardResult setBoardValid(boolean boardValid) {
        this.boardValid = boardValid;

        return this;
    }

    public BoardResult setOperationSuccess(boolean operationSuccess) {
        this.operationSuccess = operationSuccess;

        return this;
    }
}
