package edu.byu.cs.team18.tickettoride.Common;



public class User {

    private AuthToken authToken;
    private String username;

    public AuthToken getAuthToken(){
        return authToken;
    }
    public void setAuthToken(AuthToken authIn){ authToken = authIn;}
    public String getUsername(){ return username;}
    public void setUsername(String nameIn) {username= nameIn;}

    public User (AuthToken authIn, String nameIn){
        authToken = authIn;
        username = nameIn;
    }

}
