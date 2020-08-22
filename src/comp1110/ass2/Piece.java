package comp1110.ass2;

/*
 * This class define a piece
 */

public class Piece {
    private final char pieceName = 0;
    public final boolean Spotsposition[][] = new boolean[4][4];
    private Direction orientation;


    public Piece(char pieceName, Direction orientation, boolean  Spotsposition[][]){

    }
    /**
     * Given the new direction of the piece,
     * update the orientation field of the piece.
     *
     * @param: direction that want the piece put into.
     * @return void.
     */
    public void ChangeDirection(char thisPieceDir){
    }
    public char getPieceName(){
        return pieceName;
    }
    public Direction getOrientation(){
        return orientation;
    }


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
    public boolean[][] GetCurrentPos(){
        return Spotsposition;
    }



    /**
     * Check if the piece has been used in another flip
     * e.g. For piece B, if b is already in the String, return
     * false
     * @param: the current String that describe piece on the board.
     * @return the answer.
     */

    public boolean IsPiecetaken(String CurrentOnboard){
        return true;
    }


}










