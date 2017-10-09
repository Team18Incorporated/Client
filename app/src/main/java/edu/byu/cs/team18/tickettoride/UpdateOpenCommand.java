package com.example.abram.phase1main.Commands;

import com.example.abram.phase1main.ModelClasses.AuthToken;
import com.example.abram.phase1main.Results.CommandResult;

/**
 * Created by abram on 10/2/2017.
 */

public class UpdateOpenCommand implements ICommand{


    private String suffix = "UpdateOpen";

    @Override
    public String getSuffix() {
        return suffix;
    }


    public UpdateOpenCommand()
    {

    }

}
