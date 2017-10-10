package edu.byu.cs.team18.tickettoride.Common.Commands;

//import com.example.abram.phase1main.Results.CommandResult;

/**
 * Created by abram on 9/18/2017.
 */

public class Command {
    protected String type;
    protected String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Command(String ty, String t)
    {
        type = ty;
        text = t;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Command(){}


    /*public CommandResult execute()
    {
        return null;
    }*/
}
