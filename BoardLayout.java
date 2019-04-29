package MancalaProject;

public interface BoardLayout 
{
	// Methods shared by both layout1 and layout2
	public void setData(int[] data);
	public void drawBoard();
	public void repaintStones();
	public void whosTurn(int player);
	
}
