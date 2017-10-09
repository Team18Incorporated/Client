package edu.byu.cs.team18.tickettoride.Common;



public class User {

    private AuthToken authToken;
    private String username;

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(AuthToken authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }
}
