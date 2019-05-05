/**
 * Interface class works as layout management strategy
 * It provides shared functions for concrete strategy classes
 * 
 * @author Khadeja Khalid, Yu Xiu
 * @version 1.0 5/4/2019
 */

public interface BoardLayout 
{
    // Methods shared by both HorizontalLayout and VerticalLayout

    /**
     * Set the data
     * @param data : integer array of the data
     */
    public void setData(int[] data);

    /**
     * Draw the Mancala game board
     */
    public void drawBoard();

    /**
     * Repaint stones in pits
     */
    public void repaintStones();

    /**
     * Set's who's turn it currently is
     * @param player : A (1) or B (2)
     */
    public void whosTurn(int player);
    
}
