package comp1110.ass2.gui;

import comp1110.ass2.FitGame;
import comp1110.ass2.Games;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import comp1110.ass2.FitGame;

public class Board extends Application {

    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 300;

    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets)

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    // Fields we need:
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT= 700;
    private static final int BOARD_X = 50;  // horizontal distance from board to left boundary
    private static final int BOARD_Y = 150;  // vertical distance from board to top boundary
    private static final int PIECE_WIDTH = 0;  // piece width in GUI
    private static final int PIECE_HEIGHT = 0;  // piece height in GUI
    private static final int SELECT_X = 700;  // X coordinate of the SELECT Menu
    private static final int SELECT_Y = 50;  // y coordinate of the SELECT Menu
    private static final int SELECT_WIDTH = 0;  // width of the SELECT Menu
    private static final int SELECT_HEIGHT = 0;  // height of the SELECT Menu
    private static final int GAME_WIDTH = 0;  // width of the game
    private static final int GAME_HEIGHT = 0;  // height of the game

    private final Group root = new Group();
    private final Group pieces = new Group();
    private final Group solution = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group selectM = new Group();



    // where to find media assets
    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();
    // the difficulty slider
     private Slider difficulty = new Slider();
    // message on completion
     private Text completionText;
     private static String tempGame;

    // this class is to represent the piece graphically
    static class GUIPiece extends ImageView {
        // fields
        String PieceID;

        // Construct a particular playing Piece, and set the size of the piece
        public void GUIPiece(String piece) {
            Set<String> TypeBox = new HashSet<>();
            String[] T = {"B","G","I","L","N","O","P","R","S","Y"};
            List<String> A = Arrays.asList(T);
            ArrayList<String> allType = new ArrayList<String>(A);
            if (!allType.contains(String.valueOf(piece.charAt(0))) || !(piece.charAt(1) =='1') || !(piece.charAt(1) =='2')){
                throw new IllegalArgumentException("Bad piece: \"" + piece + "\"");
            }
            this.PieceID= piece;
            setFitHeight(PIECE_WIDTH);
            setFitWidth(PIECE_WIDTH);
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
    private void makeBoard(){
        board.getChildren().clear();
        ImageView baseboard = new ImageView();
        baseboard.setImage(new Image(BASEBOARD_URI));
//        baseboard.setPreserveRatio(true);
        baseboard.setFitWidth(BOARD_WIDTH);
        baseboard.setFitHeight(BOARD_HEIGHT);
        baseboard.setLayoutX(BOARD_X);
        baseboard.setLayoutY(BOARD_Y);
        board.getChildren().add(baseboard);
        board.toBack();
    }
    // Put each of the initial pieces on the board based on the string of initialState
    private void makePiece(String placement){
        pieces.getChildren().clear();
        for (int i=0; i<placement.length()/4;++i){
            String PiecePlacement = placement.substring(4*i,4*(i+1));
            Character[] arr1 = {'b','o','p','r','s','y'};
            Set<Character> setFour = new HashSet<>(Arrays.asList(arr1));
            char color = PiecePlacement.charAt(0);
            char rotate = PiecePlacement.charAt(3);
            int x = Character.getNumericValue(PiecePlacement.charAt(1));
            int y = Character.getNumericValue(PiecePlacement.charAt(2));
            String type;
            if (Character.isLowerCase(color)){ type = String.valueOf(color).toUpperCase()+"1";}
            else{type = String.valueOf(color).toUpperCase()+"2";}
            Image image = new Image(Board.class.getResource(URI_BASE + type + ".png").toString());
            ImageView imagePiece = new ImageView(image);
            if (setFour.contains(Character.toLowerCase(color))){
                imagePiece.setFitHeight(102);
                imagePiece.setFitWidth(204);
            }else{
                imagePiece.setFitHeight(102);
                imagePiece.setFitWidth(153);
            }
            imagePiece.setX(90+x*51);
            imagePiece.setY(165+y*51);
            Rotate rot = new Rotate();
            Translate translate = new Translate();
            if (rotate == 'E'){
                rot.setAngle(90);
                rot.setPivotX(imagePiece.getX()+imagePiece.getFitHeight());
                rot.setPivotY(imagePiece.getY());
                translate.setX(imagePiece.getFitHeight());
                imagePiece.getTransforms().addAll(rot,translate);
            }
            else if (rotate == 'S'){
                rot.setAngle(180);
                rot.setPivotX(imagePiece.getX()+imagePiece.getFitWidth());
                rot.setPivotY(imagePiece.getY()+imagePiece.getFitHeight());
                translate.setX(imagePiece.getFitWidth());
                translate.setY(imagePiece.getFitHeight());
                imagePiece.getTransforms().addAll(rot,translate);
            }
            else if (rotate == 'W'){
                rot.setAngle(270);
                rot.setPivotX(imagePiece.getX());
                rot.setPivotY(imagePiece.getY()+imagePiece.getFitWidth());
                translate.setY(imagePiece.getFitWidth());
                imagePiece.getTransforms().addAll(rot,translate);
            }
            pieces.getChildren().add(imagePiece);
        }
    }
    // Put possible pieces on the select menu based on the string of initialState
    private void makeSelect(){
        GridPane selectMenu = new GridPane();
        selectMenu.setPadding(new Insets(10,10,10,10));
        selectMenu.setVgap(10);
        selectMenu.setVgap(10);
        selectMenu.setPrefSize(180, 500);
        selectMenu.setLayoutX(SELECT_X);
        selectMenu.setLayoutY(SELECT_Y);

        Set<String> TypeBox2 = new HashSet<>();
        String[] T = {"B","G","I","L","N","O","P","R","S","Y"};
        List<String> A = Arrays.asList(T);
        ArrayList<String> allType = new ArrayList<String>(A);
        for(int j=0; j < allType.size();j++){
            String type1 = allType.get(j)+"1";
            Image input = new Image(Board.class.getResource(URI_BASE + type1 + ".png").toString());
            ImageView imageView = new ImageView(input);
            ImageView newImage = new ImageView(input);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(60);
            String type2 = allType.get(j)+"2";
            Image input2 = new Image(Board.class.getResource(URI_BASE + type2 + ".png").toString());
            ImageView imageView2 = new ImageView(input2);
            ImageView newImage2 = new ImageView(input2);
            imageView2.setPreserveRatio(true);
            imageView2.setFitWidth(60);
            Button bt = new Button();
            bt.setGraphic(imageView);
            bt.setPrefWidth(60);
            bt.setMinHeight(60);
            bt.setContentDisplay(ContentDisplay.CENTER);
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    imageView.setRotate(imageView.getRotate() + 90);
                    bt.setGraphic(imageView);
                }
            });
            bt.setOnMouseDragged(event -> {
                AnchorPane an = new AnchorPane();
                newImage.setRotate(imageView.getRotate());
                newImage.setPreserveRatio(true);
                newImage.setFitHeight(102);
                an.getChildren().add(newImage);
                AnchorPane.setLeftAnchor(newImage, event.getSceneX()-21);
                AnchorPane.setTopAnchor(newImage, event.getSceneY()-17);
                pieces.getChildren().add(an);
            });
            bt.setOnMouseReleased(event -> {     // drag is complete
                int rotate;
                rotate = (int) imageView.getRotate()/90;
                if(transfer(type1,rotate,event.getSceneX(),event.getSceneY())!=null) {
                    if (canMove(transfer(type1, rotate, event.getSceneX(), event.getSceneY())) && FitGame.isPlacementValid(FitGame.sortAdd(tempGame, transfer(type1, rotate, event.getSceneX(), event.getSceneY())))) {
                        tempGame = FitGame.sortAdd(tempGame, transfer(type1, rotate, event.getSceneX(), event.getSceneY()));
                            makePiece(tempGame);
                        }
                    else{
                        makePiece(tempGame);
                    }
                }
                else {
                    makePiece(tempGame);
                }
            });

            Button bt2 = new Button();
            bt2.setGraphic(imageView2);
            bt2.setPrefWidth(60);
            bt2.setMinHeight(60);
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    imageView2.setRotate(imageView2.getRotate() + 90);
                    bt2.setGraphic(imageView2);
                }
            });
            bt2.setOnMouseDragged(event -> {
                AnchorPane an2 = new AnchorPane();
                newImage2.setRotate(imageView2.getRotate());
                newImage2.setPreserveRatio(true);
                newImage2.setFitHeight(102);
                an2.getChildren().add(newImage2);
                AnchorPane.setLeftAnchor(newImage2, event.getSceneX()-21);
                AnchorPane.setTopAnchor(newImage2, event.getSceneY()-17);
                pieces.getChildren().add(an2);
            });
            bt2.setOnMouseReleased(event -> {     // drag is complete
                int rotate2;
                rotate2 = (int) imageView2.getRotate()/90;
                if(transfer(type2,rotate2,event.getSceneX(),event.getSceneY())!=null) {
                    if (canMove(transfer(type2, rotate2, event.getSceneX(), event.getSceneY())) && FitGame.isPlacementValid(FitGame.sortAdd(tempGame, transfer(type2, rotate2, event.getSceneX(), event.getSceneY())))) {
                        tempGame = FitGame.sortAdd(tempGame, transfer(type2, rotate2, event.getSceneX(), event.getSceneY()));
                        makePiece(tempGame);
                    }
                    else{
                        makePiece(tempGame);
                    }
                }
                else {
                    makePiece(tempGame);
                }
            });

            GridPane.setConstraints(bt,0,j);
            GridPane.setConstraints(bt2,1,j);
            selectMenu.getChildren().addAll(bt,bt2);
        }
        ScrollPane sp = new ScrollPane(selectMenu);
        sp.setLayoutX(SELECT_X);
        sp.setLayoutY(SELECT_Y);
        sp.setPrefSize(200,500);
        selectM.getChildren().add(sp);
    }
    public static String transfer(String Type, int rotate, double getX, double getY){
        String ans = "";
        String dir,T;
        switch (rotate){
            case 0:
                dir = "N";
                break;
            case 1:
                dir = "E";
                break;
            case 2:
                dir = "S";
                break;
            default:
                dir = "W";
        }
        if (Type.charAt(1) == '1'){
            T = String.valueOf(Character.toLowerCase(Type.charAt(0)));
        }
        else{
            T = String.valueOf(Type.charAt(0));
        }
        double newX = getX - 90;
        double newY = getY - 165;
        int row = (int) newX/51;
        int col = (int) newY/51;
        if (row < 10 && col < 5){
            ans = T + String.valueOf(row)+ String.valueOf(col) + dir;
            return ans;
        }
        else {return null;}
    }

    public static boolean canMove(String pieceType){
        if (tempGame != null){
            FitGame.initial();
            for (int i=0; i<tempGame.length()/4;++i){
                String PiecePlacement = tempGame.substring(4*i,4*(i+1));
                FitGame.addToBoard(PiecePlacement);}
            return FitGame.canPieceBePlaced(pieceType);}
        return false;
    }

    // Add the Games to the board
    private void addGamesToBoard(){}
    // Reset all of pieces to their initial state

    private void makeControls(){
        Button button = new Button("Restart");
        button.setLayoutX(BOARD_X  + 20);
        button.setLayoutY(VIEWER_HEIGHT - 175);
        button.setPrefHeight(30);
        button.setPrefWidth(150);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                restart();
            }
        });
        Button button2 = new Button("New Game");
        button2.setLayoutX(BOARD_X + 200);
        button2.setLayoutY(VIEWER_HEIGHT - 175);
        button2.setPrefHeight(30);
        button2.setPrefWidth(150);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newGame();
            }
        });
        controls.getChildren().add(button);
        controls.getChildren().add(button2);

        difficulty.setMin(1);
        difficulty.setMax(5);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(0);
        difficulty.setSnapToTicks(true);

        difficulty.setLayoutX(BOARD_X  + 460);
        difficulty.setLayoutY(VIEWER_HEIGHT - 175);
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(BOARD_X + 400);
        difficultyCaption.setLayoutY(VIEWER_HEIGHT - 170);
        controls.getChildren().add(difficultyCaption);
    }
    // Create the massage when the player wins the game
    private void completionMessage(){}
    // Show the completion message
    private void showCompletion(){}
    // Hide the completion message
    private void hideCompletion(){}
    // Start a new game, resetting everything as necessary
    private void newGame(){
        try {
            int diff = (int) difficulty.getValue() - 1;
            tempGame = Games.SOLUTIONS[(int) (Math.random()*24+diff*24)].objective;
            makePiece(tempGame);
        } catch (IllegalArgumentException e) {
            System.err.println("Uh oh. " + e);
            e.printStackTrace();
            Platform.exit();
        }
    }
    // Restart this game, creating a new game with the same initial state.
    private void restart(){
        pieces.getChildren().clear();
        makePiece(tempGame);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IQ-Fit");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(pieces);
        root.getChildren().add(selectM);
        makeSelect();
        makeBoard();
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        System.out.println(transfer("B1", 0,230, 320));
//    }
}
