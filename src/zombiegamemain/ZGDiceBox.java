/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

//<editor-fold defaultstate="collapsed" desc="comment">
import java.util.ArrayList;
//</editor-fold>

/**
 *
 * @author JaneW
 */
public class ZGDiceBox {
    
    private ArrayList <ZGDice> arlBox;
    
    public ZGDiceBox() {
        arlBox = new ArrayList();
    }
    
    public void empty() {
        arlBox.clear();
        
    }
    
    public void addDice(ZGDice diceType, int iCount) {
        
        for(int i=0;i<iCount;i++) {
            arlBox.add(new ZGDice(diceType));
        }
        
    }
    
    public ZGDice takeDice() {
        
        D2Dice dice = new D2Dice(1,arlBox.size());
        
        return arlBox.remove(dice.roll()-1);
        
    }
    
    public boolean isEmpty() {
        return (arlBox.isEmpty());
    }

    @Override
    public String toString() {
        return "ZGDiceBox{" + "arlBox=" + arlBox + '}';
    }
    
    void print() {
        for(int i=0;i<arlBox.size();i++) {
            System.out.println((i+1) + ". " + arlBox.get(i));
        }
    }
    
    
    
}
