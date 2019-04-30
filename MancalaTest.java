package MancalaProject;

public class MancalaTest 
{
    public static void main(String[] args) 
    {
//    	int[] dummyData = new int[14];
//    	
//    	for(int i=0; i<14; i++)
//    	{
//    		dummyData[i] = 3;
//    	}
//    	
//    	dummyData[4] = 7;
//    	dummyData[6] = 5;
//    	dummyData[10] = 13;
//    	dummyData[13] = 20;
//    	
//    	BoardLayout test = new HorizontalLayout();
//    	test.setData(dummyData);
//    	test.drawBoard();
//       
//    	
//      BoardLayout test2 = new VerticalLayout();
//      test2.setData(dummyData);
//      test2.drawBoard();

    	
    	MancalaDataModel model = new MancalaDataModel();
    	Board board = new Board(model);
    	model.attach(board);
    	board.homepage();
    	//board.playGame();
    	
    	//model.dummy();
    	//model.dummyPrintData();

    }
}