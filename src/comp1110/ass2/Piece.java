package comp1110.ass2;

/*
 * This class define a piece
 */

import java.util.HashMap;
import java.util.Map;

public class Piece {
    private final int topLeftX;
    private final int topLeftY;
    private final String typeName;
    public Piece(String piecePlacement){
        this.topLeftX = Character.getNumericValue(piecePlacement.charAt(1));
        this.topLeftY = Character.getNumericValue(piecePlacement.charAt(2));
        this.typeName = piecePlacement.charAt(0) + String.valueOf(piecePlacement.charAt(3));
    }
    public int[][] toMatrix(){
        Map<String, int[][]> map = new HashMap<>();
        map.put("bN",new int[][] {{1,1,1,1},{1,0,0,0},{0,0,0,0},{0,0,0,0}});
        map.put("bE",new int[][] {{1,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}});
        for (Map.Entry<String, int[][]> entry : map.entrySet()){
            if(entry.getKey().equals(typeName)){
                return entry.getValue();
            }
        }
        return null;
    }
    public int getTopLeftX(){
        return topLeftX;
    }
    public int getTopLeftY(){
        return topLeftY;
    }

    public static void main(String[] args) {
        Piece A = new Piece("b23E");
        System.out.println(A.topLeftX);
        System.out.println(A.topLeftY);
        System.out.println(A.typeName);
        System.out.println(A.toMatrix()[0][1]);
    }
}











