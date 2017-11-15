package edu.byu.cs.team18.tickettoride.GameView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by Solomons on 11/14/2017.
 */


/*
*
* ##NEEDS EDITING!!!!##
*
* ##NEEDS EDITING!!!!##
*
* ##NEEDS EDITING!!!!##
*
* */
public class TrainCardSelectPresenter {

    private int numRed=0;
    private int numBlack=0;
    private int numBlue=0;
    private int numGreen=0;
    private int numYellow=0;
    private int numPurple=0;
    private int numWhite=0;
    private int numOrange=0;
    private int numWild=0;

    public static TrainCardSelectPresenter SINGLETON = new TrainCardSelectPresenter();




    private TrainCardSelectPresenter(){}

    public ArrayList<Integer> getCardLists()
    {
        ArrayList<Integer> list = new ArrayList<>();

        ArrayList<TrainCard> trainCards = ClientModel.SINGLETON.getCurrentPlayer().getHand();

        for(int i=0; i<trainCards.size(); i++) {
            if (trainCards.get(i).getColor().getColor().equals("red")) {
                numRed++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("black"))
            {
                numBlack++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("blue"))
            {
                numBlue++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("green"))
            {
                numGreen++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("yellow"))
            {
                numYellow++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("purple"))
            {
                numPurple++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("white"))
            {
                numWhite++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("orange"))
            {
                numOrange++;
            }
            else if(trainCards.get(i).getColor().getColor().equals("wild"))
            {
                numWild++;
            }

        }
        list.add(numBlack);
        list.add(numRed);
        list.add(numBlue);
        list.add(numGreen);
        list.add(numYellow);
        list.add(numPurple);
        list.add(numWhite);
        list.add(numOrange);
        list.add(numWild);

        return list;
    }

    public boolean claimRouteCanDo(Route route, int redSelected, int blackSelected, int blueSelected, int greenSelected,
                                   int yellowSelected, int purpleSelected, int whiteSelected, int orangeSelected, int wildSelected)
    {
        String color = route.getColor().getColor();
        int length = route.getLength();
        switch (color)
        {
            case "any":
                if(redSelected+blackSelected+blueSelected+greenSelected+yellowSelected+orangeSelected+whiteSelected+wildSelected+purpleSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "red":
                if(redSelected==length)
                {
                    return true;
                }
                else if(redSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "black":
                if(blackSelected==length)
                {
                    return true;
                }
                else if(blackSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            case "blue":
                if(blueSelected==length)
                {
                    return true;
                }
                else if(blueSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "green":
                if(greenSelected==length)
                {
                    return true;
                }
                else if(greenSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "yellow":
                if(yellowSelected==length)
                {
                    return true;
                }
                else if(yellowSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "purple":
                if(purpleSelected==length)
                {
                    return true;
                }
                else if(purpleSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "white":
                if(whiteSelected==length)
                {
                    return true;
                }
                else if(whiteSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            case "orange":
                if(orangeSelected==length)
                {
                    return true;
                }
                else if(orangeSelected+wildSelected==length)
                {
                    return true;
                }
                else
                {
                    return false;
                }

        }
        return false;
    }


    public void claimRoute(Route route, ArrayList<Integer> numSelectedCards)
    {
        ArrayList<TrainCard> cardsToDiscard;  //WE SHOULD RETURN THE CARDS USED TO THE SERVER,
        // WILL NEED CHANGES IN THE CLAIM ROUTE METHODS FROM HERE TO THE SERVER PROXY AND POSSIBLY THE SERVER ITSELF

        ClientModel.SINGLETON.sendClaimRoute(route);
    }
}
