package com.suduko.game.board;

import com.suduko.game.board.block.Block;
import com.suduko.game.response.BoardResult;

import java.util.List;

public interface SudukoBoard {


    BoardResult validateAddNumber(int number, int x, int y);

    BoardResult addNumber(int number, int x, int y);

    BoardResult removeNumber(int number, int x, int y);

    BoardResult validateBoard();

    int[][] getBoardValues();

    List<Block> getBoardBlocks();
}
