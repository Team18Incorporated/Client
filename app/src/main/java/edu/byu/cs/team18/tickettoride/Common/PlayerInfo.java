package edu.byu.cs.team18.tickettoride.Common;



public class PlayerInfo {

    private String playerID;
    private String playerName;
    private Player.Color color;
    private int numTrainCards;
    private int numDestinationCards;
    private int points;
    private int numTrainPieces;

    public PlayerInfo(Player player)
    {
        playerID=player.getPlayerID();
        playerName=player.getPlayerName();
        color= player.getColor();
        points=player.getPoints();
        numTrainCards=player.getHand().size();
        numDestinationCards=player.getDestinationCards().size();
        numTrainPieces=player.getNumTrainPieces();
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player.Color getColor() {
        return color;
    }

    public int getNumTrainCards() {
        return numTrainCards;
    }

    public int getNumDestinationCards() {
        return numDestinationCards;
    }

    public int getPoints() {
        return points;
    }

    public int getNumTrainPieces(){return numTrainPieces;}

    public void setNumTrainCards(int numTrainCards) {
        this.numTrainCards = numTrainCards;
    }

    public void setNumDestinationCards(int numDestinationCards) {
        this.numDestinationCards = numDestinationCards;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNumTrainPieces(int pieces){numTrainPieces = pieces;}

    public String getColorString()
    {
        if(color== Player.Color.RED)
        {
            return "Red";
        }
        else if(color== Player.Color.BLUE)
        {
            return "Blue";
        }
        else if(color== Player.Color.BLACK)
        {
            return "Black";
        }
        else if(color== Player.Color.GREEN)
        {
            return "Green";
        }
        else
        {
            return "Yellow";
        }
    }
}
