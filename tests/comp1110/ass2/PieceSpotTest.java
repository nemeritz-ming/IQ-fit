package comp1110.ass2;



import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import comp1110.ass2.FitGame;
import static org.junit.Assert.assertTrue;


public class PieceSpotTest {




    @Rule
    public Timeout globalTimeout = Timeout.millis(500);
    private void test(String piecePlacement, int x, int y, String expected) {
        String out = FitGame.findPieceSpotOnBoard(piecePlacement,x,y);
    }

    @Test
    public void topLeftSpotEmpty(){
        test("G52Nl21S",5,2,null);
        test("b63Sn03Sy31S",0,3,null);
        test("b63Sn03Sy31S",6,3,null);

    }
    @Test
    public void topLeftSpotFill(){
        test("G52Nl21S",2,1,"l21S");
        test("b63Sn03Sy31S",3,1,"y31S");
    }

}
