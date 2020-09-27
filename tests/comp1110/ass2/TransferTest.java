package comp1110.ass2;

import comp1110.ass2.gui.Board;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TransferTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);
    private void test(String Type, int rotate, double getX, double getY, String expected) {
        String out = Board.transfer(Type, rotate, getX, getY);
        assertNull("There is a PiecePlacement returned for the objective " + " Type = " + Type + ", rotate= " + rotate + " with position (" + getX + " " + getY + ")" , null);
        assertEquals("For objective " + " Type = " + Type + ", rotate= " + rotate + " with position (" + getX + " " + getY + ")" + ", was expecting " + expected + ", but got " + out, out, expected);
    }

    @Test
    public void OutOfBoundPieces() {

        for(int i =0; i< 100; i++){
            // test left bound
            double X1 = Math.random()*89;
            double Y1 = Math.random()*700;
            test("B1", 0, X1, Y1, null);

            // test top bound
            double X2 = Math.random()*933;
            double Y2 = Math.random()*164;
            test("B1", 0, X2, Y2, null);

            // test right bound
            double X3 = Math.random()*(933-690) + 690;
            double Y3 = Math.random()*700;
            test("B1", 0, X3, Y3, null);

            //  test bottom bound
            double X4 = Math.random()*933;
            double Y4 = Math.random()*(700-465) + 465;
            test("B1", 0, X4, Y4, null);
        }
    }

    @Test
    public void WrongRotatePieces() {
        // test valid rotate index
        test("B1", 0, 130, 220, "b01N");
        test("B2", 0, 135, 225, "B01N");
        test("G1", 1, 130, 220, "g01E");
        test("G2", 1, 135, 225, "G01E");
        test("N1", 2, 130, 220, "n01S");
        test("N2", 2, 135, 225, "N01S");
        test("R1", 3, 130, 220, "r01W");
        test("R2", 3, 135, 225, "R01W");

        // test invalid rotate index
        for(int i=0; i<100; i++) {
            int index1 = (int) (Math.random()*100 + 4);
            int index2 = - (int) (Math.random()*100);
            test("O1", index1, 130, 220, null);
            test("O2", index2, 130, 220, null);
        }
    }

    @Test
    public void WrongPositionPieces(){
        for(int i=0; i<100; i++) {
            double testX = Math.random()*(510)+90;
            double testY = Math.random()*(255)+165;
            int TrueX = (int) ((testX - 90)/51);
            int TrueY = (int) ((testY - 165)/51);
            String ans = "P" + TrueX + TrueY + "W";
            test("P2",3, testX, testY, ans);
        }
    }

    @Test
    public void WrongTypePieces(){
        String[] T = {"B","G","I","L","N","O","P","R","S","Y"};
        List<String> TBox = Arrays.asList(T);
        for(int i=0; i<100; i++) {
            char t = (char) i;
            if (!TBox.contains(String.valueOf(Character.toUpperCase(t)))){
                String type = String.valueOf(Character.toUpperCase(t)) + 1;
                test(type, 0, 130, 220, null);
            }
            else{
                String type = String.valueOf(Character.toUpperCase(t)) + 2;
                String ans = String.valueOf(Character.toUpperCase(t)) + 0 + 1 + "N";
                test(type, 0, 130, 220, ans);
            }
        }

    }
}
