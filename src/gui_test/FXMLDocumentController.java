package gui_test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import worldofzuul2.Game;

/**
 *
 * @author Simon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private Game game;
    @FXML
    private AnchorPane Load;
    @FXML
    private Button button1;
    @FXML
    private TextArea area1;
    @FXML
    private Button accuse;
    @FXML
    private Button fight;
    @FXML
    private Button use;
    @FXML
    private Button loot;
    @FXML
    private Button search;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button2;
    @FXML
    private TextArea area2;
    @FXML
    private TextField writable;
    @FXML
    private Button talk;
    @FXML
    private Button load;
    @FXML
    private Button save;
    @FXML
    private Button showNotes;
    
    /**
    * ActionEvent handler to handle a click on button D in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param d String
    */
    
    @FXML
    private void handleButtonD(ActionEvent event) {
        game.convertToCommand("d");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
  /**
    * ActionEvent handler to handle a click on button W in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param w String
    */
    @FXML
    private void handleButtonW(ActionEvent event) {
        game.convertToCommand("w");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    /**
    * ActionEvent handler to handle a click on button A in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param a String
    */
    @FXML
    private void handleButtonA(ActionEvent event) {
        game.convertToCommand("a");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    /**
    * ActionEvent handler to handle a click on button S in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param s String
    */
    @FXML
    private void handleButtonS(ActionEvent event) {
        game.convertToCommand("s");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
     /**
    * ActionEvent handler to handle a click on button Search in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param search String
    */
    
    @FXML
    private void handleButtonSearch(ActionEvent event) {
        game.convertToCommand("search");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
     /**
    * ActionEvent handler to handle a click on button Accuse in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param accuse String
    */
    @FXML
    private void handleButtonAccuse(ActionEvent event) {
        game.convertToCommand("accuse");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
        if(game.hasLost()) {
            area2.setText(game.getInformation());
            game.printHighscore();
            area1.setText(game.getInformation() + "\nYou cannot set a highscore, because you've lost.");    
            game = null;
        }
    }
     /**
    * ActionEvent handler to handle a click on button Talk in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param talk String
    */
    @FXML
    private void handleButtonTalk(ActionEvent event) {
        game.convertToCommand("talk");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
     /**
    * ActionEvent handler to handle a click on button Fight in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param Fight String
    * If statement to check if game is won or lost after fight
    */
    @FXML
    private void handleButtonFight(ActionEvent event) {
        game.convertToCommand("fight");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
        if(game.hasWon() || game.hasLost()) {
            if(game.hasWon()) {
                area2.setText(game.getInformation());
                String s = game.updateHighscore();
                game.printHighscore();
                area1.setText(s + "\n" + game.getInformation());    
            }
            if(game.hasLost()) {
                area2.setText(game.getInformation());
                game.printHighscore();
                area1.setText(game.getInformation() + "\nYou cannot set a highscore, because you've lost.");    
            }
            game = null;
        }

    }
     /**
    * ActionEvent handler to handle a click on button Load in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param load String
    */
    @FXML
    private void handleButtonLoad(ActionEvent event) {
        game.convertToCommand("load");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
     /**
    * ActionEvent handler to handle a click on button Save in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param save String
    */
    @FXML
    private void handleButtonSave(ActionEvent event) {
        game.convertToCommand("save");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    private void endGame() {
// exit string fra Game, brug den fra play()
    }
    
    /**
    * ActionEvent handler to handle a click on button Loot in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param loot String
    */
    @FXML
    private void handleButtonLoot(ActionEvent event) {
        game.convertToCommand("loot");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    /**
    * ActionEvent handler to handle a click on button Use in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param use String + writable Text from TextField
    */
    @FXML
    private void handleButtonUse(ActionEvent event) {
            game.convertToCommand("use " + writable.getText());
            area2.setText(game.getInformation());
            area1.setText(game.getRoom());
    }
    /**
    * ActionEvent handler to handle a click on button Show Notes in the GUI
    * @param event
    * parses the string to the convertToCommand method in Game Class 
    * @param show String
    */
    @FXML
    private void handleButtonShowNotes(ActionEvent event) {
        game.convertToCommand("show");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    @FXML
    private void handleButtonHelp(ActionEvent event) {
        game.convertToCommand("help");
        area2.setText(game.getInformation());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = new Game();
        area1.setText(game.getRoom());
        area2.setText(game.getInformation());

        
    }
}
