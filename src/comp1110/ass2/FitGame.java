package comp1110.ass2;

import comp1110.ass2.gui.Board;

import javax.management.openmbean.TabularDataSupport;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;


/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */
public class FitGame {
    public static int[][] Board = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};

    /**
     * Create a new Piece according to the string that describe the piece
     *
     * @param: the String that describe piece
     *
     * @return The piece in type Piece
     */
    public static Piece createNewPiece(String thisPiece){
        if (isPiecePlacementWellFormed(thisPiece))
            return new Piece(thisPiece);
        else
            return null;
    }

    /**
     * Add a new piece on the board, this will include checking
     * if the placement viable. If so, the method will update
     * the board[][] as well as update the string
     *
     * @param: the current String that describe piece on the
     *          board, the piece that want to add
     * @return The new string, it will not change if the placement
     *           is not viable.
     */
    public static String addToBoard(String currentString, String thisPiece){
        Piece temp = createNewPiece(thisPiece);
        int[][] tempMat = temp.toMatrix();
        //int[][] tempMat =  {{1,1,1,1},{1,0,0,0},{0,0,0,0},{0,0,0,0}};
        int tempX = temp.getTopLeftX();//thisPiece.charAt(1) - 48;
        int tempY = temp.getTopLeftY();//thisPiece.charAt(2) - 48;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (tempMat[j][i] == 1)
                    Board[j + tempY][i + tempX] = 1;
            }
        }
        return currentString + thisPiece;
    }

    /**
     * Move a piece from the board, this will include surch
     * the piece from String. If find the piece, the method will update
     * the board[][] as well as update the string
     *
     * @param: the current String that describe piece on the
     *          board, the piece that want to move away
     * @return The new string, it will not change if the placement
     *           is not viable.
     */

    public String moveFromBoard(String currentString, String thisPiece){
        return null;
    }

    /**
     * Read the current string and update the board
     * This method is prepared for initialize the game
     * @param: the current String that describe piece on the
     *          board
     * @return void
     */
    public void StringToBoard(String currentString){}

    /**
     * Check if this piece is conflict with other piece on board
     *
     * @param: the String that describe the piece
     *
     * @return The piece in type Piece
     */
    public static boolean canPieceBePlaced(String thisPiece){
        if (!isPiecePlacementWellFormed(thisPiece))
            return false;
        Piece temp = createNewPiece(thisPiece);
        int[][] tempMat = temp.toMatrix();
        //int[][] tempMat =  {{1,1,1,1},{1,0,0,0},{0,0,0,0},{0,0,0,0}};
        int tempX = temp.getTopLeftX();//thisPiece.charAt(1) - 48;
        int tempY = temp.getTopLeftY();//thisPiece.charAt(2) - 48;
        //System.out.println("xy is :" +(tempX) +" "+ (tempY));
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (tempMat[j][i] == 1){
                    //System.out.println((tempX + i) +" "+ (tempY + j));
                    if (tempX + i > 9 || tempY + j > 4)
                        return false;
                    else{
                        if (Board[j + tempY][i + tempX] == 1)
                            return false;
                    }
                }
            }
        }
        return true;
    }




    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is a valid piece descriptor character (b, B, g, G, ... y, Y)
     * - the second character is in the range 0 .. 9 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in valid orientation N, S, E, W
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */

    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        if (piecePlacement.length() != 4)
            return false;
        char C1 = piecePlacement.toUpperCase().charAt(0), C2 = piecePlacement.charAt(1), C3 = piecePlacement.charAt(2), C4 = piecePlacement.charAt(3);
        if (C1!='B' && C1!='R' && C1!='G' && C1!='I' && C1!='L' && C1!='N' && C1!='O' && C1!='P' && C1!='S' && C1!='Y')
            return false;
        if (C2<48 || C2>57)
            return false;
        if (C3!='0' && C3!='1' && C3!='2' && C3!='3' && C3!='4')
            return false;
        if (C4!='N' && C4!='S' && C4!='E' && C4!='W')
            return false;
        return true; // FIXME Task 2: determine whether a piece placement is well-formed
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     * - the pieces are ordered correctly within the string
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        if (placement.length()%4 != 0 || placement.length()==0){
            return false;
        }
        int N = placement.length() / 4;
        String temp;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (placement.charAt(i*4) == placement.charAt(j*4))
                    return false;
                if ((int)placement.charAt(i*4) >= 91 && (int)placement.charAt(j*4) <= 'Z'){
                    if (placement.charAt(i*4)-32 > placement.charAt(j*4))
                        return false;
                }
                else if (placement.charAt(i*4) <= 'Z' && placement.charAt(j*4) >= 'a'){
                    if (placement.charAt(i*4)>placement.charAt(j*4)-32)
                        return false;
                }
                else {
                    if (placement.charAt(i*4)>placement.charAt(j*4))
                        return false;
                }
            }
            temp = placement.substring(i*4, i*4 +4);
            if (!isPiecePlacementWellFormed(temp))
                return false;
        }
        return true; // FIXME Task 3: determine whether a placement is well-formed
    }

    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementValid(String placement) {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<5; j++)
                Board[j][i] = 0;

        }
        if (!isPlacementWellFormed(placement))
            return false;
        String tempString="";
        for (int i = 0; i < placement.length()/4; i++){
            String stringForThisPiece = placement.substring(i * 4, i * 4 + 4);
            if (canPieceBePlaced(stringForThisPiece)){
                addToBoard(tempString, stringForThisPiece);
            }
            else
                return false;
        }
        return true; // FIXME Task 5: determine whether a placement string is valid
    }

    /**
     * Given a string describing a placement of pieces, and a location
     * that must be covered by the next move, return a set of all
     * possible next viable piece placements which cover the location.
     *
     * For a piece placement to be viable it must:
     *  - be a well formed piece placement
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the location
     *
     * @param placement A starting placement string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {
        return null; // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }

    /**
     * Return the solution to a particular challenge.
     **
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        return null;  // FIXME Task 9: determine the solution to the game, given a particular challenge
    }
    /**
     * Solution design
     *
     * Given the current placement
     * getTopLeftCorner method is to find the the most top left position which is not placed on any pieces.
     *
     * @param placement A starting placement string
     * @return A length 2 integer array represents the non-zero top left position
     * return the position if the Top left position exists and it is not placed on any piece
     * or return null if there is no such a position
     */
    public static int[] getTopLeftcorner(String placement){
        return null;
    }
    /** we are going to use DFS to solve this puzzle
     * String DFS(String challenge){}
     * Given a string challenge, we first convert it into a board matrix (5*10)
     * Second, check all pieces on the board and determine which pieces are not on the board
     * create a hashset to store the pieces that we are going to select
     * find the left top position that is not placed on any pieces on the current board matrix
     * if there is no such a position exists, it  means every entry of the board matrix is full
     * then return current string placement
     * if return a top left position, we use getViablePiecePlacements() to find possible pieces set
     * then we use a for loop to select appropriate piece in the set that can be put on the board according to the piece type order
     * if this piece is not in the hashset
     * add the selected piece to the board, also add it to the hashset
     * update the board matrix and corresponding string newplacement
     * recursively to find the new piece: DFS(Sting newplacement)
     * if we cannot find the piece we need, we should traceback to the last top left position and continue the for loop to select new piece
     * delete the previously selected piece from the board
     * update the board matrix
     * update new string placement
     * also delete it from the hashset
     * end the for loop
     */
}
