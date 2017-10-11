package edu.byu.cs.team18.tickettoride.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GameInfo {

    private String gameID;
    private int numPlayers;
    private String gameName;
    private ArrayList<Player> playerList;
    private ArrayList<String> playerNames = new ArrayList<>();
    private boolean hasStarted = false;
    private boolean maxPlayers=false;

    //CONSTRUCTOR-----------------------------------------------------------------------------------

    public GameInfo(String gameName, List<Player> playerList) {
        this.gameName = gameName;
        this.gameID = UUID.randomUUID().toString();
        this.playerList=(ArrayList<Player>)playerList;
        numPlayers=this.playerList.size();
        for(int i=0; i<numPlayers; i++)
        {
            playerNames.add(this.playerList.get(i).getPlayerName());
        }
    }

    //METHODS---------------------------------------------------------------------------------------
    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }
    public boolean hasStarted(){
        return hasStarted;
    }
    public String getGameID(){ return gameID;};
    public String getGameName(){return gameName;}

    public boolean hasMaxPlayers()
    {
        return maxPlayers;
    }
}
