/**
 * Creates a MancalaDataModel, Board (view/control), attaches the model to the board,
 * and calls the home page of the board, for the user to select their layout and start the game
 * 
 * @author Khadeja Khalid, Yu Xiu
 * @version 1.0 5/4/2019
 */

public class MancalaTest 
{
    public static void main(String[] args) 
    {
    	MancalaDataModel model = new MancalaDataModel();
    	Board board = new Board(model);
    	model.attach(board);
    	board.homepage();
    }
}
