/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiegamemain;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author JaneW
 */
public class ZGGame {

    private ArrayList<ZGPlayer> players;
    private int iWinningScore, iCurrentPlayer, iTurns;
    private ZGGameState currentState;
    public enum ZGGameState { START, PLAYING, END; }

    public ZGGame() {
        players = new ArrayList();
        iWinningScore = 13;
        iCurrentPlayer = 0;
        iTurns = 1;
        currentState = ZGGameState.START;
    }

    public int getNumberPlayers() {
        return players.size();
    }

    public void addPlayer(ZGPlayer newPlayer) {
        players.add(newPlayer);
    }

    public ZGPlayer getPlayer(int iPlayer) {
        return players.get(iPlayer);
    }

    public ZGPlayer getCurrentPlayer() {
        return players.get(iCurrentPlayer);
    }

    public ZGPlayer getNextPlayer() {
        iCurrentPlayer++;

        if (iCurrentPlayer >= players.size()) {
            iCurrentPlayer = 0;
            ++iTurns;
            if(isWinner() == true) {
                currentState = ZGGameState.END;
            }
        }

        return players.get(iCurrentPlayer);
    }

    public ZGGameState getGameState() {
        return currentState;
    }

    public int getTurns() {
        return iTurns;
    }
    
    
    
    public boolean isGameOver() {
        return (currentState == ZGGameState.END);
    }
    
    public void startGame() {
        currentState = ZGGameState.PLAYING;
        
    }
    

    public boolean isWinner() {
        boolean bWinner = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getScore() >= iWinningScore) {
                bWinner = true;
            }
        }

        return bWinner;
    }

    public ArrayList<ZGPlayer> getLeaderBoard() {

        ArrayList<ZGPlayer> listLeaderBoard = new ArrayList(players);

        Collections.sort(listLeaderBoard);

        for (int i = 0; i < listLeaderBoard.size(); i++) {
            ZGPlayer selected = listLeaderBoard.get(i);
            System.out.println(i + 1 + "." + selected.getName() + " : " + selected.getScore());

        }

        return listLeaderBoard;
    }
}
