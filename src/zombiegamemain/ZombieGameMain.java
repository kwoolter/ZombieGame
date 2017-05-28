/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author JaneW
 */
public class ZombieGameMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ZGFrame frame = new ZGFrame();
        frame.setVisible(true);



    }

    static private int getUserChoice(String sQuestion, int iMin, int iMax) {

        int selection = 0;

        boolean valid = false;
        while (valid == false) {
            try {

                System.out.print(sQuestion);
                Scanner input = new Scanner(System.in);

                selection = input.nextInt();

                if (selection < iMin || selection > iMax) {
                    System.out.println("Number is incorrect. Try again!");

                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("You didn't type in a number. Try again!");
            }
        }
        return selection;
    }

    private void textGame() {

        ZGGame game = new ZGGame();

        game.addPlayer(new ZGPlayer("Keith"));
        game.addPlayer(new ZGPlayer("Oliver"));

        int iWinner = 0, iMaxScore = 0, iRound = 1;

        while (game.isWinner() == false) {

            System.out.println("\nRound " + iRound);
            System.out.println("----------");

            for (int iPlayer = 0; iPlayer < game.getNumberPlayers(); iPlayer++) {

                ZGPlayer currentPlayer = game.getPlayer(iPlayer);

                System.out.println("\nIt's player " + currentPlayer.getName() + "'s turn...\n");

                ZGPlayerTurn turn = new ZGPlayerTurn(currentPlayer);

                while (turn.getTurnState() != ZGPlayerTurn.ZGTurnState.DEAD && turn.getTurnState() != ZGPlayerTurn.ZGTurnState.END) {
                    try {
                        turn.getDice();
                        turn.rollDice();
                        turn.analyze();
                        turn.print();
                    } catch (ZGException e) {
                        System.out.println(e);
                        turn.endTurn();
                    }

                    if (turn.getTurnState() == ZGPlayerTurn.ZGTurnState.CHOOSE) {

                        int iChoice = getUserChoice("1. Continue,  2. Bank :", 1, 2);

                        if (iChoice == 2) {
                            turn.endTurn();
                        }
                    }
                }

                System.out.println(currentPlayer.getName() + " scored " + turn.getTurnScore() + " in this turn.  Total score=" + currentPlayer.getScore());

                if (turn.getTurnScore() > iMaxScore) {
                    iWinner = iPlayer;
                    iMaxScore = turn.getTurnScore();
                }
            }

            System.out.println("\nEnd of Round " + iRound);
            System.out.println("Leader Board");
            game.getLeaderBoard();
            iRound++;
        }

        System.out.println("\n\nG A M E   O V E R\n");
        //System.out.println("\nPlayer " + game.getPlayer(iWinner).getName() + " wins with a score of " + game.getPlayer(iWinner).getScore());
    }
}
