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
public class ZGDice {

    private String sColor, sLastRoll;
    private ArrayList<String> arlMap;
    private int iBrains, iSteps, iShots;
    private D2Dice dice;
    

    public ZGDice(String sColor, int iBrains, int iSteps, int iShots) {

        this.sColor = sColor;
        this.iBrains = iBrains;
        this.iSteps = iSteps;
        this.iShots = iShots;
        this.sLastRoll = "";

        initialise();

    }

    public ZGDice(ZGDice copy) {
        this.sColor = copy.sColor;
        this.iBrains = copy.iBrains;
        this.iSteps = copy.iSteps;
        this.iShots = copy.iShots;
        this.sLastRoll = copy.sLastRoll;

        initialise();

    }

    private void initialise() {
        this.arlMap = new ArrayList();
        dice = new D2Dice(1, iBrains + iSteps + iSteps);

        for (int i = 0; i <= iBrains; i++) {
            arlMap.add("Brains");
        }

        for (int i = 0; i <= iSteps; i++) {
            arlMap.add("Steps");
        }

        for (int i = 0; i <= iShots; i++) {
            arlMap.add("Shots");
        }
    }

    public String getLastRoll() {
        return sLastRoll;
    }

    public String getColor() {
        return sColor;
    }
    
    
    
    

    @Override
    public String toString() {
        return sColor + " dice: " + sLastRoll;
    }
    
    

    public String roll() {

        int iRoll = dice.roll();
        this.sLastRoll = arlMap.get(iRoll);

        return sLastRoll;

    }
}
