package tictactoe;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private boolean player1Turn = true;
    private Label playerTurn;
    private GridPane grid;
    private Label playerWin;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tic Tac Toe");

        stage.setOnCloseRequest((event) -> {
            close();
        });

        grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(8);
        grid.setVgap(8);

        List<Button> buttonList = makeGrid();

        playerTurn = new Label("Player turn: X");
        GridPane.setConstraints(playerTurn, 8, 1);

        playerWin = new Label();
        GridPane.setConstraints(playerWin, 1, 3);

        Thread th = new Thread(new GameLoop(buttonList, this));
        th.setDaemon(true);
        th.start();
        
        grid.getChildren().addAll(buttonList);
        grid.getChildren().addAll(playerTurn, playerWin);

        Scene scene = new Scene(grid, 500, 480);

        String css = this.getClass().getResource("Viper.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();

    }

    private List<Button> makeGrid() {
        List<Button> list = new ArrayList<>();
        int yVal = 0;
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                yVal++;
            }
            Button button = new Button(String.valueOf(i));
            button.getStyleClass().add("emptyButton");
            GridPane.setConstraints(button, i % 3, yVal);
            list.add(button);
        }
        for (Button x : list) {
            x.setMinSize(100, 100);
            x.getStyleClass().add("button");
            x.setOnAction(e -> makeMove(x));
        }
        return list;
    }

    private void makeMove(Button button) {
        String player;
        if (player1Turn && (!button.getText().equals("O"))) {
            button.getStyleClass().clear();
            player = "X";
            player1Turn = false;
            playerTurn.setText("Player turn: O");
            button.getStyleClass().add("usedXButton");
            button.setText(player);
        } else if (!button.getText().equals("X")) {
            button.getStyleClass().clear();
            player = "O";
            player1Turn = true;
            playerTurn.setText("Player turn: X");
            button.getStyleClass().add("usedOButton");
            button.setText(player);
            
        }
    }

    public void close() {
        Platform.runLater(() -> {
            ConfirmBox cb = new ConfirmBox();
            boolean answer = cb.confirm("Quit?", "Do you whanna restart or quit the game");
            if (answer) {
                System.out.println("Restarting app!");
                Platform.runLater(() -> new TicTacToe().start(new Stage()));
            } else {
            try{
                Platform.exit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public Label getPlayerWin() {
        return playerWin;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public GridPane getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
