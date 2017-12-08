package worldofzuul;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.stage.Stage;

public final class Gui extends Application
{

    
    public Gui() {
    }
    Button button;

    //Skal ind i main launch();

    @Override
    public void start(Stage stage) 
    {
        stage.setTitle("Welcome To Murderama");
        
        button = new Button();
        button.setText("Do something");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 250, 250);
        stage.setScene(scene);
        stage.show();


    }


}