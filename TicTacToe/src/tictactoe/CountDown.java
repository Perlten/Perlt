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

    public CountDown(TicTacToe tic) {
        this.tic = tic;
    }

    @Override
    protected Integer call() throws Exception {
        Platform.runLater(() -> {
            Label countDown = new Label("Closeing program in 3 sec");
            tic.getGrid().add(countDown, 1, 5);
        });
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException interrupted) {
            }
        }
        tic.close();
        return 1;
    }

}
