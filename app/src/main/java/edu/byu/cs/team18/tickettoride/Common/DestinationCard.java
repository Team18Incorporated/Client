package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by Solomons on 10/18/2017.
 */

public class DestinationCard extends Card {

    City startCity;
    City endCity;
    int points;

    public DestinationCard(String startCityName, String endCityName, int points) {
        startCity= new City(startCityName);
        endCity= new City(endCityName);
        this.points=points;
    }

    public City getStartCity() {
        return startCity;
    }


    public City getEndCity() {
        return endCity;
    }


}
