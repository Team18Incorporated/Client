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
        boolean out = false;
        if (obj instanceof CardColor && ((CardColor) obj).getColor().equals(color)){
            out = true;
        }
        return out;
    }
}
