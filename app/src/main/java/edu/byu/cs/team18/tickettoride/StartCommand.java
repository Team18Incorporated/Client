package com.example.abram.phase1main.Commands;

import com.example.abram.phase1main.ModelClasses.AuthToken;
import com.example.abram.phase1main.Results.CommandResult;

/**
 * Created by abram on 10/2/2017.
 */

public class StartCommand implements ICommand{

    private String gameID;
    private String suffix = "Start";

    @Override
    public String getSuffix() {
        return suffix;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public StartCommand(String ID)
    {
        gameID = ID;
    }

}
