/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author Perlt
 */
public class CountDown extends Task<Integer> {

    private TicTacToe tic;
    private int count = 3;
    private Label label;

    public CountDown(TicTacToe tic) {
        this.tic = tic;
        this.label = new Label();
    }

    @Override
    protected Integer call() throws Exception {
        try {
            Platform.runLater(() -> {
                label.setText("Closeing program in " + String.valueOf(count) + " sec");
                tic.getGrid().add(label, 1, 5);
            });
            Thread.sleep(1000);
            count--;
            Platform.runLater(() -> {
                label.setText("Closeing program in " + String.valueOf(count) + " sec");
            });
            Thread.sleep(1000);
            count--;
            Platform.runLater(() -> {
               label.setText("Closeing program in " + String.valueOf(count) + " sec");
            });
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        tic.close();
        return 1;
    }

}
