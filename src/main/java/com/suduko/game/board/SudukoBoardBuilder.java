package com.suduko.game.board;

import com.suduko.game.board.builder.BoardValuesBuilder;
import com.suduko.game.board.builder.LineBlocksBuilder;
import com.suduko.game.board.builder.SquareBlockBuilder;
import com.suduko.game.board.builder.SudukoBuilderNode;
import com.suduko.game.response.BoardResult;
import org.springframework.stereotype.Component;

@Component
public class SudukoBoardBuilder {

    private SudukoBuilderNode initialNode;

    public SudukoBoardBuilder() {

        //initializes builder chain
        initializeBuilder();
    }


    public BoardResult buildBoard(int[][] boardValues) {


        //1- validate board values
        if (!validateBoardValues(boardValues)) {
            return new BoardResult(null, "Board values are not valid", false);
        }

        //2- start building chain
        if (initialNode != null) {
            SudukoBoardImpl board = new SudukoBoardImpl(boardValues.length);

            //start chain
            initialNode.handle(board, boardValues);
            return new BoardResult(board, "Board initialized", true);
        } else {
            return new BoardResult(null, "No proper board builders found", false);
        }

    }

    /**
     * Validates board values size
     *
     * @return true if values size are valid
     */
    private boolean validateBoardValues(int[][] boardValues) {
        if (boardValues == null) {
            return false;
        }

        //validate if perfect square
        int length = boardValues.length;
        Double sqrt = Math.sqrt(Double.parseDouble(String.valueOf(length)));
        int sqrtIntValue = sqrt.intValue();
        boolean isPerfectSquare = (sqrtIntValue * sqrtIntValue) == length;
        if (!isPerfectSquare) {
            return false;
        }

        //validate that rows are equal in size and consistent
        for (int row = 0; row < length; row++) {
            int rowSize = boardValues[row].length;

            if (rowSize != length)
                return false;
        }

        return true;
    }

    /**
     * Initializes builder chain operations
     */
    private void initializeBuilder() {

        initialNode = new LineBlocksBuilder();

        //init chain
        initialNode
                .setNextNode(new SquareBlockBuilder())
                .setNextNode(new BoardValuesBuilder());

    }
}
