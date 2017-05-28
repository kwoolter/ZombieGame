/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

/**
 *
 * @author JaneW
 */
public class ZGPlayer implements Comparable<ZGPlayer> {

    private String sName;
    private int iScore;

    public ZGPlayer(String sName) {
        this.sName = sName;
        this.iScore = 0;
    }

    public String getName() {
        return sName;
    }

    public int getScore() {
        return iScore;
    }

    public int addScore(int iAdd) {
        this.iScore += iAdd;

        return this.iScore;
    }

    @Override
    public String toString() {
        return "ZGPlayer{" + "sName=" + sName + ", iScore=" + iScore + '}';
    }

    @Override
    public int compareTo(ZGPlayer o) {

        return (o.getScore() - this.getScore());

    }
}
