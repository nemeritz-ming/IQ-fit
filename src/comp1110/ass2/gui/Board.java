package comp1110.ass2.gui;

import comp1110.ass2.FitGame;
import comp1110.ass2.Games;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.*;

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
    private static final int PIECE_WIDTH = 51;  // piece width in GUI
    private static final int SELECT_X = 700;  // X coordinate of the SELECT Menu
    private static final int SELECT_Y = 50;  // y coordinate of the SELECT Menu
    private static final int SELECT_WIDTH = 180;  // width of the SELECT Menu
    private static final int SELECT_HEIGHT = 500;  // height of the SELECT Menu
    private static final int SELECT_GAP = 10;  // GAP of the SELECT Menu

    private final Group root = new Group();
    private final Group pieces = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group selectM = new Group();



    // where to find media assets
    private static final String URI_BASE = "assets/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();
    // the difficulty slider
    private final Slider difficulty = new Slider();
    // message on completion
    private final Text completionText = new Text("Well done!");
    private static String tempGame;
    private static String GameSolution;
    private static String Game;



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
            imagePiece.setX(90+x*PIECE_WIDTH);
            imagePiece.setY(165+y*PIECE_WIDTH);
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
    private void delete(){
        pieces.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY){
                double posX = e.getX() - 90;
                double posY = e.getY() - 165;
                if( posX / PIECE_WIDTH < 10 && posY / PIECE_WIDTH < 5){
                    String a = FitGame.findPieceSpotOnBoard(tempGame,(int) posY / PIECE_WIDTH,(int) posX / PIECE_WIDTH);
                    if( a != null ){
                        if(!Game.contains(a)){
                            tempGame = FitGame.sortDelete(tempGame, a);
                            assert tempGame != null;
                            makePiece(tempGame);
                        }
                    }
                }
            }
        });
    }

    // Put possible pieces on the select menu based on the string of initialState
    private void makeSelect(){
        GridPane selectMenu = new GridPane();
        selectMenu.setPadding(new Insets(10,10,10,10));
        selectMenu.setVgap(SELECT_GAP);
        selectMenu.setHgap(SELECT_GAP);
        selectMenu.setPrefSize(SELECT_WIDTH, SELECT_HEIGHT);
        selectMenu.setLayoutX(SELECT_X);
        selectMenu.setLayoutY(SELECT_Y);

        String[] T = {"B","G","I","L","N","O","P","R","S","Y"};
        List<String> A = Arrays.asList(T);
        ArrayList<String> allType = new ArrayList<>(A);
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

            Character[] arr1 = {'b','o','p','r','s','y'};
            Set<Character> setFour = new HashSet<>(Arrays.asList(arr1));

            Button bt = new Button();
            bt.setGraphic(imageView);
            bt.setPrefWidth(60);
            bt.setMinHeight(60);
            bt.setContentDisplay(ContentDisplay.CENTER);
            bt.setOnAction(e -> {
                imageView.setRotate(imageView.getRotate() + 90);
                bt.setGraphic(imageView);
            });
            bt.setOnMouseDragged(event -> {
                AnchorPane an = new AnchorPane();
                newImage.setRotate(imageView.getRotate());
                newImage.setPreserveRatio(true);
                newImage.setFitHeight(102);
                an.getChildren().add(newImage);
                if(setFour.contains(Character.toLowerCase(type1.charAt(0))) && ((int)imageView.getRotate() / 90) % 2 == 1 ){
                    AnchorPane.setLeftAnchor(newImage, event.getSceneX()-80);
                    AnchorPane.setTopAnchor(newImage, event.getSceneY()+25);
                }
                else if(!setFour.contains(Character.toLowerCase(type1.charAt(0))) && ((int)imageView.getRotate() / 90) % 2 == 1 ){
                    AnchorPane.setLeftAnchor(newImage, event.getSceneX()-47);
                    AnchorPane.setTopAnchor(newImage, event.getSceneY()+3);
                }
                else{
                    AnchorPane.setLeftAnchor(newImage, event.getSceneX()-21);
                    AnchorPane.setTopAnchor(newImage, event.getSceneY()-17);
                }
                pieces.getChildren().add(an);
            });

            bt.setOnMouseReleased(event -> {     // drag is complete
                int rotate;
                rotate = (int) newImage.getRotate()/90 % 4;
                if(transfer(type1,rotate,event.getSceneX(),event.getSceneY())!=null) {
                    if (canMove(transfer(type1, rotate, event.getSceneX(), event.getSceneY())) && FitGame.isPlacementValid(FitGame.sortAdd(tempGame, transfer(type1, rotate, event.getSceneX(), event.getSceneY())))
                            && FitGame.havePiecesBeenUsed(FitGame.sortAdd(tempGame, transfer(type1, rotate, event.getSceneX(), event.getSceneY())))) {
                        tempGame = FitGame.sortAdd(tempGame, transfer(type1, rotate, event.getSceneX(), event.getSceneY()));
                            makePiece(tempGame);
                            checkCompletionUi();
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
            bt2.setOnAction(e -> {
                imageView2.setRotate(imageView2.getRotate() + 90);
                bt2.setGraphic(imageView2);
            });
            bt2.setOnMouseDragged(event -> {
                AnchorPane an2 = new AnchorPane();
                newImage2.setRotate(imageView2.getRotate());
                newImage2.setPreserveRatio(true);
                newImage2.setFitHeight(102);
                an2.getChildren().add(newImage2);
                if(setFour.contains(Character.toLowerCase(type2.charAt(0))) && ((int)imageView2.getRotate() / 90) % 2 == 1 ){
                    AnchorPane.setLeftAnchor(newImage2, event.getSceneX()-80);
                    AnchorPane.setTopAnchor(newImage2, event.getSceneY()+25);
                }
                else if(!setFour.contains(Character.toLowerCase(type2.charAt(0))) && ((int)imageView2.getRotate() / 90) % 2 == 1 ){
                    AnchorPane.setLeftAnchor(newImage2, event.getSceneX()-47);
                    AnchorPane.setTopAnchor(newImage2, event.getSceneY()+3);
                }
                else{
                    AnchorPane.setLeftAnchor(newImage2, event.getSceneX()-21);
                    AnchorPane.setTopAnchor(newImage2, event.getSceneY()-17);
                }
                pieces.getChildren().add(an2);
            });
            bt2.setOnMouseReleased(event -> {     // drag is complete
                int rotate2;
                rotate2 = (int) imageView2.getRotate()/90 % 4;
                if(transfer(type2,rotate2,event.getSceneX(),event.getSceneY())!=null) {
                    if (canMove(transfer(type2, rotate2, event.getSceneX(), event.getSceneY())) && FitGame.isPlacementValid(FitGame.sortAdd(tempGame, transfer(type2, rotate2, event.getSceneX(), event.getSceneY())))
                            && FitGame.havePiecesBeenUsed(FitGame.sortAdd(tempGame, transfer(type2, rotate2, event.getSceneX(), event.getSceneY())))) {
                        tempGame = FitGame.sortAdd(tempGame, transfer(type2, rotate2, event.getSceneX(), event.getSceneY()));
                        makePiece(tempGame);
                        checkCompletionUi();
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
//    transfer function
//    given a piece's type such as "B1", a rotate index, and its position on the UI board like (231, 255)
//    return its PiecePlacement in FitGame class like "B23S".
    public static String transfer(String Type, int rotate, double getX, double getY){
        String ans;
        String dir,T;
        String[] Ty = {"B","G","I","L","N","O","P","R","S","Y"};
        List<String> TypeBox = Arrays.asList(Ty);
        if (! TypeBox.contains(String.valueOf(Character.toUpperCase(Type.charAt(0))))){
            return null;
        }
        if(rotate >3 || rotate <0){return null;}
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
        if (newX < 0 || newY < 0){return null;}
        int row = (int) newX/51;
        int col = (int) newY/51;
        if (row < 10 && col < 5){
            ans = T + row + col + dir;
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

    private void makeControls(){
        Text hintMassage = new Text();
        hintMassage.setText("Show Hints By Pressing '/' ");
        hintMassage.setFill(Color.BLACK);
        hintMassage.setX(70);
        hintMassage.setY(600);
        hintMassage.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Button button = new Button("Restart");
        button.setLayoutX(BOARD_X  + 20);
        button.setLayoutY(VIEWER_HEIGHT - 175);
        button.setPrefHeight(30);
        button.setPrefWidth(150);
        button.setOnAction(e -> restart());
        Button button2 = new Button("New Game");
        button2.setLayoutX(BOARD_X + 200);
        button2.setLayoutY(VIEWER_HEIGHT - 175);
        button2.setPrefHeight(30);
        button2.setPrefWidth(150);
        button2.setOnAction(e -> newGame());
        controls.getChildren().add(button);
        controls.getChildren().add(button2);
        controls.getChildren().add(hintMassage);

        difficulty.setMin(1);
        difficulty.setMax(5);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(0);
        difficulty.setSnapToTicks(true);
        difficulty.setOnMouseClicked(e -> newGame());
        difficulty.setLayoutX(BOARD_X  + 460);
        difficulty.setLayoutY(VIEWER_HEIGHT - 175);
        controls.getChildren().add(difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GREY);
        difficultyCaption.setLayoutX(BOARD_X + 400);
        difficultyCaption.setLayoutY(VIEWER_HEIGHT - 170);
        controls.getChildren().add(difficultyCaption);
    }
    // make completion
    private void makeCompletion(){
        completionText.setFill(Color.BLACK);
        completionText.setCache(true);
        completionText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 80));
        completionText.setLayoutX(170);
        completionText.setLayoutY(100);
        completionText.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(completionText);
    }

    private void checkCompletionUi(){
        if(FitGame.checkCompletion()){
            showCompletion();
        }
    }
    // Show the completion message
    private void showCompletion(){
        completionText.toFront();
        completionText.setOpacity(1);
    }
    // Hide the completion message
    private void hideCompletion(){
        completionText.toBack();
        completionText.setOpacity(0);
    }
    // Start a new game, resetting everything as necessary
    private void newGame(){
        try {
            hideCompletion();
            int diff = (int) difficulty.getValue() - 1;
            int num = (int) (Math.random()*24+diff*24);
            Game = Games.SOLUTIONS[num].objective;
            tempGame = Game;
            GameSolution = Games.SOLUTIONS[num].placement;
            makePiece(Game);
            makeSelect();
        } catch (IllegalArgumentException e) {
            System.err.println("Uh oh. " + e);
            e.printStackTrace();
            Platform.exit();
        }
    }
    // Restart this game, creating a new game with the same initial state.
    private void restart(){
        hideCompletion();
        pieces.getChildren().clear();
        tempGame = Game;
        makePiece(Game);
        makeSelect();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ-Fit");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        scene.setOnKeyTyped(keyEvent -> {
            if(keyEvent.getCharacter().equals("q")||keyEvent.getCharacter().equals("Q")){
                Platform.exit();
                keyEvent.consume();
            }
            if (keyEvent.getCharacter().equals("/") || keyEvent.toString().equals("/")){
                String HintString = FitGame.findWrongPieces(tempGame, GameSolution);
                double width = 600;
                double height = 115;
                GridPane HintPane = new GridPane();
                HintPane.setPadding(new Insets(30,20,20,20));
                HintPane.setPrefSize(width, height);
                HintPane.setVgap(40);
                HintPane.setHgap(20);
                int s = HintString.length() / 2;
                for (int i=0; i<s; ++i){
                    String PiecePlacement = HintString.substring(2*i,2*(i+1));
                    char color = PiecePlacement.charAt(0);
                    char rotate = PiecePlacement.charAt(1);
                    String type;
                    if (Character.isLowerCase(color)){ type = String.valueOf(color).toUpperCase()+"1";}
                    else{type = String.valueOf(color).toUpperCase()+"2";}
                    Image image = new Image(Board.class.getResource(URI_BASE + type + ".png").toString());
                    ImageView imagePiece = new ImageView(image);
                    imagePiece.setPreserveRatio(true);
                    imagePiece.setFitHeight(45);
                    switch (rotate){
                        case 'N':
                            imagePiece.setRotate(0);
                            break;
                        case 'E':
                            imagePiece.setRotate(90);
                            break;
                        case 'S':
                            imagePiece.setRotate(180);
                            break;
                        default:
                            imagePiece.setRotate(270);
                            break;
                    }
                    if (i < 5){
                        HintPane.add(imagePiece,i, 0);}
                    else{
                        height = 230;
                        HintPane.setPrefSize(width, height);
                        HintPane.add(imagePiece,i-5, 1);}
                }
                Scene secondScene = new Scene(HintPane,width , height);
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Hint !");
                newWindow.setScene(secondScene);
                newWindow.setX(540);
                newWindow.setY(130);
                newWindow.show();
            }
        });
        root.getChildren().add(board);
        root.getChildren().add(controls);
        root.getChildren().add(pieces);
        root.getChildren().add(selectM);
        makeCompletion();
        hideCompletion();
        makeSelect();
        makeBoard();
        makeControls();
        delete();
        newGame();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//    public static void main(String[] args) {
//        System.out.println(transfer("B1", 0,230, 320));
//    }
}
