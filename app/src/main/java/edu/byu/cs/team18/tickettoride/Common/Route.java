package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by Solomons on 10/18/2017.
 */

public class Route {

    private City city1;
    private City city2;
    private int length;
    private TrainCard.Color color1;
    private TrainCard.Color color2;

    public Route (String cityName1, String cityName2, int length, TrainCard.Color color1, TrainCard.Color color2)
    {
        this.length=length;
        city1=new City(cityName1);
        city2=new City(cityName2);
        this.color1=color1;
        this.color2=color2;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getLength() {
        return length;
    }

    public TrainCard.Color getColor1() {
        return color1;
    }

    public TrainCard.Color getColor2() {
        return color2;
    }
}
