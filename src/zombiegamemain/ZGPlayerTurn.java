/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

import java.util.ArrayList;

/**
 *
 * @author JaneW
 */
public class ZGPlayerTurn {

    private ZGDiceBox box;
    private ZGPlayer playerCurrent;
    private int iRolls;
    private ArrayList<ZGDice> arlShots, arlBrains, arlRoll, arlLastRoll;

    public enum ZGTurnState {

        START, GOTDICE, ROLLED, CHOOSE, DEAD, END;
    }
    private ZGTurnState turnState;

    public ZGPlayerTurn(ZGPlayer playerCurrent) {
        this.playerCurrent = playerCurrent;
        initialize();
    }

    public ArrayList<ZGDice> getArlBrains() {
        return arlBrains;
    }

    public ArrayList<ZGDice> getArlRoll() {
        return arlRoll;
    }

    public ArrayList<ZGDice> getArlShots() {
        return arlShots;
    }

    public ZGPlayer getPlayer() {
        return playerCurrent;
    }

    private void initialize() {
        turnState = ZGTurnState.START;
        iRolls = 0;
        arlShots = new ArrayList();
        arlBrains = new ArrayList();
        arlRoll = new ArrayList();
        box = new ZGDiceBox();

        ZGDice diceRed = new ZGDice("Red", 1, 2, 3);
        ZGDice diceYellow = new ZGDice("Yellow", 2, 2, 2);
        ZGDice diceGreen = new ZGDice("Green", 3, 2, 1);

        box.addDice(diceRed, 3);
        box.addDice(diceYellow, 4);
        box.addDice(diceGreen, 6);


    }

    public void getDice() throws ZGException {
        for (int i = arlRoll.size(); i < 3; i++) {

            if (box.isEmpty() == false) {
                arlRoll.add(box.takeDice());
            }
        }
        //System.out.println("Dice=" + arlRoll);

        turnState = ZGTurnState.GOTDICE;
    }

    public void rollDice() throws ZGException {

        if (this.getTurnState() != ZGTurnState.GOTDICE) {
            throw new ZGException("You haven't got the dice from the box.");
        }

        this.iRolls++;
        for (int i = 0; i < arlRoll.size(); i++) {
            arlRoll.get(i).roll();
            System.out.println(i + 1 + ". " + arlRoll.get(i));
        }

        // Take a copy of the roll
        arlLastRoll = new ArrayList(arlRoll);

        turnState = ZGTurnState.ROLLED;

    }

    public ArrayList<ZGDice> getDiceRoll() throws ZGException {

        if (arlLastRoll == null) {
            throw new ZGException("No dice rolled.");
        }

        return arlLastRoll;

    }

    public ArrayList<ZGDice> getDiceShots() {

        return new ArrayList(arlShots);

    }

    public ArrayList<ZGDice> getDiceBrains() {

        return new ArrayList(arlBrains);

    }

    public void analyze() throws ZGException {

        if (this.getTurnState() != ZGTurnState.ROLLED) {
            throw new ZGException("The dice haven't been rolled.");
        }

        int i = 0;

        while (i < arlRoll.size()) {

            String sType;

            sType = arlRoll.get(i).getLastRoll();

            //System.out.println("Dice=" + sType);

            if (sType.equals("Brains")) {
                arlBrains.add(arlRoll.remove(i));
            } else if (sType.equals("Shots")) {
                arlShots.add(arlRoll.remove(i));
            } else {
                ++i;
            }

        }
        // If you have been shot 3 times you are dead
        if (arlShots.size() >= 3) {
            System.out.println("*** You got shot " + arlShots.size() + " times ***");
            this.turnState = ZGTurnState.DEAD;
        } // If there are no dice left to roll and none in the dice box then you have finished
        else if (arlRoll.isEmpty() && box.isEmpty()) {
            //this.playerCurrent.addScore(getTurnScore());
            System.out.println("*** You used up all the dice - CONGRATULATIONS ***");
            this.turnState = ZGTurnState.END;
            playerCurrent.addScore(this.getTurnScore());
        } // Else you need to decide what to do next...
        else {
            System.out.println("*** You need to choose what to do next ***");
            turnState = ZGTurnState.CHOOSE;
        }
    }

    public int getBrains() {
        return arlBrains.size();
    }

    public int getShots() {
        return arlShots.size();
    }

    public int getRolls() {
        return iRolls;
    }

    public int getTurnScore() {

        int iScore;
        if (this.turnState == ZGTurnState.DEAD) {
            iScore = 0;
        } else {
            iScore = arlBrains.size();
        }

        return iScore;
    }

    public ZGTurnState getTurnState() {
        return turnState;
    }

    public void endTurn() {
        this.playerCurrent.addScore(getTurnScore());
        turnState = ZGTurnState.END;
    }

    public void print() {
        System.out.println("Player=" + playerCurrent.getName() + ":" + turnState);
        System.out.println("Score=" + playerCurrent.getScore() + " Rolls=" + getRolls());
        System.out.println("Brains=" + getBrains() + " Shots=" + getShots());

    }
}
