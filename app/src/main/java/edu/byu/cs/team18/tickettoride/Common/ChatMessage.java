package edu.byu.cs.team18.tickettoride.Common;

import java.util.Date;

/**
 * Created by abram on 10/25/2017.
 */

public class ChatMessage {
    private String message;
    private String messageID;
    private String playerID;
    private Date time;

    public ChatMessage(String message, String messageID, String playerID, Date time) {
        this.message = message;
        this.messageID = messageID;
        this.playerID = playerID;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
