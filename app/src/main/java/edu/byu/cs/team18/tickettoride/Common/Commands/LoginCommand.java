package edu.byu.cs.team18.tickettoride.Common.Commands;

/**
 * Created by abram on 10/2/2017.
 */

public class LoginCommand implements ICommand{

    private String username;
    private String password;
    private String suffix = "Login";

    public String getSuffix() {
        return suffix;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginCommand(String un, String pw)
    {
        username = un;
        password = pw;
    }


    public void execute(){}
}
