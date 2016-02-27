package prototypes;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    @FXML
    private TitledPane aligner_options_pane;
    @FXML
    private TitledPane general_settings_pane;
    @FXML
    private TitledPane inputs_pane;
    @FXML
    private CheckBox useAltBwaMemVer;
    @FXML
    private TextField altBwaMemPath;
    @FXML
    private TextField altBwaMemQueue;
    @FXML
    private TextField limitBwaMemMem;
    @FXML
    private TextField limitBwaMemCpu;
    @FXML
    private TextField limitBwaMemRuntime;
    @FXML
    private CheckBox useAltNovoalignVer;
    @FXML
    private TextField altNovoalignPath;
    @FXML
    private TextField altNovoalignQueue;
    @FXML
    private TextField limitNovoalignMem;
    @FXML
    private TextField limitNovoalignCpu;
    @FXML
    private TextField limitNovoalignRuntime;
    @FXML
    private CheckBox useAltSnapVer;
    @FXML
    private TextField altSnapPath;
    @FXML
    private TextField altSnapQueue;
    @FXML
    private TextField limitSnapMem;
    @FXML
    private TextField limitSnapCpu;
    @FXML
    private TextField limitSnapRuntime;
    @FXML
    private CheckBox useAltBowTieVer;
    @FXML
    private TextField altBowTiePath;
    @FXML
    private TextField altBowTieQueue;
    @FXML
    private TextField limitBowTieMem;
    @FXML
    private TextField limitBowTieCpu;
    @FXML
    private TextField limitBowTieRuntime;
    @FXML
    private TextField jobManagerQueue;
    @FXML
    private TextField GATKPath;
    @FXML
    private TextField GATKArguments;
    @FXML
    private TextField GATKQueue;
    @FXML
    private TextField GATKMemory;
    @FXML
    private TextField GATKCPU;
    @FXML
    private TextField GATKRuntime;
    @FXML
    private TextField solPath;
    @FXML
    private TextField solArguments;
    @FXML
    private TextField solQueue;
    @FXML
    private TextField solMemory;
    @FXML
    private TextField solCPU;
    @FXML
    private TextField solRuntime;
    @FXML
    private TextField varPath;
    @FXML
    private TextField varArguments;
    @FXML
    private TextField varQueue;
    @FXML
    private TextField varCPU;
    @FXML
    private TextField varMemory;
    @FXML
    private TextField varRuntime;
    @FXML
    private TextField SAMPath;
    @FXML
    private TextField SAMArguments;
    @FXML
    private TextField SAMQueue;
    @FXML
    private TextField SAMCPU;
    @FXML
    private TextField SAMMemory;
    @FXML
    private TextField SAMRuntime;
    @FXML
    private TitledPane snp_caller_options_pane;
    @FXML
    private CheckBox cbGATK;
    @FXML
    private CheckBox cbSolSNP;
    @FXML
    private CheckBox cbVarScan;
    @FXML
    private CheckBox cbSAMTools;
    @FXML
    private TitledPane filter_options_pane;
    @FXML
    private CheckBox optionsOutputMatrix;
    @FXML
    private CheckBox optionsSkip;
    @FXML
    private TextField inputPath;
    @FXML
    private TextField inputRead;
    @FXML
    private TextField inputGenomes;
    @FXML
    private TextField inputSAM;
    @FXML
    private TextField inputVCF;
    @FXML
    private TextField inputNUCMER;
    @FXML
    private TextField inputDelta;
    @FXML
    private CheckBox enableAdvNucmerButton;




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

                        if (general_settings_pane.isExpanded())
                        {
                            settings.put("outputDirText", outputDirText.getText());
                            settings.put("jobManagerChoice", jobManagerChoice.getValue().toString());
                            settings.put("jobManagerQueue" , jobManagerQueue.getText());
                            settings.put("jobManagerArgs", jobManagerArgs.getText());

                        }
                        if (aligner_options_pane.isExpanded()) {
                            if (bwaSampCheck.isSelected()) {
                                if (!altBwaSampPath.isDisabled())
                                    settings.put("altBwaSampPath", altBwaSampPath.getText());
                                settings.put("altBwaSampQueue", altBwaSampQueue.getText());
                                settings.put("limitBwaSampMem", limitBwaSampMem.getText());
                                settings.put("limitBwaSampCpu", limitBwaSampCpu.getText());
                                settings.put("limitBwaSampRuntime", limitBwaSampRuntime.getText());
                            }
                            else if (bwaMemCheck.isSelected()) {
                                if (!useAltBwaMemVer.isDisabled())
                                    settings.put("altBwaMemPath", altBwaMemPath.getText());
                                settings.put("altBwaMemQueue", altBwaMemQueue.getText());
                                settings.put("limitBwaMemMem", limitBwaMemMem.getText());
                                settings.put("limitBwaMemCpu", limitBwaMemCpu.getText());
                                settings.put("limitBwaMemRuntime", limitBwaMemRuntime.getText());
                            }
                            else if (novoalignCheck.isSelected()) {
                                if (!useAltNovoalignVer.isDisabled())
                                    settings.put("altNovoalignPath", altNovoalignPath.getText());
                                settings.put("altNovoalignQueue", altNovoalignQueue.getText());
                                settings.put("limitNovoalignMem", limitNovoalignMem.getText());
                                settings.put("limitNovoalignCpu", limitNovoalignCpu.getText());
                                settings.put("limitNovoalignRuntime", limitNovoalignRuntime.getText());
                            }
                            else if (snapCheck.isSelected()) {
                                if (!useAltSnapVer.isDisabled())
                                    settings.put("altSnapPath", altSnapPath.getText());
                                settings.put("altSnapQueue", altSnapQueue.getText());
                                settings.put("limitSnapMem", limitSnapMem.getText());
                                settings.put("limitSnapCpu", limitSnapCpu.getText());
                                settings.put("limitSnapRuntime", limitSnapRuntime.getText());
                            }
                            else if (bowtie2Check.isSelected()) {
                                if (!useAltBowTieVer.isDisabled())
                                    settings.put("altBowTiePath", altBowTiePath.getText());
                                settings.put("altBowTieQueue", altBowTieQueue.getText());
                                settings.put("limitBowTieMem", limitBowTieMem.getText());
                                settings.put("limitBowTieCpu", limitBowTieCpu.getText());
                                settings.put("limitBowTieRuntime", limitBowTieRuntime.getText());
                            }

                        }

                        if (snp_caller_options_pane.isExpanded())
                        {
                            if (cbGATK.isSelected())
                            {
                                settings.put("GATKPath", GATKPath.getText());
                                settings.put("GATKArguments", GATKArguments.getText());
                                settings.put("GATKQueue", GATKQueue.getText());
                                settings.put("GATKMemory", GATKMemory.getText());
                                settings.put("GATKCPU", GATKCPU.getText());
                                settings.put("GATKRuntime", GATKRuntime.getText());
                            }
                            else if (cbSolSNP.isSelected())
                            {
                                settings.put("solPath", solPath.getText());
                                settings.put("solArguments", solArguments.getText());
                                settings.put("solQueue", solQueue.getText());
                                settings.put("solMemory", solMemory.getText());
                                settings.put("solCPU", solCPU.getText());
                                settings.put("solRuntime", solRuntime.getText());
                            }
                            else if (cbVarScan.isSelected())
                            {
                                settings.put("varPath", varPath.getText());
                                settings.put("varArguments", varArguments.getText());
                                settings.put("varQueue", varQueue.getText());
                                settings.put("varMemory", varMemory.getText());
                                settings.put("varCPU", varCPU.getText());
                                settings.put("varRuntime", varRuntime.getText());
                            }
                            else if (cbSAMTools.isSelected())
                            {
                                settings.put("SAMPath", SAMPath.getText());
                                settings.put("SAMArguments", SAMArguments.getText());
                                settings.put("SAMQueue", SAMQueue.getText());
                                settings.put("SAMMemory", SAMMemory.getText());
                                settings.put("SAMCPU", SAMCPU.getText());
                                settings.put("SAMRuntime", SAMRuntime.getText());
                            }

                        }

                        if (filter_options_pane.isExpanded())
                        {
                            if (optionsOutputMatrix.isSelected())
                                settings.put("optionsOutputMatrix","True");
                            else
                                settings.put("optionsOutputMatrix","False");

                            if (optionsSkip.isSelected())
                                settings.put("optionsSkip","True");
                            else
                                settings.put("optionsSkip","False");
                        }
                        if (inputs_pane.isExpanded())
                        {
                            settings.put("inputPath",inputPath.getText());
                            settings.put("inputGenomes",inputGenomes.getText());
                            settings.put("inputRead",inputRead.getText());
                            settings.put("inputSAM",inputSAM.getText());
                            settings.put("inputVCF",inputVCF.getText());
                            if (enableAdvNucmerButton.isSelected()) {
                                settings.put("inputNUCMER", inputPath.getText());
                                settings.put("inputDelta", inputDelta.getText());
                            }
                        }

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

                            int j;
                            if (aligner_options_pane.isExpanded()) {
                                if (bwaSampCheck.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("altBwaSampPath".equals(entries[i].substring(1, j)))
                                            altBwaSampPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("altBwaSampQueue".equals(entries[i].substring(1, j)))
                                            altBwaSampQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaSampMem".equals(entries[i].substring(1, j)))
                                            limitBwaSampMem.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaSampCpu".equals(entries[i].substring(1, j)))
                                            limitBwaSampCpu.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaSampRuntime".equals(entries[i].substring(1, j)))
                                            limitBwaSampRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                                else if (bwaMemCheck.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("altBwaMemPath".equals(entries[i].substring(1, j)))
                                            altBwaMemPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("altBwaMemQueue".equals(entries[i].substring(1, j)))
                                            altBwaMemQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaMemMem".equals(entries[i].substring(1, j)))
                                            limitBwaMemMem.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaMemCpu".equals(entries[i].substring(1, j)))
                                            limitBwaMemCpu.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitBwaMemRuntime".equals(entries[i].substring(1, j)))
                                            limitBwaMemRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                                else if (novoalignCheck.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("altNovoalignPath".equals(entries[i].substring(1, j)))
                                            altNovoalignPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("altNovoalignQueue".equals(entries[i].substring(1, j)))
                                            altNovoalignQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitNovoalignMem".equals(entries[i].substring(1, j)))
                                            limitNovoalignMem.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitNovoalignCpu".equals(entries[i].substring(1, j)))
                                            limitNovoalignCpu.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("limitNovoalignRuntime".equals(entries[i].substring(1, j)))
                                            limitNovoalignRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                            }

                            if (general_settings_pane.isExpanded())
                            {
                                for (int i = 0; i < entries.length; i++) {
                                    j = entries[i].indexOf("=");
                                    if ("outputDirText".equals(entries[i].substring(1, j)))
                                        outputDirText.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerChoice".equals(entries[i].substring(1, j)))
                                        jobManagerChoice.setValue(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerQueue".equals(entries[i].substring(1, j)))
                                        jobManagerQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerArgs".equals(entries[i].substring(1, j)))
                                        jobManagerArgs.setText(entries[i].substring(j + 1, entries[i].length()));

                                }

                            }

                            if (snp_caller_options_pane.isExpanded()) {
                                if (cbGATK.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("GATKPath".equals(entries[i].substring(1, j)))
                                            GATKPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("GATKArguments".equals(entries[i].substring(1, j)))
                                            GATKArguments.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("GATKQueue".equals(entries[i].substring(1, j)))
                                            GATKQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("GATKMemory".equals(entries[i].substring(1, j)))
                                            GATKMemory.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("GATKCPU".equals(entries[i].substring(1, j)))
                                            GATKCPU.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("GATKRuntime".equals(entries[i].substring(1, j)))
                                            GATKRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                                else if (cbSolSNP.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("solPath".equals(entries[i].substring(1, j)))
                                            solPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("solArguments".equals(entries[i].substring(1, j)))
                                            solArguments.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("solQueue".equals(entries[i].substring(1, j)))
                                            solQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("solMemory".equals(entries[i].substring(1, j)))
                                            solMemory.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("solCPU".equals(entries[i].substring(1, j)))
                                            solCPU.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("solRuntime".equals(entries[i].substring(1, j)))
                                            solRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                                else if (cbVarScan.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("varPath".equals(entries[i].substring(1, j)))
                                            varPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("varArguments".equals(entries[i].substring(1, j)))
                                            varArguments.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("varQueue".equals(entries[i].substring(1, j)))
                                            varQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("varMemory".equals(entries[i].substring(1, j)))
                                            varMemory.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("varCPU".equals(entries[i].substring(1, j)))
                                            varCPU.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("varRuntime".equals(entries[i].substring(1, j)))
                                            varRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                                else if (cbSAMTools.isSelected()) {
                                    for (int i = 0; i < entries.length; i++) {
                                        j = entries[i].indexOf("=");
                                        if ("SAMPath".equals(entries[i].substring(1, j)))
                                            SAMPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("SAMArguments".equals(entries[i].substring(1, j)))
                                            SAMArguments.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("SAMQueue".equals(entries[i].substring(1, j)))
                                            SAMQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("SAMMemory".equals(entries[i].substring(1, j)))
                                            SAMMemory.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("SAMCPU".equals(entries[i].substring(1, j)))
                                            SAMCPU.setText(entries[i].substring(j + 1, entries[i].length()));
                                        else if ("SAMRuntime".equals(entries[i].substring(1, j)))
                                            SAMRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    }
                                }
                            }
                            if (filter_options_pane.isExpanded())
                            {
                                for (int i = 0; i < entries.length; i++) {
                                    j = entries[i].indexOf("=");
                                    if ("optionsOutputMatrix".equals(entries[i].substring(1, j))) {
                                        if (entries[i].substring(j + 1, entries[i].length()).equals("True"))
                                            optionsOutputMatrix.setSelected(true);
                                        else
                                            optionsOutputMatrix.setSelected(false);
                                    }
                                    else if ("optionsSkip".equals(entries[i].substring(1, j))) {
                                        if (entries[i].substring(j + 1, entries[i].length()).equals("True"))
                                            optionsSkip.setSelected(true);
                                        else
                                            optionsSkip.setSelected(false);
                                    }



                                }
                            }
                            if (inputs_pane.isExpanded())
                            {
                                for (int i = 0; i < entries.length; i++) {
                                    j = entries[i].indexOf("=");
                                    if ("inputPath".equals(entries[i].substring(1, j)))
                                        inputPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputGenomes".equals(entries[i].substring(1, j)))
                                        inputGenomes.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputRead".equals(entries[i].substring(1, j)))
                                        inputRead.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputSAM".equals(entries[i].substring(1, j)))
                                        inputSAM.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputVCF".equals(entries[i].substring(1, j)))
                                        inputVCF.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputNUCMER".equals(entries[i].substring(1, j)))
                                        inputNUCMER.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputDelta".equals(entries[i].substring(1, j)))
                                        inputDelta.setText(entries[i].substring(j + 1, entries[i].length()));
                                }
                            }


                        } catch (IOException w) {
                            w.printStackTrace();
                        }
                    }

                });
    }


}
