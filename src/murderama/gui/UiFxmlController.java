/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murderama.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jeanjohnsen
 */
public class UiFxmlController implements Initializable {

    @FXML
    private MenuButton menuButtonBar;
    @FXML
    private AnchorPane textOutputField;
    @FXML
    private AnchorPane mapPane;
    @FXML
    private AnchorPane gamePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
