package com.example.abram.phase1main.Commands;

import com.example.abram.phase1main.Results.CommandResult;

/**
 * Created by abram on 10/2/2017.
 */

public class RegisterCommand extends Command{

    private String username;
    private String password;

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

    public RegisterCommand(String un, String pw)
    {
        username = un;
        password = pw;
    }



}
