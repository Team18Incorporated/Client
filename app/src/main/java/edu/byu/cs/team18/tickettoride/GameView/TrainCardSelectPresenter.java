package edu.byu.cs.team18.tickettoride.GameView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;

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

    private boolean checkTotal(int cards, int total)
    {
        if(total - cards == 0)
        {
            return true;
        }
        return false;
    }


    public boolean claimRouteCanDo(Route route, int redSelected, int blackSelected, int blueSelected, int greenSelected,
                                   int yellowSelected, int purpleSelected, int whiteSelected, int orangeSelected, int wildSelected)
    {
        int total = redSelected + blackSelected + blueSelected + greenSelected + yellowSelected + purpleSelected + whiteSelected
                + orangeSelected + wildSelected;
        String color = route.getColor().getColor();
        int length = route.getLength();
        switch (color)
        {
            case "any":
                if(redSelected + wildSelected == length)
                {
                    if(!checkTotal(redSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(blackSelected + wildSelected == length)
                {
                    if(!checkTotal(blackSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(blueSelected + wildSelected == length)
                {
                    if(!checkTotal(blueSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(greenSelected + wildSelected == length)
                {
                    if(!checkTotal(greenSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(yellowSelected + wildSelected == length)
                {
                    if(!checkTotal(yellowSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(purpleSelected + wildSelected == length)
                {
                    if(!checkTotal(purpleSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(whiteSelected + wildSelected == length)
                {
                    if(!checkTotal(whiteSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else if(orangeSelected + wildSelected == length)
                {
                    if(!checkTotal(orangeSelected+wildSelected, total))
                    {
                        return false;
                    }
                    return true;
                }
                else
                {
                    return false;
                }

            case "red":
                if(!checkTotal(redSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(blackSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(blueSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(greenSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(yellowSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(purpleSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(whiteSelected+wildSelected, total))
                {
                    return false;
                }
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
                if(!checkTotal(orangeSelected+wildSelected, total))
                {
                    return false;
                }
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

        ServerProxy.getServerProxy().claimRoute(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(),route, numSelectedCards);
    }





}
