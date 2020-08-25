package comp1110.ass2.gui;

import comp1110.ass2.Games;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets)

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)
    // Fields we need
    private static final int BOARD_X = 0;  // horizontal distance from board to left boundary
    private static final int BOARD_Y = 0;  // vertical distance from board to top boundary
    private static final int PIECE_WIDTH = 0;  // piece width in GUI
    private static final int PIECE_HEIGHT = 0;  // piece height in GUI
    private static final int SELECT_X = 0;  // X coordinate of the SELECT Menu
    private static final int SELECT_Y = 0;  // y coordinate of the SELECT Menu
    private static final int SELECT_WIDTH = 0;  // width of the SELECT Menu
    private static final int SELECT_HEIGHT = 0;  // height of the SELECT Menu
    private static final int GAME_WIDTH = 0;  // width of the game
    private static final int GAME_HEIGHT = 0;  // height of the game

    private final Group root = new Group();
    private final Group pieces = new Group();
    private final Group solution = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();

    // where to find media assets
    private static final String URI_BASE = null;
    private static final String BASEBOARD_URI = null;
    // the difficulty slider
     private Slider difficulty;
    // message on completion
     private Text completionText;

    // this class is to represent the piece graphically
    static class GUIPiece extends ImageView {
        // fields
        String PieceID;

        // Construct a particular playing Piece, and set the size of the piece
        public void GUIPiece(String piece) {
        } // like GUIPiece("N2")
    }

    // This class extends piece with the capacity for it to be dragged and dropped, and snap-to-grid.
    static class DraggablePiece extends GUIPiece{
        double homeX, homeY; // the original position of the dragged piece, should be in the 		// select menu
        double mouseX, mouseY; // the last known mouse positions when dragging
        int orientation;  // 0 - 3 used to change the orientation of given piece.
        boolean canMove;  // check if the piece can be dragged onto the board

        private void canMoveToBoard(String pieceID){} // return true if the piece is movable i.e. if it can be put on the board
        // DraggablePiece method convert piece from its board position numbers to GUI position 	also, using event handlers to move the piece
        public DraggablePiece(String placement){}  // like DraggablePiece("b23S")
        // Snap the tile to an appropriate position on the board or a nearest appropriate position
        // if mouseX, mouseY are not on the board.
        private void snapToBoard() {}
        // Snap the tile back to its original position in Select menu
        private void snapToHome() {}
        // Convert from x and y coordinates in GUI to a position on the board. It should be a int
        // tuple like (2, 1) which presents the entry of the board matrix.
        private void getPieceOnBoardPosition(double x, double y){}
        private void setNewGameBoardPosition(){}         // Update the game state with this piece's position
        private void checkCompletion(){}}        // Check game completion and update status


    // Set up event handlers for the main game, create handlers for key press and release events
    // like quit the game by pressing Q, Snap the last piece on the board to select menu by pressing B
    private void setUpHandlers(Scene scene) {}
    // Present the board in the board group
    private void makeBoard(){}
    // Put each of the initial pieces on the board based on the string of initialState
    private void makePiece(String placement){}
    // Put possible pieces on the select menu based on the string of initialState
    private void makeSelect(String placement){}
    // Add the Games to the board
    private void addGamesToBoard(){}
    // Reset all of pieces to their initial state
    private void resetPieces(){}
    // restart the game by pressing Restart button, reselect a new game by pressing the New game button,
    // and adjust difficulty by pressing Difficulty button
    private void makeControls(){}
    // Create the massage when the player wins the game
    private void completionmessage(){}
    // Show the completion message
    private void showCompletion(){}
    // Hide the completion message
    private void hideCompletion(){}
    // Start a new game, resetting everything as necessary
    private void newGame(Games games){}
    // Restart this game, creating a new game with the same initial state.
    private void restart(){}

    @Override
    public void start(Stage primaryStage) {
    }
}
