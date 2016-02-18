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
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Hashtable;


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
    @FXML
    private Button btnSaveSettings;
    @FXML
    private TextField altBwaSampQueue;
    @FXML
    private Button btnLoadSettings;
    @FXML
    private TextField altBwaSampPath;
    @FXML
    private TextField limitBwaSampMem;
    @FXML
    private TextField limitBwaSampCpu;
    @FXML
    private TextField limitBwaSampRuntime;





    private ArrayList<File> selectedFiles;
    private DirectoryChooser dirChooser = new DirectoryChooser();
    private CheckBox[] checkArray = {bwaSampCheck, bwaMemCheck, bowtie2Check, novoalignCheck, snapCheck};
    private TitledPane[] panes = {bwaSampTitledPane, bwaMemTitledPane, bowTieTitledPane, novoalignTitledPane, snapTitledPane};

    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources) {

        initStartJobButton();
        handleCheckBoxes();
        saveSettings();
        loadSettings();

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

    private void saveSettings() {
        btnSaveSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {

                        Hashtable<String, String> settings
                                = new Hashtable<String, String>();
                        if(!altBwaSampPath.isDisabled())
                            settings.put("altBwaSampPath",altBwaSampPath.getText());
                        settings.put("altBwaSampQueue",altBwaSampQueue.getText());
                        settings.put("limitBwaSampMem",limitBwaSampMem.getText());
                        settings.put("limitBwaSampCpu",limitBwaSampCpu.getText());
                        settings.put("limitBwaSampRuntime",limitBwaSampRuntime.getText());


                        try {

                            PrintWriter out = new PrintWriter("PaneSetting.dat");
                            out.write(settings.toString());
                            out.close();

                        }
                        catch (IOException exception)
                        {
                            System.out.println("Error processing file: " + exception);

                        }

                    }
                });
    }

    void loadSettings() {
        btnLoadSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        try {
                            File file = new File("PaneSetting.dat");
                            FileReader fileReader = new FileReader(file);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                sb.append(line);
                            }
                            fileReader.close();
                            String delims = "[,]";
                            String[] entries = sb.toString().split(delims);
                            entries[entries.length-1] = entries[entries.length-1].substring(0,entries[entries.length-1].length()-1);
                            //int i = sb.indexOf("{");
                            //int j = sb.indexOf("=");
                            int j;
                            for (int i = 0; i < entries.length; i++) {
                                j = entries[i].indexOf("=");
                                if ("altBwaSampPath".equals(entries[i].substring(1,j)))
                                    altBwaSampPath.setText(entries[i].substring(j+1,entries[i].length()));
                                else if ("altBwaSampQueue".equals(entries[i].substring(1,j)))
                                    altBwaSampQueue.setText(entries[i].substring(j+1,entries[i].length()));
                                else if ("limitBwaSampMem".equals(entries[i].substring(1,j)))
                                    limitBwaSampMem.setText(entries[i].substring(j+1,entries[i].length()));
                                else if ("limitBwaSampCpu".equals(entries[i].substring(1,j)))
                                    limitBwaSampCpu.setText(entries[i].substring(j+1,entries[i].length()));
                                else if ("limitBwaSampRuntime".equals(entries[i].substring(1,j)))
                                    limitBwaSampRuntime.setText(entries[i].substring(j+1,entries[i].length()));
                            }
                            /*String key = sb.substring(i + 1, j);
                            i = sb.indexOf("=");
                            j = sb.indexOf("}");
                            String value = sb.substring(i + 1, j);

                            if ("altBwaSampQueue".equals( key)) {
                                altBwaSampQueue.setText(value);
                                System.out.print("loaded");
                            }*/
                        } catch (IOException w) {
                            w.printStackTrace();
                        }
                    }

                });
    }


}
