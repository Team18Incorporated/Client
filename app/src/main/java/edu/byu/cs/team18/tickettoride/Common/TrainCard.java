package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by Solomons on 10/18/2017.
 */

public class TrainCard extends Card {

    public enum Color{PURPLE, WHITE, RED, BLUE, GREEN, YELLOW, BLACK, ORANGE, WILD}
    private Color color;

    public TrainCard(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
