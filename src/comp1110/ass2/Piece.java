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
        map.put("bN",new int[][] {{1,1,1,1},{1,0,0,0}});
        map.put("bE",new int[][] {{1,1},{0,1},{0,1},{0,1}});
        map.put("bS",new int[][] {{0,0,0,1},{1,1,1,1}});
        map.put("bW",new int[][] {{1,0},{1,0},{1,0},{1,1}});
        map.put("BN",new int[][] {{1,1,1,1},{0,1,0,1}});
        map.put("BE",new int[][] {{0,1},{1,1},{0,1},{1,1}});
        map.put("BS",new int[][] {{1,0,1,0},{1,1,1,1}});
        map.put("BW",new int[][] {{1,1},{1,0},{1,1},{1,0}});
        map.put("gN",new int[][] {{1,1,1,0},{0,1,0,0}});
        map.put("gE",new int[][] {{0,1,},{1,1},{0,1}});
        map.put("gS",new int[][] {{0,1,0,0},{1,1,1,0}});
        map.put("gW",new int[][] {{1,0},{1,1},{1,0}});
        map.put("GN",new int[][] {{1,1,1},{1,1,0}});
        map.put("GE",new int[][] {{1,1},{1,1},{0,1}});
        map.put("GS",new int[][] {{0,1,1},{1,1,1}});
        map.put("GW",new int[][] {{1,0},{1,1},{1,1}});
        map.put("iN",new int[][] {{1,1,1},{0,0,1}});
        map.put("iE",new int[][] {{0,1},{0,1},{1,1}});
        map.put("iS",new int[][] {{1,0,0},{1,1,1}});
        map.put("iW",new int[][] {{1,1},{1,0},{1,0}});
        map.put("IN",new int[][] {{1,1,1},{0,1,1}});
        map.put("IE",new int[][] {{0,1},{1,1},{1,1}});
        map.put("IS",new int[][] {{1,1,0},{1,1,1}});
        map.put("IW",new int[][] {{1,1},{1,1},{1,0}});
        map.put("lN",new int[][] {{1,1,1},{1,0,0}});
        map.put("lE",new int[][] {{1,1},{0,1},{0,1}});
        map.put("lS",new int[][] {{0,0,1},{1,1,1}});
        map.put("lW",new int[][] {{1,0},{1,0},{1,1}});
        map.put("LN",new int[][] {{1,1,1},{1,0,1}});
        map.put("LE",new int[][] {{1,1},{0,1},{1,1}});
        map.put("LS",new int[][] {{1,0,1},{1,1,1}});
        map.put("LW",new int[][] {{1,1},{1,0},{1,1}});
        map.put("nN",new int[][] {{1,1,1},{0,1,0}});
        map.put("nE",new int[][] {{0,1},{1,1},{0,1}});
        map.put("nS",new int[][] {{0,1,0},{1,1,1}});
        map.put("nW",new int[][] {{1,0},{1,1},{1,0}});
        map.put("NN",new int[][] {{1,1,1},{1,0,1}});
        map.put("NE",new int[][] {{1,1},{0,1},{1,1}});
        map.put("NS",new int[][] {{1,0,1},{1,1,1}});
        map.put("NW",new int[][] {{1,1},{1,0},{1,1}});
        map.put("oN",new int[][] {{1,1,1,1},{0,1,0,0}});
        map.put("oE",new int[][] {{0,1},{1,1},{0,1},{0,1}});
        map.put("oS",new int[][] {{0,0,1,0},{1,1,1,1}});
        map.put("oW",new int[][] {{1,0},{1,0},{1,1},{1,0}});
        map.put("ON",new int[][] {{1,1,1,1},{1,0,1,0}});
        map.put("OE",new int[][] {{1,1},{0,1},{1,1},{0,1}});
        map.put("OS",new int[][] {{0,1,0,1},{1,1,1,1}});
        map.put("OW",new int[][] {{1,0},{1,1},{1,0},{1,1}});
        map.put("pN",new int[][] {{1,1,1,1},{0,0,1,0}});
        map.put("pE",new int[][] {{0,1},{0,1},{1,1},{0,1}});
        map.put("pS",new int[][] {{0,1,0,0},{1,1,1,1}});
        map.put("pW",new int[][] {{1,0},{1,1},{1,0},{1,0}});
        map.put("PN",new int[][] {{1,1,1,1},{1,1,0,0}});
        map.put("PE",new int[][] {{1,1},{1,1},{0,1},{0,1}});
        map.put("PS",new int[][] {{0,0,1,1},{1,1,1,1}});
        map.put("PW",new int[][] {{1,0},{1,0},{1,1},{1,1}});
        map.put("rN",new int[][] {{1,1,1,1},{1,0,0,0}});
        map.put("rE",new int[][] {{1,1},{0,1},{0,1},{0,1}});
        map.put("rS",new int[][] {{0,0,0,1},{1,1,1,1}});
        map.put("rW",new int[][] {{1,0},{1,0},{1,0},{1,1}});
        map.put("RN",new int[][] {{1,1,1,1},{1,0,0,1}});
        map.put("RE",new int[][] {{1,1},{0,1},{0,1},{1,1}});
        map.put("RS",new int[][] {{1,0,0,1},{1,1,1,1}});
        map.put("RW",new int[][] {{1,1},{1,0},{1,0},{1,1}});
        map.put("sN",new int[][] {{1,1,1,1},{0,1,0,0}});
        map.put("sE",new int[][] {{0,1},{1,1},{0,1},{0,1}});
        map.put("sS",new int[][] {{0,0,1,0},{1,1,1,1}});
        map.put("sW",new int[][] {{1,0},{1,0},{1,1},{1,0}});
        map.put("SN",new int[][] {{1,1,1,1},{0,1,1,0}});
        map.put("SE",new int[][] {{0,1},{1,1},{1,1},{0,1}});
        map.put("SS",new int[][] {{0,1,1,0},{1,1,1,1}});
        map.put("SW",new int[][] {{1,0},{1,1},{1,1},{1,0}});
        map.put("yN",new int[][] {{1,1,1,1},{0,0,0,1}});
        map.put("yE",new int[][] {{0,1},{0,1},{0,1},{1,1}});
        map.put("yS",new int[][] {{1,0,0,0},{1,1,1,1}});
        map.put("yW",new int[][] {{1,1},{1,0},{1,0},{1,0}});
        map.put("YN",new int[][] {{1,1,1,1},{0,0,1,1}});
        map.put("YE",new int[][] {{0,1},{0,1},{1,1},{1,1}});
        map.put("YS",new int[][] {{1,1,0,0},{1,1,1,1}});
        map.put("YW",new int[][] {{1,1},{1,1},{1,0},{1,0}});



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
}











