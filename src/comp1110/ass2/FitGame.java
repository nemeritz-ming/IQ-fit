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
        int rows = tempMat.length;//行数
        int columns = tempMat[0].length;//列数
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

    public static void main(String[] args) {
        Board = new int[][] {{0,0,0,1,1,1,1,0,1,0},{0,0,1,0,0,1,0,0,1,0},{0,1,1,0,1,0,0,1,1,0},{1,1,0,0,1,0,1,0,1,0},{0,1,0,0,0,0,0,0,0,1},{0,0,1,1,0,1,1,0,0,0},{0,0,1,1,0,0,0,0,0,0},{0,0,1,1,0,0,1,1,0,0},{0,0,0,0,0,0,1,1,1,0},{0,0,0,0,0,1,0,1,0,0}};
        for (int l = 0 ; l<5; l++)
                System.out.println(Board[l][0]+" "+Board[l][1]+" "+Board[l][2]+" "+Board[l][3]+" "+Board[l][4]+" "+Board[l][5]+" "+Board[l][6]+" "+Board[l][7]+" "+Board[l][8]+" "+Board[l][9]);
        System.out.println(Arrays.toString(getNextPos()));
//        String a = "B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N";
//        System.out.println(a.length()/4);
//        for (int i = 0; i < a.length()/4; i++){
//            String stringForThisPiece = a.substring(i * 4, i * 4 + 4);
//            if (canPieceBePlaced(stringForThisPiece)){
//                addToBoard(stringForThisPiece);
//            }
//            else
//                System.out.println("No");
//            for (int l = 0 ; l<5; l++)
//                System.out.println(Board[l][0]+" "+Board[l][1]+" "+Board[l][2]+" "+Board[l][3]+" "+Board[l][4]+" "+Board[l][5]+" "+Board[l][6]+" "+Board[l][7]+" "+Board[l][8]+" "+Board[l][9]);
//        }
//        System.out.println("yes");
//        String b = "n11Sp13SS53S,B20EG00Ni40Sl82Wn02No80Ep50NR42NS43Sy03S";
//        System.out.println(sortAdd(b,"p20E"));
//        System.out.println(getViablePiecePlacements(b,8,3));
//        for (int l = 0 ; l<5; l++)
//            System.out.println(Board[l][0]+" "+Board[l][1]+" "+Board[l][2]+" "+Board[l][3]+" "+Board[l][4]+" "+Board[l][5]+" "+Board[l][6]+" "+Board[l][7]+" "+Board[l][8]+" "+Board[l][9]);
//        System.out.println(canPieceBePlaced("g60N"));
//        System.out.println(isPlacementWellFormed("B00WG70Ni43Sl73Sn03So10Ep31WR61SS30Ny52S，b81EI21Es53S"));
        System.out.println(getSolution("b33Sp30S"));
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
            int rows = tempMat.length;//行数
            int columns = tempMat[0].length;//列数
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
        else{return null;} // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }

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

    public static void dfs(String challenge){
        System.out.println(challenge);
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

    public static int[] getNextPos(){
//        int ans = -1;
//        int[][] res = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
//        for (int i=1; i<9;i++){
//            for (int j=1; j<4;j++){
//                if (Board[j][i]==0){
//                    if(Board[j][i+1]==1){res[j][i]+=1;}
//                    else if(Board[j][i-1]==1){res[j][i]+=1;}
//                    else if(Board[j+1][i]==1){res[j][i]+=1;}
//                    else if(Board[j-1][i]==1){res[j][i]+=1;}
//                }
//            }
//        }
//        for (int i=1; i<9;i++){
//            if (Board[0][i]==0){if (Board[0][i+1]==1){res[0][i]+=1;} else if(Board[0][i-1]==1){res[0][i]+=1;}}
//            if (Board[4][i]==0){if (Board[4][i+1]==1){res[4][i]+=1;} else if(Board[4][i-1]==1){res[4][i]+=1;}}
//        }
//        for (int i=1; i<4;i++){
//            if (Board[i][0]==0){if (Board[i+1][0]==1){res[i][0]+=1;}else if(Board[i-1][0]==1){res[i][0]+=1;}}
//            if (Board[i][9]==0){if (Board[i+1][9]==1){res[i][9]+=1;}else if(Board[i-1][9]==1){res[i][9]+=1;}}
//        }
//        if (Board[0][0]==0){if(Board[0][1]==1 || Board[1][0]==1){res[0][0]+=1;}}
//        if (Board[4][0]==0){if(Board[3][0]==1 || Board[4][1]==1){res[4][0]+=1;}}
//        if (Board[0][9]==0){if(Board[1][9]==1 || Board[0][8]==1){res[0][9]+=1;}}
//        if (Board[4][9]==0){if(Board[3][9]==1 || Board[4][8]==1){res[4][9]+=1;}}
//        for (int i = 0; i<10;i++){
//            for (int j = 0; j<5; j++){
//                if (res[j][i] > ans) {ans = res[j][i];}
//            }
//        }
//        for (int i = 0; i<10;i++){
//            for (int j = 0; j<5; j++){
//                if (res[j][i] == ans) { return new int[]{j,i};}
//            }
//        }

//        for (int i=0; i<10;i++){
//            for (int j=0;j<4;j++){
//                if(Board[j][i]==0 && Board[j+1][i]==1){
//                    return new int[]{j,i};
//                }
//            }
//        }

        for (int i=0; i<10;i++){
            for (int j=0;j<5;j++){
                if(Board[j][i]==0){
                    return new int[]{j,i};
                }
            }
        }


//        for (int i=9; i>-1;i--){
//            for (int j=4;j>-1;j--){
//                if(Board[j][i]==0){
//                    return new int[]{j,i};
//                }
//            }
//        }
        return null;
    }
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
    public static String sortDelete(String a, String Piece){
        StringBuilder A = new StringBuilder(a);
        for (int i = 0; i < a.length()/4; i++){
            if (a.substring(i * 4, i * 4 + 4).equals(Piece)){
                return a.substring(0,4*i)+a.substring(4*i+4,a.length());
            }
        }
        return null;
    }
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
//        check center with 2 blanks
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
}
