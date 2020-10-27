/*This class is authored by Ming Lei*/
package comp1110.ass2.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A very simple viewer for piece placements in the IQ-Fit game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;
    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement){
        root.getChildren().clear();
        Pane container = new Pane();
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
            Image image = new Image(Viewer.class.getResource(URI_BASE + type + ".png").toString());
            ImageView imagePiece = new ImageView(image);
            if (setFour.contains(Character.toLowerCase(color))){
                imagePiece.setFitHeight(102);
                imagePiece.setFitWidth(204);
            }else{
                imagePiece.setFitHeight(102);
                imagePiece.setFitWidth(153);
            }
            imagePiece.setX(102+x*51);
            imagePiece.setY(75+y*51);
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
            container.getChildren().add(imagePiece);
        }
        Image Board =  new Image(Viewer.class.getResource(URI_BASE  + "board.png").toString());
        ImageView imageBoard = new ImageView(Board);
        imageBoard.setX(60);
        imageBoard.setY(60);
        imageBoard.setFitHeight(300);
        imageBoard.setFitWidth(600);
        root.getChildren().add(controls);
        root.getChildren().add(imageBoard);
        root.getChildren().add(container);
        // FIXME Task 4: implement the simple placement viewer
    }
    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FitGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //end of code
}
