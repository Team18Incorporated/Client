package edu.byu.cs.team18.tickettoride.Common;



public class CardColor {
    String color;

    public CardColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        CardColor color= (CardColor) obj;
        if(((CardColor) obj).getColor().equals(color))
        {
            return true;
        }
        return false;
    }
}
