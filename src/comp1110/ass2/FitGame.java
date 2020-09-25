package comp1110.ass2;

import java.util.*;


/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */

public class FitGame {
    public static int[][] Board = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    public static String solution = "";
    public static int tlx = -1;
    public static int tly = -1;
    public static void initial(){
        Board =new int[][] {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    }
    /**
     * Create a new Piece according to the string that describe the piece
     *
     * @param: the String that describe piece
     *
     * @return The piece in type Piece
     */

    public static boolean checkCompletion(){
        for (int i =0;i<5;i++){
            for(int j=0;j<10;j++){
                if (Board[i][j]!=1){
                    return false;
                }
            }
        }
        return true;
    }
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
    public static void addToBoard(String thisPiece){
        Piece temp = createNewPiece(thisPiece);
        int[][] tempMat = temp.toMatrix();
        int tempX = temp.getTopLeftX();
        int tempY = temp.getTopLeftY();
        int rows = tempMat.length;
        int columns = tempMat[0].length;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (tempMat[j][i] == 1)
                    Board[j + tempY][i + tempX] = 1;
            }
        }
    }
    public static void deleteFromBoard(String thisPiece){
        Piece temp = createNewPiece(thisPiece);
        int[][] tempMat = temp.toMatrix();

        int tempX = temp.getTopLeftX();
        int tempY = temp.getTopLeftY();
        int rows = tempMat.length;
        int columns = tempMat[0].length;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (tempMat[j][i] == 1)
                    Board[j + tempY][i + tempX] = 0;
            }
        }
    }

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
        int tempX = temp.getTopLeftX();
        int tempY = temp.getTopLeftY();
        int rows = tempMat.length;
        int columns = tempMat[0].length;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                if (tempMat[j][i] == 1){
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
     * check if the piece (or its brother) has been used
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if all pieces are not used.
     */
    public static boolean havePiecesBeenUsed(String placement){
        if(!isPlacementWellFormed(placement))
            return false;
        char[] T = {'B','R','G','I','L','N','O','P','S','Y'};
        boolean[] check = new boolean[10];
        String temp = placement.toUpperCase();
        for (int i = 0; i < placement.length(); i += 4){
            for (int j = 0; j < 10; j++){
                if(T[j] == temp.charAt(i)){
                    if (check[j])
                        return false;
                    else
                        check[j] = true;
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
        if (placement.length() % 4 != 0|| placement.equals("")){
            return false;
        }
        int N = placement.length() / 4;
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
            if (!isPiecePlacementWellFormed(placement.substring(i*4, i*4 +4)))
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
        if (!isPlacementWellFormed(placement))
            return false;
        initial();
        int[][] tempBoard = Board;
        for (int n = 0; n < placement.length()/4; n++){
            String stringForThisPiece = placement.substring(n * 4, n * 4 + 4);
            Piece temp = createNewPiece(stringForThisPiece);
            int[][] tempMat = temp.toMatrix();
            int tempX = temp.getTopLeftX();
            int tempY = temp.getTopLeftY();
            int rows = tempMat.length;
            int columns = tempMat[0].length;
            for (int i = 0; i < columns; i++){
                for (int j = 0; j < rows; j++){
                    if (tempMat[j][i] == 1){
                        if (tempX + i > 9 || tempY + j > 4)
                            return false;
                        else{
                            if (tempBoard[j + tempY][i + tempX] == 1)
                                return false;
                        }
                        tempBoard[j + tempY][i + tempX] = 1;
                    }
                }
            }
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
        if (!placement.equals("")){if(! isPlacementValid(placement)){return null;}}
        initial();
        for (int i = 0; i < placement.length()/4; i++){
            String stringForThisPiece = placement.substring(i * 4, i * 4 + 4);
            if (canPieceBePlaced(stringForThisPiece)){
                addToBoard(stringForThisPiece);
            }
        }
        if (Board[row][col]==1){return null;}
        Set<String> box =new HashSet<>();
        String[] direct = {"N","E","S","W"};
        String[] T = {"B","R","G","I","L","N","O","P","S","Y","b","r","g","i","l","n","o","p","s","y"};
        List<String> A = Arrays.asList(T);
        ArrayList<String> allType = new ArrayList<String>(A);
        for (int i = 0; i < placement.length()/4; i++){
            String low = Character.toString(Character.toLowerCase(placement.charAt(i * 4)));
            String up =  Character.toString(Character.toUpperCase(placement.charAt(i * 4)));
            if (allType.contains(low)||allType.contains(up)){
                allType.remove(low);
                allType.remove(up);
            }
        }
        ArrayList<String> position = new ArrayList<>();
        for (int i=Math.max(0,col-3); i<=col;i++){
            for (int j=Math.max(0,row-3); j<=row;j++){
                position.add(Integer.toString(i) +Integer.toString(j));
            }
        }
        for(String i: direct){
            for(String j: allType){
                for (String posString: position){
                    if (canPieceBePlaced(j+posString+i)){
                        addToBoard(j+posString+i);
                        if(Board[row][col]==1){
                            box.add(j+posString+i);
                        }
                        deleteFromBoard(j+posString+i);
                    }
                }
            }
        }
        if (box.size() !=0){return box;}
        else{return null;}
    }// FIXME Task 6: determine the set of all viable piece placements given existing placements

    /**
     * Return the solution to a particular challenge.
     **
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        solution = "";
        dfs(challenge);
        return solution;
        // FIXME Task 9: determine the solution to the game, given a particular challenge
    }
    //    DFS searching
    public static void dfs(String challenge){
        if(challenge !=null){
            if (challenge.length()==40){
                if(checkCompletion()){
                    solution = challenge ;
                    return;
                }
            }
            Board = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
            for (int i = 0; i < challenge.length()/4; i++){
                String stringForThisPiece = challenge.substring(i * 4, i * 4 + 4);
                if (canPieceBePlaced(stringForThisPiece)){
                    addToBoard(stringForThisPiece);
                }
            }
            if (helper()) {
                Set<String> box;
                if (getNextPos() != null) {
                    tlx = getNextPos()[0];
                    tly = getNextPos()[1];
                    box = getViablePiecePlacements(challenge, tly, tlx);
                    if (box != null) {
                        for (String k : box) {
                            addToBoard(k);
                            challenge = sortAdd(challenge, k);
                            dfs(challenge);
                            if (!solution.equals("")){return;}
                            challenge = sortDelete(challenge, k);
                            deleteFromBoard(k);
                        }
                    }
                }
            }
        }
    }

    //     find the next position to place pieces
    public static int[] getNextPos(){
        for (int i=0; i<10;i++){
            for (int j=0;j<5;j++){
                if(Board[j][i]==0){
                    return new int[]{j,i};
                }
            }
        }
        return null;
    }
    //    add piece string to the board string
    public static String sortAdd(String a, String Piece){
        String res = "";
        ArrayList<String> B = new ArrayList<>();
        for (int i = 0; i < a.length()/4; i++){
            B.add(a.substring(i * 4, i * 4 + 4));
        }
        B.add(Piece);
        Collections.sort(B,String.CASE_INSENSITIVE_ORDER);
        for (String k: B){
            res += k;
        }
        return res;
    }
    //    delete piece string from the board string
    public static String sortDelete(String a, String Piece){
        StringBuilder A = new StringBuilder(a);
        for (int i = 0; i < a.length()/4; i++){
            if (a.substring(i * 4, i * 4 + 4).equals(Piece)){
                return a.substring(0,4*i)+a.substring(4*i+4,a.length());
            }
        }
        return null;
    }
    //    stop searching if impossible cases happen (i.e. a blank is surrounded by pieces)
    public static boolean helper(){
//        check corner
        if(Board[0][0]==0 && Board[0][1] == 1 && Board[1][0]==1){return false;}
        if(Board[4][0]==0 && Board[3][0] == 1 && Board[4][1]==1){return false;}
        if(Board[0][9]==0 && Board[1][9] == 1 && Board[0][8]==1){return false;}
        if(Board[4][9]==0 && Board[3][9] == 1 && Board[4][8]==1){return false;}


//        check center with 1 blank
        for(int i=1;i<4;i++){
            for(int j=1;j<9;j++){
                if(Board[i][j]==0 && Board[i][j+1] == 1 && Board[i+1][j]==1 && Board[i-1][j] == 1 && Board[i][j-1]==1){
                    return false;
                }
            }
        }

//        check edge
        for (int i=1;i<4;i++){
            if (Board[i][0]==0 && Board[i-1][0]==1 && Board[i+1][0]==1 && Board[i][1]==1){return false;}
            if (Board[i][9]==0 && Board[i-1][9]==1 && Board[i+1][9]==1 && Board[i][8]==1){return false;}
        }
        for (int j=1;j<9;j++){
            if (Board[0][j]==0 && Board[0][j+1]==1 && Board[0][j-1]==1 && Board[1][j]==1){return false;}
            if (Board[4][j]==0 && Board[4][j+1]==1 && Board[4][j-1]==1 && Board[3][j]==1){return false;}
        }
        return true;
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

    /**
     * This method compares the current string of all the pieces on the board with the final answer.
     * and it will return a string that represent all the pieces that haven't been correctly put.
     * The method is meanly used to show the Hint window while playing
     **
     * @param currentString The current pieces statement on the board.
     * @param gameNum The iNum of the current game.
     *
     * @return A string that represents all the pieces that haven't been correctly placed.
     *         eg: String "BNiEoW" means that B/i/o haven't been well placed,
     *         and they should in direction North/East/West.
     */
    public static String findWrongPieces(String currentString, int gameNum) {
        String pieceList = "";
        String ans = Games.SOLUTIONS[gameNum].placement;
        System.out.println(ans);
        int piecesNumber = currentString.length() / 4;
        int checkPoint = 0;
        for (int i = 0; i < 10; i++){
            String correctPiece = ans.substring(i*4, i*4 + 4);
            String realPiece = currentString.substring(checkPoint*4, checkPoint*4 + 4);
            if (correctPiece.toUpperCase().charAt(0) == realPiece.toUpperCase().charAt(0)){
                checkPoint += 1;
                if (!correctPiece.equals(realPiece)){
                    pieceList = pieceList + correctPiece.charAt(0) + correctPiece.charAt(3);
                }
            }
            else
                pieceList = pieceList + correctPiece.charAt(0) + correctPiece.charAt(3);
            if (checkPoint == piecesNumber){
                for (int j = i + 1; j < 10; j++){
                    correctPiece = ans.substring(j*4, j*4 + 4);
                    pieceList = pieceList + correctPiece.charAt(0) + correctPiece.charAt(3);
                }
                break;
            }
        }
        return pieceList;
    }

    public static String findPieceSpotOnBoard(String piecePlacement, int x, int y) {
        Piece temp = createNewPiece(piecePlacement);
        int[][] tempMat = temp.toMatrix();
        int tempX = temp.getTopLeftX();
        int tempY = temp.getTopLeftY();
        int rows = tempMat.length;
        int columns = tempMat[0].length;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (j + tempY== x && i+tempX == y) {
                    return piecePlacement;
                }
            }

        }
        return null;

    }

}
