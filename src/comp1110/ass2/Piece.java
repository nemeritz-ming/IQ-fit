package comp1110.ass2;

/*
 * This class define a piece
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Piece {
    private final String piecetype;
    private final int Topleftx;
    private final int Toplefty;
    public Piece(String PiecePlacement){
        this.piecetype = String.valueOf(PiecePlacement.charAt(0)) + String.valueOf(PiecePlacement.charAt(3));
        this.Topleftx = Character.getNumericValue(PiecePlacement.charAt(1));
        this.Toplefty = Character.getNumericValue(PiecePlacement.charAt(2));
    }
    public int[][] Tomatirx(){
        HashMap<String, int[][]> map = new HashMap<>();
        map.put("bN",new int[][]{{1,1,1,1},{1,0,0,0},{0,0,0,0},{0,0,0,0}});
        map.put("gN",new int[][]{{1,1,1,0},{0,1,0,0},{0,0,0,0},{0,0,0,0}});
        for (Map.Entry<String, int[][]> entry : map.entrySet()){
            if (entry.getKey().equals(piecetype)){
                return entry.getValue();
            }
        }
        return null;
    }
    public int getTopleftx(){
        return Topleftx;
    }
    public int getToplefty(){
        return Toplefty;
    }

    public static void main(String[] args) {
        Piece A =new Piece("g09N");
        System.out.println(A.Tomatirx()[0][1]);
        System.out.println(A.Topleftx);
        System.out.println(A.Toplefty);
    }


    /**
     * Given the new direction and position of the piece,
     * update the orientation and LeftPos, UpperPos field of the piece.
     *
     * @param: the orientation and position that want the piece put into.
     * @return void.
     */



//    public void ChangeDirandPos(String PieceStatement){ }
//    public char getPieceName(){
//        return pieceName;
//    }
//    public Direction getOrientation(){
//        return orientation;
//    }


    /**
     * Calculate the Boolean table according to the orientation
     * and the Spotsposition array of the piece
     * The method includes rotation and translation of the
     * array’s valu
     *
     * @param: None.
     * @return A 4*4 boolean array that describe the piece
     * in the current orientation.
     */
//    public boolean[][] GetCurrentPos(){
//        return Spotsposition;
//    }



    /**
     * Check if the piece has been used in another flip
     * e.g. For piece B, if b is already in the String, return
     * false
     * @param: the current String that describe piece on the board.
     * @return the answer.
     */

//    public boolean IsPiecetaken(String CurrentOnboard){
//        return true;
//    }


    /**
     * Transfer a piece's statment to String such as b73E
     *
     * @param: null.
     * @return the 4 char String that include position, name and orientation.
     */
//    public String PiecetoString(){return null;}


}










