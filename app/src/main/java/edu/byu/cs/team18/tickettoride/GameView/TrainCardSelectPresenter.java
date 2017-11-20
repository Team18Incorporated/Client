package edu.byu.cs.team18.tickettoride.GameView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;
import edu.byu.cs.team18.tickettoride.States.NotTurnState;

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
    private TrainCardSelectFragment view;

    public static TrainCardSelectPresenter SINGLETON = new TrainCardSelectPresenter();




    private TrainCardSelectPresenter(){}

    public Route getRoute()
    {
        Route route =ClientModel.SINGLETON.getCurrentRoute();
        ClientModel.SINGLETON.setCurrentRoute(null);
        return route;
    }

    public void setView(TrainCardSelectFragment view)
    {
        this.view=view;
    }

    public ArrayList<Integer> getCardLists()
    {
        ArrayList<Integer> list = new ArrayList<>();

        ArrayList<TrainCard> trainCards = ClientModel.SINGLETON.getCurrentPlayer().getHand();

        for(int i=0; i<trainCards.size(); i++) {
            if (trainCards.get(i).getColor().getColor().equals("red"))
            {
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
        boolean canDo= false;
        switch (color)
        {
            case "any":
                if(redSelected + wildSelected == length)
                {
                    if(!checkTotal(redSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;
                }
                else if(blackSelected + wildSelected == length)
                {
                    if(!checkTotal(blackSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else if(blueSelected + wildSelected == length)
                {
                    if(!checkTotal(blueSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else if(greenSelected + wildSelected == length)
                {
                    if(!checkTotal(greenSelected+wildSelected, total))
                    {
                        canDo=false;
                    }
                    else
                        canDo= true;                }
                else if(yellowSelected + wildSelected == length)
                {
                    if(!checkTotal(yellowSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else if(purpleSelected + wildSelected == length)
                {
                    if(!checkTotal(purpleSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else if(whiteSelected + wildSelected == length)
                {
                    if(!checkTotal(whiteSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else if(orangeSelected + wildSelected == length)
                {
                    if(!checkTotal(orangeSelected+wildSelected, total))
                    {
                        canDo= false;
                    }
                    else
                        canDo= true;                }
                else
                {
                    canDo= false;
                }
                break;

            case "red":
                if(!checkTotal(redSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(redSelected==length)
                {
                    canDo= true;
                }
                else if(redSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "black":
                if(!checkTotal(blackSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(blackSelected==length)
                {
                    canDo= true;
                }
                else if(blackSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "blue":
                if(!checkTotal(blueSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(blueSelected==length)
                {
                    canDo= true;
                }
                else if(blueSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "green":
                if(!checkTotal(greenSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(greenSelected==length)
                {
                    canDo= true;
                }
                else if(greenSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "yellow":
                if(!checkTotal(yellowSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(yellowSelected==length)
                {
                    canDo= true;
                }
                else if(yellowSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "purple":
                if(!checkTotal(purpleSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(purpleSelected==length)
                {
                    canDo= true;
                }
                else if(purpleSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "white":
                if(!checkTotal(whiteSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(whiteSelected==length)
                {
                    canDo= true;
                }
                else if(whiteSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

            case "orange":
                if(!checkTotal(orangeSelected+wildSelected, total))
                {
                    canDo= false;
                }
                if(orangeSelected==length)
                {
                    canDo= true;
                }
                else if(orangeSelected+wildSelected==length)
                {
                    canDo= true;
                }
                else
                {
                    canDo= false;
                }
                break;

        }
        return canDo;
    }


    public void claimRoute(Route route, ArrayList<Integer> numSelectedCards)
    {
        ServerProxy.getServerProxy().claimRoute(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(),route, numSelectedCards);
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        ServerProxy.getServerProxy().endTurn(ClientModel.SINGLETON.getCurrentUser().getAuthToken(), ClientModel.SINGLETON.getCurrentGame().getGameID(), ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
        clearLists();
        view.getActivity().getSupportFragmentManager().beginTransaction().remove(view).commit();
    }



    public void clearLists()
    {
        numBlack=0;
        numBlue=0;
        numGreen=0;
        numOrange=0;
        numPurple=0;
        numRed=0;
        numWhite=0;
        numWild=0;
        numYellow=0;
    }

}
