package ctrls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import utils.JobSaveLoadManager;
import utils.DefaultRemoteNetworking;
import xmlbinds.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the JobTab Pane.
 *
 * In future iterations, this controller and perhaps its FXML should be dynamically defined based on XML.
 * This would allow a high degree of modularity.
 */
public class JobTabMainController implements Initializable {

    // Panes and other Containers
    @FXML   private AnchorPane jobConfigTabAnchorPane;
    @FXML   private TitledPane general_settings_pane;
    @FXML   private TitledPane inputs_pane;
    @FXML   private TitledPane aligner_options_pane;
    @FXML   private VBox alignerVbox;
    @FXML   private TitledPane bwaSampTitledPane;
    @FXML   private TitledPane bwaMemTitledPane;
    @FXML   private TitledPane bowTieTitledPane;
    @FXML   private TitledPane novoalignTitledPane;
    @FXML   private TitledPane snapTitledPane;
    @FXML   private TitledPane snp_caller_options_pane;
    @FXML   private TitledPane gatkOptionsPane;
    @FXML   private TitledPane solSnpPane;
    @FXML   private TitledPane varScanPane;
    @FXML   private TitledPane samtoolsPane;
    @FXML   private TitledPane filter_options_pane;

    // JobManagerOptions Fields and Buttons
    @FXML   private TextField jobOutputDir;
    @FXML   private ChoiceBox<String> jobManagerSystem;
    @FXML   private TextField jobManagerQueue;
    @FXML   private TextArea jobManagerArgs;
    @FXML   private Button outputDirButton;

    // Inputs Fields and Buttons
    @FXML   private TextField inputRefFastaPath;
    @FXML   private TextField inputFastaExternalGen;
    @FXML   private ListView<String> inputReadFiles;
    @FXML   private ListView<?> inputSamBamFiles;
    @FXML   private ListView<String> inputVcfFiles;
    @FXML   private CheckBox cbInputAdvNucmer;
    @FXML   private TextField inputNucmerArgs;
    @FXML   private TextField inputDeltaArgs;

    // Aligners Fields and Buttons
    // Aligners Toggle Buttoms
    @FXML   private CheckBox cbAlignersBwaSamp;
    @FXML   private CheckBox cbAlignersBwaMem;
    @FXML   private CheckBox cbAlignersBowtie;
    @FXML   private CheckBox cbAlignersNovoalign;
    @FXML   private CheckBox cbAlignersSnap;
    // Aligners BWA SAMP/SE Fields and Buttons
    @FXML   private CheckBox useAltBwaSampVer;
    @FXML   private TextField altBwaSampPath;
    @FXML   private TextField altBwaSampQueue;
    @FXML   private TextField limitBwaSampMem;
    @FXML   private TextField limitBwaSampCpu;
    @FXML   private TextField limitBwaSampRuntime;
    @FXML   private TextArea bwaSampArgs;
    // Aligners BWA MEM Fields and Buttons
    @FXML   private CheckBox useAltBwaMemVer;
    @FXML   private TextField altBwaMemPath;
    @FXML   private TextField altBwaMemQueue;
    @FXML   private TextField limitBwaMemMem;
    @FXML   private TextField limitBwaMemCpu;
    @FXML   private TextField limitBwaMemRuntime;
    @FXML   private TextArea bwaMemArgs;
    // Aligners Bowtie Fields and Buttons
    @FXML   private CheckBox useAltBowtieVer;
    @FXML   private TextField altBowtiePath;
    @FXML   private TextField altBowtieQueue;
    @FXML   private TextField limitBowtieMem;
    @FXML   private TextField limitBowtieCpu;
    @FXML   private TextField limitBowtieRuntime;
    @FXML   private TextArea bowtieArgs;
    // Aligners Novoalign Fields and Buttons
    @FXML   private CheckBox useAltNovoalignVer;
    @FXML   private TextField altNovoalignPath;
    @FXML   private TextField altNovoalignQueue;
    @FXML   private TextField limitNovoalignMem;
    @FXML   private TextField limitNovoalignCpu;
    @FXML   private TextField limitNovoalignRuntime;
    @FXML   private TextArea novoalignArgs;
    // Aligners SNAP Fields and Buttons
    @FXML   private CheckBox useAltSnapVer;
    @FXML   private TextField altSnapPath;
    @FXML   private TextField altSnapQueue;
    @FXML   private TextField limitSnapMem;
    @FXML   private TextField limitSnapCpu;
    @FXML   private TextField limitSnapRuntime;
    @FXML   private TextArea snapArgs;

    // SNP Callers Buttons and Fields
    // SNP Callers Toggle Buttons
    @FXML   private CheckBox cbSnpCallerGATK;
    @FXML   private CheckBox cbSnpCallerSolSNP;
    @FXML   private CheckBox cbSnpCallerVarScan;
    @FXML   private CheckBox cbSnpCallerSAMTools;
    // SNP Callers GATK Fields and Buttons
    @FXML   private TextField GATKPath;
    @FXML   private TextField GATKArguments;
    @FXML   private TextField GATKQueue;
    @FXML   private TextField GATKMemory;
    @FXML   private TextField GATKCPU;
    @FXML   private TextField GATKRuntime;
    @FXML   private TextArea gatkArgs;
    // SNP Callers Sol Fields and Buttons
    @FXML   private TextField solPath;
    @FXML   private TextField solArguments;
    @FXML   private TextField solQueue;
    @FXML   private TextField solMemory;
    @FXML   private TextField solCPU;
    @FXML   private TextField solRuntime;
    @FXML   private TextArea solArgs;
    // SNP Callers VarScan Fields and Buttons
    @FXML   private TextField varPath;
    @FXML   private TextField varArguments;
    @FXML   private TextField varQueue;
    @FXML   private TextField varMemory;
    @FXML   private TextField varCPU;
    @FXML   private TextField varRuntime;
    @FXML   private TextArea varArgs;
    // SNP Callers SAM Fields and Buttons
    @FXML   private TextField SAMPath;
    @FXML   private TextField SAMArguments;
    @FXML   private TextField SAMQueue;
    @FXML   private TextField SAMMemory;
    @FXML   private TextField SAMCPU;
    @FXML   private TextField SAMRuntime;
    @FXML   private TextArea samArgs;

    // Outputs Fields and Buttons
    @FXML   private CheckBox outputFindDuplicates;
    @FXML   private CheckBox outputSkipDuplicateRegions;
    @FXML   private CheckBox outputMaskLowQualityCalls;

    // Save/Load/Start Buttons
    @FXML   private Button btnStartJob;
    @FXML   private Button btnSaveSettings;
    @FXML   private Button btnLoadSettings;

    private File nasp_xml;

    private ResourceBundle resources;
    /**
     * This is the root Object which represents the Job XML
     */
    private NaspInputData naspData;

    private DirectoryChooser dirChooser = new DirectoryChooser();

    /**
     * Initializes the new JobPane.
     *
     * @param fxmlFileLocation a URL representing the FXML layout to be used for this pane
     * @param resources the ResourceBundle from which to call other resources
     */
    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources) {

        this.resources = resources;

        // The lists of all ListViews, CheckBoxes, and TitledPanes are created to add drag, and toggle functionality iteratively
        TextField[] textFieldArray = {inputFastaExternalGen, jobOutputDir};
        ListView[] listViewArray = {inputReadFiles, inputSamBamFiles, inputSamBamFiles};
        CheckBox[] checkBoxArray = {cbAlignersSnap,cbInputAdvNucmer,cbAlignersNovoalign,cbAlignersBowtie,
                cbAlignersBwaMem,cbAlignersBwaSamp,cbSnpCallerGATK,cbSnpCallerSAMTools,cbSnpCallerSolSNP,
                cbSnpCallerVarScan};
        TitledPane[] checkPaneArray = {bwaSampTitledPane, bwaMemTitledPane, bowTieTitledPane, novoalignTitledPane,
                snapTitledPane, gatkOptionsPane, solSnpPane, varScanPane, samtoolsPane};

        //Initialize Buttons
        initStartJobButton();
        initSaveButton();
        initLoadButton();

        // Drag and drop is initialized for all the fields that need it
        initializeCheckBoxToggle(checkBoxArray, checkPaneArray);
        initializeListViewDrag(listViewArray);
        initializeTextFieldDrag(textFieldArray);
        toggleCheckBoxes();

        // Config file Candidate
        // Define the options for observableArrayList
        ObservableList items =FXCollections.observableArrayList(
                "None", new Separator(), "PBS/TORQUE", "SLURM", "SGE*");
        jobManagerSystem.setItems(items);
        jobManagerSystem.getSelectionModel().select(0);

        //Create a new NaspInputData Object which represents a blank job request
        naspData = new ObjectFactory().createNaspInputDataType();
    }

    /**
     * This method calls the setListDragHandler for every ListView in the given ArrayList
     * @param listFields
     */
    private void initializeListViewDrag (ListView[] listFields) {
        int currentListItemIndex = 0;

        while (currentListItemIndex < listFields.length) {
            //System.out.println("Current Index: " + currentListItemIndex);
            setListDragHandler(listFields[currentListItemIndex]);
            currentListItemIndex++;
        }
    }

    /**
     * Adds Drag-and-Drop functionality to the ListView
     *
     * @param list_view the ListView which will gain DnD
     */
    private void setListDragHandler (ListView list_view) {
        // Get the current items in the ListView passed in
        final ObservableList listContents = list_view.getItems();
        list_view.setOnDragDropped(new EventHandler<DragEvent>() {
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

        list_view.setOnDragOver(new EventHandler<DragEvent>() {
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

    /**
     * This method calls the setTextFieldDragHandler for every textField in the given ArrayList
     * @param textFields an array of all textFields to be initialized
     */
    private void initializeTextFieldDrag(TextField[] textFields) {
        int currentListItemIndex = 0;

        while (currentListItemIndex < textFields.length) {
            setTextFieldDragHandler(textFields[currentListItemIndex]);
            currentListItemIndex++;
        }
    }

    /**
     *
     * @param textField a single TextField that will be initialized with a DnD handler
     */
    private void setTextFieldDragHandler(final TextField textField) {

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
                    String content = db.getString();
                    System.out.println(content);

                    event.acceptTransferModes(TransferMode.ANY);
                    textField.setText(content);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Incorrect Input Format");
                    alert.setHeaderText(null);
                    alert.setContentText("The dragged input is not a file path.");
                    alert.showAndWait();
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    /**
     * Initializes the Save button in the Bottom Menu
     */
    private void initSaveButton(){
        btnSaveSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        saveFormState();
                    }
                });
    }

    /**
     * Initializes the Load button in the Bottom Menu
     */
    private void initLoadButton(){
        btnLoadSettings.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        loadFormState();
                    }
                });
    }

    /**
     * Initializes the Start Job button in the Bottom Menu
     */
    private void initStartJobButton() {
        btnStartJob.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                       // try {
                            saveFormState();
                        File temp = new File(String.valueOf(getClass().getClassLoader().getResource("test/NaspInputExample_Aspen.xml")));
                        DefaultRemoteNetworking.getInstance().upload(temp,"/home/jlabadie");
                        DefaultRemoteNetworking.getInstance().runNaspJob("/home/jlabadie/NaspInputExample_Aspen.xml");
//                            AnchorPane job_monitor_pane = FXMLLoader.load(this.getClass().getClassLoader().getResource("job/NASPJobMonitorPane.fxml"));
//
//                            // Testing only, remove from production builds
//                            AnchorPane test = (AnchorPane)resources.getObject("NASPJobMonitorPane.fxml");
//                            test.setVisible(true);
//
//                            jobConfigTabAnchorPane.getChildren().clear();
//                            jobConfigTabAnchorPane.getChildren().add(job_monitor_pane);

//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
                    }
                });
    }

    /**
     *
     */
    private void saveFormState(){

        ExternalApplications exapps = new ExternalApplications();
        naspData.setExternalApplications(exapps);

        Options opts = new Options();
        naspData.setOptions(opts);

        Files files = new Files();
        naspData.setFiles(files);

        String ref_path = inputRefFastaPath.getText();
        String ref_name = ref_path.split("/([^/.]+)(([^/]*)(.[^/.]+))?$")[0];
        Reference ref = new Reference();
        System.out.println();
        ref.setName(ref_name);
        ref.setPath(ref_path);
        opts.setReference(ref);
        opts.setOutputFolder(jobOutputDir.getText());
        opts.setJobSubmitter(jobManagerSystem.getValue().toString());
        opts.setRunName( "test"+"_"+Calendar.DATE);

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
        bwa_samp.setAdditionalArgs(bwaSampArgs.getText());

        Aligner bwa_mem = new Aligner();
        aligners.add(bwa_mem);
        JobParameters bwa_mem_param = new JobParameters();
        bwa_mem.setJobParameters(bwa_mem_param);
        bwa_mem.setPath(altBwaMemPath.getText());
        bwa_mem_param.setQueue(altBwaMemQueue.getText());
        bwa_mem_param.setMemRequested(limitBwaMemMem.getText());
        bwa_mem_param.setNumCPUs(limitBwaMemCpu.getText());
        bwa_mem_param.setWalltime(limitBwaMemRuntime.getText());
        bwa_mem.setAdditionalArgs(bwaSampArgs.getText());

        Aligner novo = new Aligner();
        aligners.add(novo);
        JobParameters novo_param = new JobParameters();
        novo.setJobParameters(novo_param);
        novo.setPath(altNovoalignPath.getText());
        novo_param.setQueue(altNovoalignQueue.getText());
        novo_param.setMemRequested(limitNovoalignMem.getText());
        novo_param.setNumCPUs(limitNovoalignCpu.getText());
        novo_param.setWalltime(limitNovoalignRuntime.getText());
        novo.setAdditionalArgs(bwaSampArgs.getText());

        Aligner snap = new Aligner();
        aligners.add(snap);
        JobParameters snap_param = new JobParameters();
        snap.setJobParameters(snap_param);
        snap.setPath(altSnapPath.getText());
        snap_param.setQueue(altSnapQueue.getText());
        snap_param.setMemRequested(limitSnapMem.getText());
        snap_param.setNumCPUs(limitSnapCpu.getText());
        snap_param.setWalltime(limitSnapRuntime.getText());
        snap.setAdditionalArgs(bwaSampArgs.getText());

        Aligner bow = new Aligner();
        aligners.add(bow);
        JobParameters bow_param = new JobParameters();
        bow.setJobParameters(bow_param);
        bow.setPath(altBowtiePath.getText());
        bow_param.setQueue(altBowtieQueue.getText());
        bow_param.setMemRequested(limitBowtieMem.getText());
        bow_param.setNumCPUs(limitBowtieCpu.getText());
        bow_param.setWalltime(limitBowtieRuntime.getText());
        bow.setAdditionalArgs(bwaSampArgs.getText());

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

        alignment.setPath(inputFastaExternalGen.getText());
        //read.setPath(inputRead.getItems().get(0).toString());

        final Stage dialogStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Template");
        fileChooser.setInitialDirectory(new File(getClass().getClassLoader().getResource("test/NaspInputExample_Aspen.xml").getFile()).getParentFile());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(dialogStage);

        try {
            JobSaveLoadManager.jaxbObjectToXML(naspData, file.getPath());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successfully Saved");
            alert.setHeaderText("");
            alert.setContentText("Your job was saved successfully.");
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Saving Failed");
            alert.setHeaderText("");
            alert.setContentText("Your job could not be saved!");
            alert.showAndWait();
        }
    }

    /**
     *
     */
    private void loadFormState(){
        final Stage dialogStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Saved Job");
        fileChooser.setInitialDirectory(new File(getClass().getClassLoader().getResource("test/NaspInputExample_Aspen.xml").getFile()).getParentFile());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(dialogStage);

        naspData = JobSaveLoadManager.jaxbXMLToObject(file);

        ExternalApplications exapps = naspData.getExternalApplications();
        Options opts = naspData.getOptions();
        Files files = naspData.getFiles();
        Filters filters = opts.getFilters();

        //Options
        inputRefFastaPath.setText(opts.getReference().getPath());

        // Inputs
        try {
            ObservableList<String> vcf = FXCollections.observableArrayList(files.getVCFFolder().getPath());
            inputVcfFiles.setItems(vcf);
            ObservableList<String> read = FXCollections.observableArrayList(files.getReadFolder().getPath());
            inputReadFiles.setItems(read);
            ObservableList<String> assembly = FXCollections.observableArrayList(files.getAssemblyFolder().getPath());
            inputReadFiles.setItems(assembly);
        }
        catch (Exception e){
            System.out.println("?");
        }
        inputRefFastaPath.setText(opts.getReference().getPath());

        jobOutputDir.setText(opts.getOutputFolder());
        jobManagerSystem.getSelectionModel().select(opts.getJobSubmitter());
        outputFindDuplicates.setSelected(opts.getReference().getFindDups().equals("true"));
        opts.setRunName("test"+"_"+ Calendar.DATE);

        // aligner_options_pane
        List<Aligner> aligners = exapps.getAligner();
        List<SNPCaller> snpcallers = exapps.getSNPCaller();

        for(Aligner aligner : aligners){
            JobParameters job_params = aligner.getJobParameters();
            String name = aligner.getName().toLowerCase();
            String args = aligner.getAdditionalArgs();
            String path = aligner.getPath();

            if(name.contains("sampe")){
                cbAlignersBwaSamp.setSelected(true);
                altBwaSampPath.setText(path);
                altBwaSampQueue.setText(job_params.getQueue());
                limitBwaSampCpu.setText(job_params.getNumCPUs());
                limitBwaSampMem.setText(job_params.getMemRequested());
                limitBwaSampRuntime.setText(job_params.getWalltime());
                bwaSampArgs.setText(args);
            }
            else if(name.contains("mem")){
                cbAlignersBwaMem.setSelected(true);
                altBwaMemPath.setText(path);
                altBwaMemQueue.setText(job_params.getQueue());
                limitBwaMemCpu.setText(job_params.getNumCPUs());
                limitBwaMemMem.setText(job_params.getMemRequested());
                limitBwaMemRuntime.setText(job_params.getWalltime());
                bwaMemArgs.setText(args);
            }
            else if(name.contains("novo")){
                cbAlignersNovoalign.setSelected(true);
                altNovoalignPath.setText(path);
                altNovoalignQueue.setText(job_params.getQueue());
                limitNovoalignCpu.setText(job_params.getNumCPUs());
                limitNovoalignMem.setText(job_params.getMemRequested());
                limitNovoalignRuntime.setText(job_params.getWalltime());
                novoalignArgs.setText(args);
            }
            else if(name.contains("snap")){
                cbAlignersSnap.setSelected(true);
                altSnapPath.setText(path);
                altSnapQueue.setText(job_params.getQueue());
                limitSnapCpu.setText(job_params.getNumCPUs());
                limitSnapMem.setText(job_params.getMemRequested());
                limitSnapRuntime.setText(job_params.getWalltime());
                snapArgs.setText(args);
            }
            else if(name.contains("bow")){
                cbAlignersBowtie.setSelected(true);
                altBowtiePath.setText(path);
                altBowtieQueue.setText(job_params.getQueue());
                limitBowtieCpu.setText(job_params.getNumCPUs());
                limitBowtieMem.setText(job_params.getMemRequested());
                limitBowtieRuntime.setText(job_params.getWalltime());
                bowtieArgs.setText(args);
            }
        }

        for(SNPCaller caller: snpcallers){
            JobParameters job_params = caller.getJobParameters();
            String name = caller.getName().toLowerCase();
            String args = caller.getAdditionalArgs();
            String path = caller.getPath();
            if(name.contains("gatk")){
                cbSnpCallerGATK.setSelected(true);
                GATKPath.setText(path);
                GATKQueue.setText(job_params.getQueue());
                GATKCPU.setText(job_params.getNumCPUs());
                GATKMemory.setText(job_params.getMemRequested());
                GATKRuntime.setText(job_params.getWalltime());
                GATKArguments.setText(args);
            }
            else if(name.contains("sol")){
                cbSnpCallerSolSNP.setSelected(true);
                solPath.setText(path);
                solQueue.setText(job_params.getQueue());
                solCPU.setText(job_params.getNumCPUs());
                solMemory.setText(job_params.getMemRequested());
                solRuntime.setText(job_params.getWalltime());
                solArgs.setText(args);
            }
            else if(name.contains("var")){
                cbSnpCallerVarScan.setSelected(true);
                varPath.setText(path);
                varQueue.setText(job_params.getQueue());
                varCPU.setText(job_params.getNumCPUs());
                varMemory.setText(job_params.getMemRequested());
                varRuntime.setText(job_params.getWalltime());
                varArgs.setText(args);
            }
            else if(name.contains("sam")){
                cbSnpCallerSAMTools.setSelected(true);
                SAMPath.setText(path);
                SAMQueue.setText(job_params.getQueue());
                SAMCPU.setText(job_params.getNumCPUs());
                SAMMemory.setText(job_params.getMemRequested());
                SAMRuntime.setText(job_params.getWalltime());
                SAMArguments.setText(args);
            }
        }

        //output and filter settings

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("");
        alert.setContentText("Your template was successfully loaded.");
        alert.showAndWait();
    }

    /**
     *
     * @param checkArray
     * @param checkPanes
     */
    private void initializeCheckBoxToggle (CheckBox[] checkArray, TitledPane[] checkPanes) {
        for (int i = 0; i < checkArray.length; i++) {
            if (i < checkArray.length - 1) {
                setCheckboxToggle(checkArray[i], checkPanes[i]);
            }
            else {
                TitledPane dummy = new TitledPane();
                setCheckboxToggle(checkArray[i], dummy);
            }
        }
    }

    /**
     *
     * @param checkBox
     * @param checkPane
     */
    private void setCheckboxToggle (final CheckBox checkBox, TitledPane checkPane) {
        //System.out.println("ID: " + checkBox.getId());
        final TitledPane correspondingPane = checkPane;
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (checkBox.isSelected()) {
                    //System.out.println(checkBox.getId() + "Checkbox toggled");
                    if (checkBox.getId().equals("enableAdvNucmerButton")) { 
                        cbInputAdvNucmer.setDisable(false);
                        inputDeltaArgs.setDisable(false);
                    }
                    else {
                        correspondingPane.setDisable(false);
                        correspondingPane.setExpanded(true);
                    }
                }
                else if (!checkBox.isSelected()){
                    if (checkBox.getId().equals("enableAdvNucmerButton")) {
                        cbInputAdvNucmer.setDisable(true);
                        inputDeltaArgs.setDisable(true);
                    }
                    else {
                        correspondingPane.setDisable(true);
                        correspondingPane.setExpanded(false);
                    }
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

    private void toggleCheckBoxes()
    {
        useAltBwaSampVer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (useAltBwaSampVer.isSelected())
                            altBwaSampPath.setDisable(false);
                        else
                            altBwaSampPath.setDisable(true);
                    }
                }
        );

        useAltBwaMemVer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (useAltBwaMemVer.isSelected())
                            altBwaMemPath.setDisable(false);
                        else
                            altBwaMemPath.setDisable(true);
                    }
                }
        );

        useAltBowtieVer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (useAltBowtieVer.isSelected())
                            altBowtiePath.setDisable(false);
                        else
                            altBowtiePath.setDisable(true);
                    }
                }
        );

        useAltNovoalignVer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (useAltNovoalignVer.isSelected())
                            altNovoalignPath.setDisable(false);
                        else
                            altNovoalignPath.setDisable(true);
                    }
                }
        );

        useAltSnapVer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (useAltSnapVer.isSelected())
                            altSnapPath.setDisable(false);
                        else
                            altSnapPath.setDisable(true);

                    }
                }
        );
    }

}