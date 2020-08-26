package comp1110.ass2.gui;

import com.sun.glass.ui.View;
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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
     *
     */
    void makePlacement(String placement){
        root.getChildren().clear();
        char color = placement.charAt(0);
        char rotate = placement.charAt(3);
        int rotatetimes;
        String type;
        if (Character.isLowerCase(color)){ type = String.valueOf(color).toUpperCase()+"1";}
        else{type = String.valueOf(color).toUpperCase()+"2";}
        Image image = new Image(Viewer.class.getResource(URI_BASE + type + ".png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setX(200);
        imageView.setY(110);
        imageView.setFitHeight(150);
        imageView.setFitWidth(300);
        if (rotate == 'E'){rotatetimes = 1;}
        else if (rotate == 'S'){rotatetimes = 2;}
        else if (rotate == 'W'){rotatetimes = 3;}
        else{rotatetimes = 0;}
        imageView.setRotate(imageView.getRotate()+rotatetimes*90);
        root.getChildren().add(imageView);
        root.getChildren().add(controls);
        // FIXME Task 4: implement the simple placement viewer
        //
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
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FitGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);
        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
