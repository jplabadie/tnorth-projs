package ctrls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 */
public class UserSettingsController  implements Initializable {

    @FXML
    private TextField usrnamefield;
    @FXML
    private TextField pwdfield;
    @FXML
    private TextField serverurlfield;

    /**
     *
     * @param fxmlFileLocation the location of the required fxml layout
     * @param resources the ResourceBundle which stores a desired saved or default state for the scene
     */
    @Override
    public void initialize(final URL fxmlFileLocation, ResourceBundle resources){

    }
}
