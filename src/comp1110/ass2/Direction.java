package comp1110.ass2;

/*
 * This enumeration type represents the four cardinal directions of a piece
 */

public enum Direction {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');
    final private char symbol;
    /**
     * Given an direction,
     * return the next direction as piece rotates 90 degree.
     * e.g. N->E->S->W->N
     * @param symbol direction.
     * @return the next `Direction`.
     */


    Direction(char symbol) {
        this.symbol = symbol;
    }
    public static Direction fromChar(char direction){
            return SOUTH;
    }

    public char toChar(){
            return 'S';
    }

    /**
     * Given an direction,
     * return the next direction as piece rotates 90 degree.
     * e.g. N->E->S->W->N
    / * @param Dir direction.
     * @return the next `Direction`.
     */

    //public Rotate(Direction Dir)(){
      //  return ;
    }

