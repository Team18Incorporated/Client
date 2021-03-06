package edu.byu.cs.team18.tickettoride.Common;



public class TrainCard extends Card {

    private CardColor color;

    public TrainCard(CardColor color) {
        this.color = color;
    }

    public TrainCard(String color)
    {
        this.color = new CardColor(color);
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }
}
