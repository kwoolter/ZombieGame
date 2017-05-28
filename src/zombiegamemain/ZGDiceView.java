/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author JaneW
 */
public class ZGDiceView {

    private ZGDice dice;
    private Image image;
    private Color diceColour;
    private static int viewHeight = 60, viewWidth = 60;

    public ZGDiceView(ZGDice dice) {
        this.dice = dice;

        switch (dice.getColor()) {
            case "Red":
                diceColour = Color.RED;
                break;
            case "Yellow":
                diceColour = Color.YELLOW;
                break;
            case "Green":
                diceColour = Color.GREEN;
                break;
            default:
                diceColour = Color.MAGENTA;
        }
    }

    public static int getViewHeight() {
        return viewHeight;
    }

    public static void setViewHeight(int viewHeight) {
        ZGDiceView.viewHeight = viewHeight;
    }

    public static int getViewWidth() {
        return viewWidth;
    }

    public static void setViewWidth(int viewWidth) {
        ZGDiceView.viewWidth = viewWidth;
    }

    public void draw(Graphics g, int x, int y) {

        try {
            System.out.println(dice.getLastRoll());
            ImageIcon ii = new ImageIcon(this.getClass().getResource("resources/" + dice.getLastRoll() + ".png"));
            this.image = ii.getImage();
            //this.image = ImageIO.read(new File("C:/Users/JaneW/My Development/ZombieGame/src/zombiegamemain/resources/" + dice.getLastRoll() + ".png"));
            //this.image = ImageIO.read(new File("C:/Users/KeithW/My Development/ZombieGameMain/src/zombiegamemain/resources/" + dice.getLastRoll() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.drawImage(image, x, y, ZGDiceView.getViewWidth(), ZGDiceView.getViewHeight(), diceColour, null);

    }
}
