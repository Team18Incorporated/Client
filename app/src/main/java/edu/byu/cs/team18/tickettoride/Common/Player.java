package edu.byu.cs.team18.tickettoride.Common;


import java.util.ArrayList;
import java.util.List;

public class Player {

    private String playerID;
    private String playerName;
    public enum Color{RED, BLUE, GREEN, YELLOW, BLACK}
    private Color color;
    private ArrayList<TrainCard> hand;
    private ArrayList<DestinationCard> destinationCards;
    private int points;
    private ArrayList<Route> claimedRoutes = new ArrayList<>();
    private int numTrainPieces;
    private ArrayList<DestinationCard> destinationCardChoices = new ArrayList<>();
    //CONSTRUCTOR-----------------------------------------------------------------------------------


    public Player(String playerID, String playerName, Color color) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.color = color;
    }

    /*changePlayerColor changes the color of the player
    *
    * @pre cannot be the same color as another player in the same Game
    * @post changes Player color
    *
    * */
    public void changePlayerColor(Color color) //need a way to find which colors have already been taken
    {
        this.color=color;
    }

    public String getPlayerName(){
	return playerName;
    }

    public String getPlayerID(){
	return playerID;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<TrainCard> getHand() {
        return hand;
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public ArrayList<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public int getNumTrainPieces() {
        return numTrainPieces;
    }

    public void setNumTrainPieces(int numTrainPieces) {
        this.numTrainPieces = numTrainPieces;
    }

    public void claimRoute(Route route)
    {
        claimedRoutes.add(route);
    }

    public List<DestinationCard> getDestinationCardChoices() {
        return destinationCardChoices;
    }

    public void setDestinationCardChoices(List<DestinationCard> destinationCardChoices) {
        this.destinationCardChoices = (ArrayList)destinationCardChoices;
    }

    public void setHand(ArrayList<TrainCard> hand) {
        this.hand = hand;
    }

    public int cardCount(CardColor color){
        int out = 0;
        for (int i=0; i<hand.size(); i++){
            if (hand.get(i).getColor() == color || hand.get(i).getColor().getColor().equals("wild")){
                out++;
            }
        }
        return out;
    }


    public String getColorString()
    {
        if(color==Color.RED)
        {
            return "Red";
        }
        else if(color==Color.BLUE)
        {
            return "Blue";
        }
        else if(color==Color.BLACK)
        {
            return "Black";
        }
        else if(color==Color.GREEN)
        {
            return "Green";
        }
        else
        {
            return "Yellow";
        }
    }
}
