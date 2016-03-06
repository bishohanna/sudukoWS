package com.suduko.rest;


import com.suduko.game.board.SudukoBoard;
import com.suduko.game.board.SudukoBoardBuilder;
import com.suduko.game.response.BoardResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SudukoRestController {

    private final String GAME_SESSION_KEY = "sudukoGame";

    //initial game values
    private final int[][] sudukoUnCompleteBoard = new int[][]
            {
                    {5, 3, 4, 6, 7, 8, 9, 1, 2},
                    {6, 7, 2, 1, 9, 5, 3, 4, 8},
                    {1, 9, 8, 3, 4, 2, 5, 6, 0},//7 remaining
                    {8, 5, 9, 7, 6, 1, 4, 2, 3},
                    {4, 2, 6, 8, 5, 3, 7, 9, 1},
                    {7, 1, 3, 9, 2, 0, 8, 5, 6},//4 remaining
                    {9, 6, 1, 5, 3, 7, 2, 8, 4},
                    {2, 8, 7, 4, 1, 9, 6, 3, 5},
                    {3, 4, 5, 2, 8, 6, 1, 7, 9},

            };


    /**
     * Creates a game if no game attached to an open session is found, or returns the game attached to an open session
     *
     * @return Suduko board result (Suduko Game)
     */
    @RequestMapping("/suduko/create")
    public BoardResult startGame(HttpServletRequest req) {
        return getOrCreateSudukoGame(req);
    }


    /**
     * Adds a number to specific X Y cell
     *
     * @param number number to add
     * @param xValue X value
     * @param yValue Y value
     * @return Board result expressing the operation  results and the current game state
     */
    @RequestMapping("/suduko/addNumber")
    public BoardResult addNumber(@RequestParam("number") Integer number,
                                 @RequestParam("x") Integer xValue,
                                 @RequestParam("y") Integer yValue,
                                 HttpServletRequest request) {

        BoardResult currentState = getOrCreateSudukoGame(request);
        if (number == null || xValue == null || yValue == null) {
            return currentState.setOperationSuccess(false).setMessage("Please pass valid parameters !");
        }

        //get board
        SudukoBoard board = currentState.getBoard();

        //add number
        BoardResult addResult = board.addNumber(number, xValue, yValue);

        return addResult;
    }


    /**
     * Checks if a number can be added to a specific cell , without adding a number
     *
     * @param number number to add
     * @param xValue X value
     * @param yValue Y value
     * @return Board result expressing the operation  results and the current game state
     */
    @RequestMapping("/suduko/validateAddNumber")
    public BoardResult validateAddNumber(@RequestParam("number") Integer number,
                                         @RequestParam("x") Integer xValue,
                                         @RequestParam("y") Integer yValue,
                                         HttpServletRequest request) {

        BoardResult currentState = getOrCreateSudukoGame(request);
        if (number == null || xValue == null || yValue == null) {
            return currentState.setOperationSuccess(false).setMessage("Please pass valid parameters !");
        }

        //get board
        SudukoBoard board = currentState.getBoard();

        //validate add number
        BoardResult validateResult = board.validateAddNumber(number, xValue, yValue);

        return validateResult;
    }

    /**
     * Removes a number from specific X Y cell
     *
     * @param number number to remove
     * @param xValue X
     * @param yValue Y
     * @return Board result expressing the operation results and the current game state
     */
    @RequestMapping("/suduko/removeNumber")
    public BoardResult removeNumber(@RequestParam("number") Integer number,
                                    @RequestParam("x") Integer xValue,
                                    @RequestParam("y") Integer yValue,
                                    HttpServletRequest request) {

        BoardResult currentState = getOrCreateSudukoGame(request);
        if (number == null || xValue == null || yValue == null) {
            return currentState.setOperationSuccess(false).setMessage("Please pass valid parameters !");
        }

        //get board
        SudukoBoard board = currentState.getBoard();

        //remove number
        BoardResult removeResult = board.removeNumber(number, xValue, yValue);

        return removeResult;
    }


    /**
     * Validates the current state of the board and returns the current state along with the invalid blocks
     *
     * @return Board result expressing the operation  results and the current game state
     */
    @RequestMapping("/suduko/validateBoard")
    public BoardResult validateBoard(HttpServletRequest request) {

        BoardResult currentState = getOrCreateSudukoGame(request);

        //get board
        SudukoBoard board = currentState.getBoard();

        //validate board
        BoardResult validateResult = board.validateBoard();

        return validateResult;
    }

    /**
     * Creates a game if no game attached to an open session is found, or returns the game attached to an open session
     *
     * @param req http request
     * @return Suduko board result (Suduko Game)
     */
    private BoardResult getOrCreateSudukoGame(HttpServletRequest req) {
        BoardResult boardResult = null;

        if (req.getSession().getAttribute(GAME_SESSION_KEY) != null) {
            boardResult = (BoardResult) req.getSession().getAttribute(GAME_SESSION_KEY);
        } else {
            boardResult = new SudukoBoardBuilder(sudukoUnCompleteBoard).buildBoard();
            req.getSession().setAttribute(GAME_SESSION_KEY, boardResult);
        }

        return boardResult;
    }
}
