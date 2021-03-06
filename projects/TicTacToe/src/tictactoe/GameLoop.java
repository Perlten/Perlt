package tictactoe;

import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class GameLoop implements Runnable{

    private List<Button> buttonList;
    private TicTacToe ttt;

    public GameLoop(List<Button> buttonList, TicTacToe ttt) {
        this.buttonList = buttonList;
        this.ttt = ttt;
    }

   @Override
    public void run() {
        boolean tie = false;
        while (true) {
            System.out.print("");
            if ( //Hori
                    (compare(0, 1) && compare(1, 2))
                    || (compare(3, 4) && compare(4, 5))
                    || (compare(6, 7) && compare(7, 8))) {
                break;
            } else if ( //vertical
                    (compare(0, 3) && compare(3, 6))
                    || (compare(1, 4) && compare(4, 7))
                    || (compare(2, 5) && compare(5, 8))) {
                break;
            } else if ( //cross
                    (compare(0, 4) && compare(4, 8))
                    || (compare(2, 4) && compare(4, 6))) {
                break;
            }
            boolean full = true;
            for(Button x : buttonList){
                if(!x.getText().equals("X") && !x.getText().equals("O")){
                    full = false;
                    break;
                }
            }
            if(full){
                tie = true;
                break;
            }
        }
        printWinner(tie);
    }

    public void printWinner(boolean tie) {
        Platform.runLater(() -> {
            if(tie){
                ttt.getPlayerWin().setText("Its a tie");
            }else if (ttt.isPlayer1Turn()) {
                ttt.getPlayerWin().setText("Player O wins");
            } else {
                ttt.getPlayerWin().setText("Player X wins");
            }
            Thread th = new Thread(new CountDown(ttt));
            th.setDaemon(true);
            th.start();
        });
    }
  
    private boolean compare(int index1, int index2) {
        return buttonList.get(index1).getText().equals(buttonList.get(index2).getText());
    }

    
}
