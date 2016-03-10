package prototypes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xmlsources.*;

import java.io.*;
import java.net.URL;
import java.util.*;


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
    private ListView inputRead;
    @FXML
    private ListView inputGenomes;
    @FXML
    private ListView inputSAM;
    @FXML
    private ListView inputVCF;
    @FXML
    private TextField inputNUCMER;
    @FXML
    private TextField inputDelta;
    @FXML
    private CheckBox enableAdvNucmerButton;
    @FXML
    private TitledPane gatkOptionsPane;
    @FXML
    private TitledPane solSnpPane;
    @FXML
    private TitledPane varScanPane;
    @FXML
    private TitledPane samtoolsPane;

    private NaspInputData naspData;

    public static final ObservableList data =
            FXCollections.observableArrayList();

    private ArrayList<File> selectedFiles;
    private DirectoryChooser dirChooser = new DirectoryChooser();
    private CheckBox[] checkArray = {bwaSampCheck, bwaMemCheck, bowtie2Check, novoalignCheck, snapCheck};
    private TitledPane[] panes = {bwaSampTitledPane, bwaMemTitledPane, bowTieTitledPane, novoalignTitledPane, snapTitledPane};

    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources) {
        // The lists of all ListViews, CheckBoxes, and TitledPanes are created to add drag, and toggle functionality iteratively
        TextField[] textFieldArray = {inputPath, outputDirText};
        ListView[] listViewArray = {inputGenomes, inputRead, inputSAM, inputVCF};
        CheckBox[] checkBoxArray = {bwaSampCheck, bwaMemCheck, bowtie2Check, novoalignCheck, snapCheck, cbGATK, cbSolSNP, cbVarScan, cbSAMTools};
        TitledPane[] checkPaneArray = {bwaSampTitledPane, bwaMemTitledPane, bowTieTitledPane, novoalignTitledPane, snapTitledPane, gatkOptionsPane, solSnpPane, varScanPane, samtoolsPane};

        initStartJobButton();
        // Drag and drop is initialized for all the fields that need it
        initializeCheckBoxToggle(checkBoxArray, checkPaneArray);
        initializeListViewDrag(listViewArray);
        initializeTextFieldDrag(textFieldArray);
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

        naspData = new ObjectFactory().createNaspInputDataType();
    }

    // This method calls the setListDragHandler for every ListView in the array passed to it
    private void initializeListViewDrag (ListView[] listFields) {
        int currentListItemIndex = 0;

        while (currentListItemIndex < listFields.length) {
            //System.out.println("Current Index: " + currentListItemIndex);
            setListDragHandler(listFields[currentListItemIndex]);
            currentListItemIndex++;
        }
    }
    // This method sets the drag functionality to a ListView
    private void setListDragHandler (ListView view) {
        // Get the current items in the ListView passed in
        final ObservableList listContents = view.getItems();
        view.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                
                boolean success = false;
                if (db.hasString()) {
                    String content = db.getString().substring(db.getString().indexOf('\'') + 1, db.getString().length() - 1);
                    File file = new File(content);
                    if (!file.isDirectory()) {
                        event.acceptTransferModes(TransferMode.ANY);
                        listContents.add(content);
                        System.out.println(listContents);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Incorrect Input Format");
                        alert.setHeaderText(null);
                        alert.setContentText("The dragged input is not a file.");
                        alert.showAndWait();
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        view.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if(db.hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });
    }

    private void initializeTextFieldDrag(TextField[] textFields) {
        int currentListItemIndex = 0;

        while (currentListItemIndex < textFields.length) {
            setTextFieldDragHandler(textFields[currentListItemIndex]);
            currentListItemIndex++;
        }
    }

    private void setTextFieldDragHandler(TextField textField) {

        textField.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });

         textField.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();

                boolean success = false;
                if (db.hasString()) {
                    String content = db.getString().substring(db.getString().indexOf('\'') + 1, db.getString().length() - 1);
                    File file = new File(content);
                    if (file.isDirectory()) {
                        event.acceptTransferModes(TransferMode.ANY);
                        System.out.println("Contents: " + content);
                        //textField.setText(content);
                        //System.out.println("item: " + item.toString());
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Incorrect Input Format");
                        alert.setHeaderText(null);
                        alert.setContentText("The dragged input is not a file path.");
                        alert.showAndWait();
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    private void initStartJobButton() {
        startJobButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        try {

                            saveFormState();
                            AnchorPane job_monitor_pane = FXMLLoader.load(this.getClass().getClassLoader().getResource("NASPJobMonitorPane.fxml"));
                            jobConfigTabAnchorPane.getChildren().clear();
                            jobConfigTabAnchorPane.getChildren().add(job_monitor_pane);


                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
    }

    private void saveFormState(){

        ExternalApplications exapps = new ExternalApplications();
        naspData.setExternalApplications(exapps);

        Options opts = new Options();
        naspData.setOptions(opts);

        Files files = new Files();
        naspData.setFiles(files);

        opts.setOutputFolder(outputDirText.getText());
        opts.setJobSubmitter(jobManagerChoice.getValue().toString());
        opts.setRunName("jp-"+ Calendar.DATE);

        // aligner_options_pane
        List<Aligner> aligners = exapps.getAligner();
        List<SNPCaller> snpcallers = exapps.getSNPCaller();

        Aligner bwa_samp = new Aligner();
        aligners.add(bwa_samp);
        JobParameters bwa_samp_param = new JobParameters();
        bwa_samp.setJobParameters(bwa_samp_param);
        bwa_samp.setPath(altBwaSampPath.getText());
        bwa_samp_param.setQueue(altBwaSampQueue.getText());
        bwa_samp_param.setMemRequested(limitBwaSampMem.getText());
        bwa_samp_param.setNumCPUs(limitBwaSampCpu.getText());
        bwa_samp_param.setWalltime(limitBwaSampRuntime.getText());

        Aligner bwa_mem = new Aligner();
        aligners.add(bwa_mem);
        JobParameters bwa_mem_param = new JobParameters();
        bwa_mem.setJobParameters(bwa_mem_param);
        bwa_mem.setPath(altBwaMemPath.getText());
        bwa_mem_param.setQueue(altBwaMemQueue.getText());
        bwa_mem_param.setMemRequested(limitBwaMemMem.getText());
        bwa_mem_param.setNumCPUs(limitBwaMemCpu.getText());
        bwa_mem_param.setWalltime(limitBwaMemRuntime.getText());

        Aligner novo = new Aligner();
        aligners.add(novo);
        JobParameters novo_param = new JobParameters();
        novo.setJobParameters(novo_param);
        novo.setPath(altNovoalignPath.getText());
        novo_param.setQueue(altNovoalignQueue.getText());
        novo_param.setMemRequested(limitNovoalignMem.getText());
        novo_param.setNumCPUs(limitNovoalignCpu.getText());
        novo_param.setWalltime(limitNovoalignRuntime.getText());

        Aligner snap = new Aligner();
        aligners.add(snap);
        JobParameters snap_param = new JobParameters();
        snap.setJobParameters(snap_param);
        snap.setPath(altSnapPath.getText());
        snap_param.setQueue(altSnapQueue.getText());
        snap_param.setMemRequested(limitSnapMem.getText());
        snap_param.setNumCPUs(limitSnapCpu.getText());
        snap_param.setWalltime(limitSnapRuntime.getText());

        Aligner bow = new Aligner();
        aligners.add(bow);
        JobParameters bow_param = new JobParameters();
        bow.setJobParameters(bow_param);
        bow.setPath(altBowTiePath.getText());
        bow_param.setQueue(altBowTieQueue.getText());
        bow_param.setMemRequested(limitBowTieMem.getText());
        bow_param.setNumCPUs(limitBowTieCpu.getText());
        bow_param.setWalltime(limitBowTieRuntime.getText());

        SNPCaller gatk = new SNPCaller();
        snpcallers.add(gatk);
        JobParameters gatk_param = new JobParameters();
        gatk.setJobParameters(gatk_param);
        gatk.setPath(GATKPath.getText());
        gatk.setAdditionalArgs(GATKArguments.getText());
        gatk_param.setQueue(GATKQueue.getText());
        gatk_param.setMemRequested(GATKMemory.getText());
        gatk_param.setNumCPUs(GATKCPU.getText());
        gatk_param.setWalltime(GATKRuntime.getText());

        SNPCaller sol = new SNPCaller();
        snpcallers.add(sol);
        JobParameters sol_param = new JobParameters();
        sol.setJobParameters(sol_param);
        sol.setPath(solPath.getText());
        sol.setAdditionalArgs(solArguments.getText());
        sol_param.setQueue(solQueue.getText());
        sol_param.setMemRequested(solMemory.getText());
        sol_param.setNumCPUs(solCPU.getText());
        sol_param.setWalltime(solRuntime.getText());

        SNPCaller var = new SNPCaller();
        snpcallers.add(var);
        JobParameters var_param = new JobParameters();
        var.setJobParameters(var_param);
        var.setPath(varPath.getText());
        var.setAdditionalArgs(varArguments.getText());
        var_param.setQueue(varQueue.getText());
        var_param.setMemRequested(varMemory.getText());
        var_param.setNumCPUs(varCPU.getText());
        var_param.setWalltime(varRuntime.getText());

        SNPCaller sam = new SNPCaller();
        snpcallers.add(sam);
        JobParameters sam_param = new JobParameters();
        sam.setJobParameters(sam_param);
        sam.setPath(SAMPath.getText());
        sam.setAdditionalArgs(SAMArguments.getText());
        sam_param.setQueue(SAMQueue.getText());
        sam_param.setMemRequested(SAMMemory.getText());
        sam_param.setNumCPUs(SAMCPU.getText());
        sam_param.setWalltime(SAMRuntime.getText());

        //filter_options_pane

        //  inputs_pane
        AlignmentFolder alignment = new AlignmentFolder();
            files.setAlignmentFolder(alignment);
        AssemblyFolder assembly = new AssemblyFolder();
            files.setAssemblyFolder(assembly);
        ReadFolder read = new ReadFolder();
            files.setReadFolder(read);

        alignment.setPath(inputPath.getText());
        //read.setPath(inputRead.getItems().get(0).toString());

        OutputParser.jaxbObjectToXML(naspData, outputDirText.getText());

    }
    private void initializeCheckBoxToggle (CheckBox[] checkArray, TitledPane[] checkPanes) {
        for (int i = 0; i < checkArray.length; i++) {
            setCheckboxToggle(checkArray[i], checkPanes, i);
        }
    }

    private void setCheckboxToggle (final CheckBox checkBox, TitledPane[] checkPanes,  int paneIndex) {
        final TitledPane correspondingPane = checkPanes[paneIndex];
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkBox.isSelected()) {
                    correspondingPane.setDisable(false);
                    correspondingPane.setExpanded(true);
                }
                else {
                    correspondingPane.setDisable(true);
                    correspondingPane.setExpanded(false);
                }
            }
        });
    }
    /*
        capturing users input for template saving functionality
     */
    private void saveSettings() {
        btnSaveSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {

                        final Hashtable<String, String> settings
                                = new Hashtable<String, String>();

                        //general_settings_pane
                        settings.put("outputDirText", outputDirText.getText());
                        settings.put("jobManagerChoice", jobManagerChoice.getValue().toString());
                        settings.put("jobManagerQueue", jobManagerQueue.getText());
                        settings.put("jobManagerArgs", jobManagerArgs.getText());

                        // aligner_options_pane

                        settings.put("altBwaSampPath", altBwaSampPath.getText());
                        settings.put("altBwaSampQueue", altBwaSampQueue.getText());
                        settings.put("limitBwaSampMem", limitBwaSampMem.getText());
                        settings.put("limitBwaSampCpu", limitBwaSampCpu.getText());
                        settings.put("limitBwaSampRuntime", limitBwaSampRuntime.getText());
                        settings.put("altBwaMemPath", altBwaMemPath.getText());
                        settings.put("altBwaMemQueue", altBwaMemQueue.getText());
                        settings.put("limitBwaMemMem", limitBwaMemMem.getText());
                        settings.put("limitBwaMemCpu", limitBwaMemCpu.getText());
                        settings.put("limitBwaMemRuntime", limitBwaMemRuntime.getText());
                        settings.put("altNovoalignPath", altNovoalignPath.getText());
                        settings.put("altNovoalignQueue", altNovoalignQueue.getText());
                        settings.put("limitNovoalignMem", limitNovoalignMem.getText());
                        settings.put("limitNovoalignCpu", limitNovoalignCpu.getText());
                        settings.put("limitNovoalignRuntime", limitNovoalignRuntime.getText());
                        settings.put("altSnapPath", altSnapPath.getText());
                        settings.put("altSnapQueue", altSnapQueue.getText());
                        settings.put("limitSnapMem", limitSnapMem.getText());
                        settings.put("limitSnapCpu", limitSnapCpu.getText());
                        settings.put("limitSnapRuntime", limitSnapRuntime.getText());
                        settings.put("altBowTiePath", altBowTiePath.getText());
                        settings.put("altBowTieQueue", altBowTieQueue.getText());
                        settings.put("limitBowTieMem", limitBowTieMem.getText());
                        settings.put("limitBowTieCpu", limitBowTieCpu.getText());
                        settings.put("limitBowTieRuntime", limitBowTieRuntime.getText());

                        //snp_caller_options_pane
                        settings.put("GATKPath", GATKPath.getText());
                        settings.put("GATKArguments", GATKArguments.getText());
                        settings.put("GATKQueue", GATKQueue.getText());
                        settings.put("GATKMemory", GATKMemory.getText());
                        settings.put("GATKCPU", GATKCPU.getText());
                        settings.put("GATKRuntime", GATKRuntime.getText());
                        settings.put("solPath", solPath.getText());
                        settings.put("solArguments", solArguments.getText());
                        settings.put("solQueue", solQueue.getText());
                        settings.put("solMemory", solMemory.getText());
                        settings.put("solCPU", solCPU.getText());
                        settings.put("solRuntime", solRuntime.getText());
                        settings.put("varPath", varPath.getText());
                        settings.put("varArguments", varArguments.getText());
                        settings.put("varQueue", varQueue.getText());
                        settings.put("varMemory", varMemory.getText());
                        settings.put("varCPU", varCPU.getText());
                        settings.put("varRuntime", varRuntime.getText());
                        settings.put("SAMPath", SAMPath.getText());
                        settings.put("SAMArguments", SAMArguments.getText());
                        settings.put("SAMQueue", SAMQueue.getText());
                        settings.put("SAMMemory", SAMMemory.getText());
                        settings.put("SAMCPU", SAMCPU.getText());
                        settings.put("SAMRuntime", SAMRuntime.getText());

                        //filter_options_pane
                        if (optionsOutputMatrix.isSelected())
                            settings.put("optionsOutputMatrix", "True");
                        else
                            settings.put("optionsOutputMatrix", "False");

                        if (optionsSkip.isSelected())
                            settings.put("optionsSkip", "True");
                        else
                            settings.put("optionsSkip", "False");

                        //  inputs_pane
                        settings.put("inputPath", inputPath.getText());
                        //settings.put("inputGenomes", inputGenomes.getItems().toString());
                        //settings.put("inputRead", inputRead.getText());
                        //settings.put("inputSAM", inputSAM.getText());
                        //settings.put("inputVCF", inputVCF.getText());
                        settings.put("inputNUCMER", inputPath.getText());
                        settings.put("inputDelta", inputDelta.getText());

                        // saving all the inputs
                        final Stage dialogStage = new Stage();

                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save Template");
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
                        fileChooser.getExtensionFilters().add(extFilter);
                        File file = fileChooser.showSaveDialog(dialogStage);
                        if (file != null) {
                            saveTempFile(settings.toString(), file);
                        }
                    }
                });
    }

    /*
        Loading the users input for loading template functionality
     */
    void loadSettings() {
        btnLoadSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        try {
                            final Stage dialogStage = new Stage();
                            FileChooser fileChooser = new FileChooser();
                            fileChooser.setTitle("Save Template");
                            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("dat files (*.dat)", "*.dat");
                            fileChooser.getExtensionFilters().add(extFilter);
                            File file = fileChooser.showOpenDialog(dialogStage);
                            FileReader fileReader = null;
                            StringBuilder sb = new StringBuilder();
                            if (file != null) {
                                fileReader = new FileReader(file);
                                BufferedReader bufferedReader = new BufferedReader(fileReader);

                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    sb.append(line);
                                }
                                fileReader.close();

                                String delims = "[,]";
                                String[] entries = sb.toString().split(delims);
                                entries[entries.length - 1] = entries[entries.length - 1].substring(0, entries[entries.length - 1].length() - 1);

                                int j;
                                // aligner_options_pane
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
                                    else if ("altSnapPath".equals(entries[i].substring(1, j)))
                                        altSnapPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("altSnapQueue".equals(entries[i].substring(1, j)))
                                        altSnapQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitSnapMem".equals(entries[i].substring(1, j)))
                                        limitSnapMem.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitSnapCpu".equals(entries[i].substring(1, j)))
                                        limitSnapCpu.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitSnapRuntime".equals(entries[i].substring(1, j)))
                                        limitSnapRuntime.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("altBowTiePath".equals(entries[i].substring(1, j)))
                                        altBowTiePath.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("altBowTieQueue".equals(entries[i].substring(1, j)))
                                        altBowTieQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitBowTieMem".equals(entries[i].substring(1, j)))
                                        limitBowTieMem.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitBowTieCpu".equals(entries[i].substring(1, j)))
                                        limitBowTieCpu.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("limitBowTieRuntime".equals(entries[i].substring(1, j)))
                                        limitBowTieRuntime.setText(entries[i].substring(j + 1, entries[i].length()));

                                    // general_settings_pane

                                    if ("outputDirText".equals(entries[i].substring(1, j)))
                                        outputDirText.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerChoice".equals(entries[i].substring(1, j)))
                                        jobManagerChoice.setValue(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerQueue".equals(entries[i].substring(1, j)))
                                        jobManagerQueue.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("jobManagerArgs".equals(entries[i].substring(1, j)))
                                        jobManagerArgs.setText(entries[i].substring(j + 1, entries[i].length()));

                                    //snp_caller_options_pane
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

                                    //filter_options_pane

                                    if ("optionsOutputMatrix".equals(entries[i].substring(1, j))) {
                                        if (entries[i].substring(j + 1, entries[i].length()).equals("True"))
                                            optionsOutputMatrix.setSelected(true);
                                        else
                                            optionsOutputMatrix.setSelected(false);
                                    } else if ("optionsSkip".equals(entries[i].substring(1, j))) {
                                        if (entries[i].substring(j + 1, entries[i].length()).equals("True"))
                                            optionsSkip.setSelected(true);
                                        else
                                            optionsSkip.setSelected(false);
                                    }

                                    // inputs_pane
                                    String genomes = "";

                                    if ("inputPath".equals(entries[i].substring(1, j)))
                                        inputPath.setText(entries[i].substring(j + 1, entries[i].length()));
                                    /*else if ("inputGenomes".equals(entries[i].substring(1, j))) {
                                        genomes = entries[i].substring(j + 1, entries[i].length());
                                        String delims2 = "[[]]";
                                        String genomesEntries[] = genomes.split(delims);

                                        for (int w = 0; w < genomesEntries.length; w++) {
                                            data.add(genomesEntries[w].substring(1, genomesEntries[w].length()));
                                        }
                                        inputGenomes.setItems(data);

                                    }*/
                                    //inputGenomes.setItems(entries[i].substring(j + 1, entries[i].length()));
                                    // else if ("inputRead".equals(entries[i].substring(1, j)))
                                    // inputRead.setText(entries[i].substring(j + 1, entries[i].length()));
                                    // else if ("inputSAM".equals(entries[i].substring(1, j)))
                                    // inputSAM.setText(entries[i].substring(j + 1, entries[i].length()));
                                    //else if ("inputVCF".equals(entries[i].substring(1, j)))
                                    // inputVCF.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputNUCMER".equals(entries[i].substring(1, j)))
                                        inputNUCMER.setText(entries[i].substring(j + 1, entries[i].length()));
                                    else if ("inputDelta".equals(entries[i].substring(1, j)))
                                        inputDelta.setText(entries[i].substring(j + 1, entries[i].length()));
                                }
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText("");
                                alert.setContentText("Your template was loaded successfully");
                                alert.showAndWait();
                            }
                        }
                        catch (IOException w) {
                            w.printStackTrace();
                        }
                    }
                });
    }

    /*
        saving the file into specified location
     */
    private void saveTempFile(String content,File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("");
            alert.setContentText("Your template was saved successfully");
            alert.showAndWait();
        }
        catch (IOException exception)
        {
            System.out.println("Error processing file: " + exception);
        }
    }

}