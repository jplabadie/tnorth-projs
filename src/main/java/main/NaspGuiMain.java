package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method which defines the root of the JavaFX application
 */
public class NaspGuiMain extends Application {
    /**
     *
     * @param primaryStage the root stage of the GUI
     * @throws Exception multitude of exceptions due to resource loading issues
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main/NASPGuiMainLayout.fxml"));
        primaryStage.setTitle("NASP GUI Prototype");
        Scene scene = new Scene(root, 1024, 800);
        scene.getStylesheets().add(getClass().getResource("css/default.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}