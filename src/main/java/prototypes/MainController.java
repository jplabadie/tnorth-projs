package prototypes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private TreeView<File> mainFileBrowserTree;
    @FXML
    private TabPane jobTabPane;
    @FXML
    private MenuItem createNewJob;

    /**
     *
     * @param fxmlFileLocation the location of the required fxml layout
     * @param resources the ResourceBundle which stores a desired saved or default state for the scene
     */
    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources){

        initMainFileBrowserTree();
        initCreateNewJobHandler();

        DragResizerController.makeResizable(mainFileBrowserTree);
    }
    /**
     *  On startup, creates a Handler which monitors the “Create New Job”
     button in the main menu. When the “Create New Job” button in the
     main menu is pressed, this Handler will add a new Tab to the
     JobTabPane with its own Handler.
     */
    private void initCreateNewJobHandler() {
        createNewJob.setOnAction(
                new EventHandler<ActionEvent>() {
                    //@Override
                    public void handle(final ActionEvent e) {
                        try {
                            AnchorPane new_job_pane = FXMLLoader.load(getClass().getClassLoader().getResource("NASPDefaultJobPane.fxml"));
                            Tab new_tab = new Tab("New Tab");
                            new_tab.setContent(new_job_pane);
                            jobTabPane.getTabs().add(new_tab);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
    }

    /**
     * On startup, creates a Tree which visualizes the user’s file
     * system, and displays this tree in the file browser pane. This
     * Tree allows users to drag and drop their selected files or
     * directories into containers in a JobTabPane.
     */
    private void initMainFileBrowserTree() {
        File[] roots = File.listRoots();    // Get a list of all drives attached

        TreeItem<File> dummyRoot = new TreeItem<File>();    // This dummy node is used to that we have multiple drives as roots
        // Iterate over the list of drives and add them and their children as children to the dummy node
        for (int i = 0; i < roots.length; i++) {
            dummyRoot.getChildren().addAll(createNode(roots[i]));
        }

        mainFileBrowserTree.setEditable(true);
        //TreeItem<File> root = createNode(new File("/"));
        //root.setExpanded(true);

        mainFileBrowserTree.setCellFactory(new Callback<TreeView<File>,TreeCell<File>>(){
            @Override
            public TreeCell<File> call(TreeView<File> param) {
                return new DraggableTreeCell<File>();
            }
        });
        mainFileBrowserTree.setRoot(dummyRoot);     // Set dummy node as root of the TreeView
        mainFileBrowserTree.setShowRoot(false);     // Hide the root so the drives appear as roots
        //mainFileBrowserTree.setRoot(root);


    }

    /**
     * This method creates a TreeItem to represent the given File. It does this
     * by overriding the TreeItem.getChildren() and TreeItem.isLeaf() methods
     * anonymously, but this could be better abstracted by creating a
     * 'FileTreeItem' subclass of TreeItem. However, this is left as an exercise
     * for the reader.
     *
     * @param f the root File from which a tree will be created
     * @return the Tree of Files and Directories
     */
    private TreeItem<File> createNode(final File f) {
        return new TreeItem<File>(f) {
            // We cache whether the File is a leaf or not. A File is a leaf if
            // it is not a directory and does not have any files contained within
            // it. We cache this as isLeaf() is called often, and doing the
            // actual check on File is expensive.
            private boolean isLeaf;

            // We do the children and leaf testing only once, and then set these
            // booleans to false so that we do not check again during this
            // run. A more complete implementation may need to handle more
            // dynamic file system situations (such as where a folder has files
            // added after the TreeView is shown). Again, this is left as an
            // exercise for the reader.
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    // First getChildren() call, so we actually go off and
                    // determine the children of the File contained in this TreeItem.
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> tree_item) {
                File f = tree_item.getValue();

                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();

                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                        for (File child_file : files) {
                            children.add(createNode(child_file));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }

}
