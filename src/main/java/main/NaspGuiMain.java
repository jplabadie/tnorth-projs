package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NaspGuiMain extends Application {

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