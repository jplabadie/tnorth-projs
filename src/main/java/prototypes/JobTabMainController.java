package prototypes;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Jean-Paul Labadie
 *
 */
public class JobTabMainController implements Initializable {

    @FXML
    private AnchorPane jobConfigTabAnchorPane;
    @FXML
    private TextArea jobManagerArgs;
    @FXML
    private Button outputDirButton;
    @FXML
    private TextField outputDirText;
    @FXML
    private Button refFastaPathButton;
    @FXML
    private TextField refFastaPathText;
    @FXML
    private ChoiceBox jobManagerChoice;
    @FXML
    private VBox alignerVbox;
    @FXML
    private TitledPane bwaSampTitledPane;
    @FXML
    private TitledPane bwaMemTitledPane;
    @FXML
    private TitledPane bowTieTitledPane;
    @FXML
    private TitledPane novoalignTitledPane;
    @FXML
    private TitledPane snapTitledPane;
    @FXML
    private Button startJobButton;
    @FXML
    private Button generateXML;
    @FXML
    private CheckBox bwaSampCheck;
    @FXML
    private CheckBox bwaMemCheck;
    @FXML
    private CheckBox bowtie2Check;
    @FXML
    private CheckBox novoalignCheck;
    @FXML
    private CheckBox snapCheck;


    private ArrayList<File> selectedFiles;
    private DirectoryChooser dirChooser = new DirectoryChooser();
    private CheckBox[] checkArray = {bwaSampCheck, bwaMemCheck, bowtie2Check, novoalignCheck, snapCheck};
    private TitledPane[] panes = {bwaSampTitledPane, bwaMemTitledPane, bowTieTitledPane, novoalignTitledPane, snapTitledPane};

    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources) {

        initStartJobButton();
        handleCheckBoxes();

        jobManagerChoice.setItems(FXCollections.observableArrayList(
                "None", new Separator(), "PBS/TORQUE", "SLURM", "SGE*")
        );
        jobManagerChoice.getSelectionModel().select(0);

        outputDirButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        File file = dirChooser.showDialog(outputDirButton.getContextMenu());
                        if (file != null) {
                            //action using non-null file input here
                            try {
                                outputDirText.setText(file.getCanonicalPath());
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initStartJobButton() {
        startJobButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        try {
                            AnchorPane job_monitor_pane = FXMLLoader.load(this.getClass().getClassLoader().getResource("NASPJobMonitorPane.fxml"));
                            jobConfigTabAnchorPane.getChildren().clear();
                            jobConfigTabAnchorPane.getChildren().add(job_monitor_pane);


                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
    }


    private void handleCheckBoxes() {

        for(TitledPane pane: panes){
            checkArray[i].setOnAction(
                    new EventHandler<ActionEvent>() {
                        //@Override
                        public void handle(final ActionEvent e) {
                            if (checkArray[i].isSelected()) {
                                pane.setDisable(false);
                                pane.setExpanded(true);
                            } else {
                                panes[i].setDisable(true);
                                panes[i].setExpanded(false);
                            }
                        }
                    }
            );
            i++;

        }

        
        bwaSampCheck.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        if (bwaSampCheck.isSelected()) {
                            bwaSampTitledPane.setDisable(false);
                            bwaSampTitledPane.setExpanded(true);
                        } else {
                            bwaSampTitledPane.setDisable(true);
                            bwaSampTitledPane.setExpanded(false);
                        }
                    }
                });

        bwaMemCheck.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        if (bwaMemCheck.isSelected()) {
                            bwaMemTitledPane.setDisable(false);
                            bwaMemTitledPane.setExpanded(true);
                        } else {
                            bwaMemTitledPane.setDisable(true);
                            bwaMemTitledPane.setExpanded(false);
                        }
                    }
                });

        bowtie2Check.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        if (bowtie2Check.isSelected()) {
                            bowTieTitledPane.setDisable(false);
                            bowTieTitledPane.setExpanded(true);
                        } else {
                            bowTieTitledPane.setDisable(true);
                            bowTieTitledPane.setExpanded(false);
                        }
                    }
                });
        novoalignCheck.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        if (novoalignCheck.isSelected()) {
                            novoalignTitledPane.setDisable(false);
                            novoalignTitledPane.setExpanded(true);
                        } else {
                            novoalignTitledPane.setDisable(true);
                            novoalignTitledPane.setExpanded(false);
                        }
                    }
                });

        snapCheck.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        if (snapCheck.isSelected()) {
                            snapTitledPane.setDisable(false);
                            snapTitledPane.setExpanded(true);
                        } else {
                            snapTitledPane.setDisable(true);
                            snapTitledPane.setExpanded(false);
                        }
                    }
                });
    }

}
