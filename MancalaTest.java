import javax.swing.*;
import java.awt.*;

public class MancalaTest {
    public static void main(String[] args) {
        int[] dummyData = new int[14];

        for(int i=0; i<14; i++)
        {
            dummyData[i] = 2;
        }

        dummyData[4] = 7;
        dummyData[6] = 5;
        dummyData[10] = 13;
        dummyData[13] = 10;

        //BoardLayout test = new HorizontalLayout();
        //test.setData(dummyData);
        //test.drawBoard();


        BoardLayout test2 = new VerticalLayout();
        test2.setData(dummyData);
        test2.drawBoard();

    }
}
