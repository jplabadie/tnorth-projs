package ctrls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jean-Paul Labadie
 */
public class JobTabMonitorController implements Initializable {

    @FXML
    private Button nextButton;
    @FXML
    private AnchorPane jobTabMonitorPane;

    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources)
    {
        initNextButton();
    }

    private void initNextButton()
    {
        nextButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        Region job_visualization_pane =new VisualizationBrowser();

                        jobTabMonitorPane.getChildren().clear();
                        jobTabMonitorPane.getChildren().add(job_visualization_pane);
                    }
                });
    }
}