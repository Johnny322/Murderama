/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private TextField writable2;
    
    @FXML
    private void handleButtonD(ActionEvent event) {
        game.convertToCommand("d");
        area2.setText(game.getInformation());

        area1.setText(game.getRoom());

    }
    
    @FXML
    private void handleButtonW(ActionEvent event) {
        game.convertToCommand("w");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
    @FXML
    private void handleButtonA(ActionEvent event) {
        game.convertToCommand("a");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
    @FXML
    private void handleButtonS(ActionEvent event) {
        game.convertToCommand("s");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
    
    
    @FXML
    private void handleButtonSearch(ActionEvent event) {
        game.convertToCommand("search");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
    @FXML
    private void handleButtonAccuse(ActionEvent event) {
        game.convertToCommand("accuse");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    @FXML
    private void handleButtonTalk(ActionEvent event) {
        game.convertToCommand("talk");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    @FXML
    private void handleButtonFight(ActionEvent event) {
        game.convertToCommand("fight");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
        if(game.hasWon() || game.hasLost()) {
            if(game.hasWon()) {
                area2.setText("You have won the game!");
                game.printHighscore();
                area1.setText(game.getInformation() + "\n" + game.updateHighscore() + "\n");    
            }
            if(game.hasLost()) {
                area2.setText("You have lost the game!");
                game.printHighscore();
                area1.setText("You cannot set a highscore, because you've lost");    
            }
            game = null;
        }

    }
    
    @FXML
    private void handleButtonLoad(ActionEvent event) {
        game.convertToCommand("load");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    @FXML
    private void handleButtonSave(ActionEvent event) {
        game.convertToCommand("save");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
    }
    
    private void endGame() {
// exit string fra Game, brug den fra play()
    }
    
    @FXML
    private void handleButtonLoot(ActionEvent event) {
        game.convertToCommand("loot");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());

    }
    
    @FXML
    private void handleButtonUse(ActionEvent event) {
        if(writable.getText() != null) {
            game.convertToCommand("use " + writable.getText());
            area2.setText(game.getInformation());
            area1.setText(game.getRoom());
        } else {
            area2.setText("What item?");
        }
    }
    
    @FXML
    private void handleButtonShowNotes(ActionEvent event) {
        game.convertToCommand("show");
        area2.setText(game.getInformation());
        area1.setText(game.getRoom());
        if(game.hasWon()) {
            area2.setText("You");
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = new Game();
        area1.setText(game.getRoom());
        
        
    }
}
