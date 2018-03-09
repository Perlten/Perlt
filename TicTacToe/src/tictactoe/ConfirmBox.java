package tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Perlt
 */
public class ConfirmBox {
    
        private boolean answer;
    
    public boolean confirm(String title, String message){
        
        Stage stage = new Stage();
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        
        Label label = new Label(message);
        Button yesButton = new Button("Restart");
        Button noButton = new Button("Quit");

        
        yesButton.setOnAction(e -> {
            answer = true;
            stage.close();
        });
        
        noButton.setOnAction(e -> {
            answer = false;
            stage.close();
        });
        
        HBox layout = new HBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 400, 300);
        String css = this.getClass().getResource("Viper.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.showAndWait();
        
        return answer;
    }
}
