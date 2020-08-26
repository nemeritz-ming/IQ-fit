package comp1110.ass2;

/*
 * This class define a piece
 */

public class Piece {
    private final char pieceName = 0;
    public final boolean spotsPosition[][] = new boolean[4][4];
    private Direction orientation = Direction.NORTH;//Default orientation is North
    private int LeftPos = -1, UpperPos = -1; //This field record theUpLeft position of the piece if it has been put on the board, otherwise it will be -1

    public Piece(char pieceName, boolean  spotsPosition[][]){

    }
    /**
     * Given the new direction and position of the piece,
     * update the orientation and LeftPos, UpperPos field of the piece.
     *
     * @param: the orientation and position that want the piece put into.
     * @return void.
     */
    public void changeDirAndPos(String pieceStatement){ }
    public char getPieceName(){
        return pieceName;
    }
    public Direction getOrientation(){
        return orientation;
    }


    /**
     * Calculate the Boolean table according to the orientation
     * and the spotsPosition array of the piece
     * The method includes rotation and translation of the
     * array’s valu
     *
     * @param: None.
     * @return A 4*4 boolean array that describe the piece
     * in the current orientation.
     */
    public boolean[][] getCurrentPos(){
        return spotsPosition;
    }



    /**
     * Check if the piece has been used in another flip
     * e.g. For piece B, if b is already in the String, return
     * false
     * @param: the current String that describe piece on the board.
     * @return the answer.
     */

    public boolean isPieceTaken(String currentOnBoard){
        return true;
    }


    /**
     * Transfer a piece's statement to String such as b73E
     *
     * @param: null.
     * @return the 4 char String that include position, name and orientation.
     */
    public String pieceToString(){return null;}


}










