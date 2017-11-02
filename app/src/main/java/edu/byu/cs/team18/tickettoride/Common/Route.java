package edu.byu.cs.team18.tickettoride.Common;


import java.util.ArrayList;

public class Route {

    private City city1;
    private City city2;
    private int length;
    private CardColor color;
    private String ownerID;
    private ArrayList<Integer> segments = null;


    public Route (String cityName1, String cityName2, int length, CardColor color1)
    {
        this.length=length;
        city1=new City(cityName1);
        city2=new City(cityName2);
        this.color=color1;
        ownerID=null;
    }

    public Route (City city1, City city2, String color, String length)
    {
        this.city1=city1;
        this.city2=city2;
        this.length=Integer.parseInt(length);
        this.color= new CardColor(color);
        ownerID=null;

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

    public CardColor getColor() {
        return color;
    }

    public void setOwner(String playerID){ownerID=playerID;}

    public String getOwnerID(){return ownerID;}

    public void setSegments(ArrayList<Integer> in){segments = in;}

    public ArrayList<Integer> getSegments(){return segments;}


}
