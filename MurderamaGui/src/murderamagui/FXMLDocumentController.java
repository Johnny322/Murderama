/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murderamagui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;

/**
 *
 * @author jeanjohnsen
 */
public class FXMLDocumentController implements Initializable {
    
  @Override
public void initialize(URL url, ResourceBundle resourceBundle) {
}

@FXML
public void append(int i) {
    textArea.appendText(String.valueOf((char) i));
}

@FXML
private void Header(){
    header.setText("MURDERAMA");
}

@FXML
private void Menu() {
      MenuBar menuBar;
      menuBar = this.menu;
}


@FXML
private Label header;
private TextArea textArea;
private MenuBar menu;
}  
   
