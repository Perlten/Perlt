package tictactoe;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountDown implements Runnable {

    private  TicTacToe tic;
    private int seconds = 3;

    public CountDown(TicTacToe tic) {
        this.tic = tic;
    }

    @Override
    public void run() {
        Label label = new Label();
        int count = seconds;
        Platform.runLater(() -> {
            tic.getGrid().add(label, 1, 5);
        });
        for (int i = 0; i < count; i++) {
            Platform.runLater(() -> {
                label.setText("Closeing program in " + String.valueOf(seconds) + " sec");
                seconds--;
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Time");
        tic.close();
    }
}
