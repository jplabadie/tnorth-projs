package NASPGui;
/**
 * @author Jean-Paul Labadie
 * @date 8/11/2015
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NASPGui extends Application {

    Button button;

    public static void main(String[] args) {

        launch(args); //this method call initializes the FXApplication
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("GNASP"); //The title of this window
        button = new Button(); // initializing a button (as in Android)
        button.setText("Just a button"); //set button text

        StackPane layout = new StackPane(); // set layout
        layout.getChildren().add(button); // add button to layout

        Scene scene = new Scene(layout,500,500); // set scene initialization
        primaryStage.setScene(scene); // link scene to primary stage
        primaryStage.show(); //display the GUI
    }
}
