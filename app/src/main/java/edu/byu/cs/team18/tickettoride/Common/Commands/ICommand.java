package edu.byu.cs.team18.tickettoride.Common.Commands;

/**
 * Created by abram on 9/16/2017.
 */

public interface ICommand {

    public String getSuffix();

    public String getClassName();

    public void execute();

}
