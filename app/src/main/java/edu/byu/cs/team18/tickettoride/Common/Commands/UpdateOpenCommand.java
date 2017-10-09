package edu.byu.cs.team18.tickettoride.Common.Commands;

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
