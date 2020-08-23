package comp1110.ass2;

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
    public boolean Board[][] = new boolean[5][10];

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
    public String AddtoBoard(String currentString, Piece PieceName){
        return null;
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

    public String MovefromBoard(String currentString, Piece PieceName){
        return null;
    }

    /**
     * Read the current string and update the board
     * This method is prepared for initialize the game
     * @param: the current String that describe piece on the
     *          board
     * @return void
     */
    public void StringToBoard(String currentString, Piece PieceName){}






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
        return false; // FIXME Task 5: determine whether a placement string is valid
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

}
