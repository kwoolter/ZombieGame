/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

import java.util.Random;

/**
 *
 * @author JaneW
 */
public class D2Dice {
    
    private int iNumberOfDice;
    private int iNumberOfDiceSides;
    
    public D2Dice(D2Dice diceCopy) {
        this.iNumberOfDice = diceCopy.getNumberOfDice();
        this.iNumberOfDiceSides = diceCopy.getNumberOfDiceSides();
    }
    
    public D2Dice(int iNewNumberOfDice, int iNewNumberOfDiceSides) {
        iNumberOfDice = iNewNumberOfDice;
        iNumberOfDiceSides = iNewNumberOfDiceSides; 
    }
    
    public int getMax() {
        return iNumberOfDice * iNumberOfDiceSides;
    }
    
    public int getMin() {
        return iNumberOfDice;
    }
    
    public int roll() {
        return this.roll(0);
    }
    public int roll(int iAdd) {
        int iResult = 0;
        Random randomGenerator = new Random();
        
        for(int i=0;i<this.iNumberOfDice;i++) {
            int iRoll = randomGenerator.nextInt(this.iNumberOfDiceSides) +1;
            iResult += iRoll;
        }
        
        iResult += iAdd; 
        
        if(iResult>iNumberOfDice*iNumberOfDiceSides) {
            iResult = iNumberOfDice*iNumberOfDiceSides;
        }
        
        return iResult;        
    }

    static public int roll(int iNumberOfDice, int iNumberOfDiceSides, int iAdd) {
        int iResult = 0;
        Random randomGenerator = new Random();
        
        for(int i=0;i<iNumberOfDice;i++) {
            int iRoll = randomGenerator.nextInt(iNumberOfDiceSides) +1;
            iResult += iRoll;
        }
        
        iResult += iAdd;
        
        if(iResult>iNumberOfDice*iNumberOfDiceSides) {
            iResult = iNumberOfDice*iNumberOfDiceSides;
        }
        
        return iResult;
    }
    
    public int getNumberOfDice() {
        return iNumberOfDice;
    }
    
    public int getNumberOfDiceSides() {
        return iNumberOfDiceSides;
    }
    
    @Override public String toString() {
        return getNumberOfDice()+ "d" + getNumberOfDiceSides();
    }
        
    
    
}


