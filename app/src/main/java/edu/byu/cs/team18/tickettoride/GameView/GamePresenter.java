package edu.byu.cs.team18.tickettoride.GameView;

import android.graphics.Point;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ClaimRouteCommand;
//import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.RemoveCardsCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.RemoveCardsCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateChatHistoryCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateEnemyScoreCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateEnemyTrainHandCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateFaceUpCommand;
//import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdatePlayerTurnCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdatePlayerTurnCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateScoreCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateTrainDeckSizeCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateTrainHandCommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by Antman 537 on 10/25/2017.
 */

public class GamePresenter implements Observer{
    public static GamePresenter SINGLETON = new GamePresenter();
    private GameViewFragment view = null;
    private int testStep=0;

    private GamePresenter (){}

    public void setView(GameViewFragment in){
        view = in;
        ClientModel.SINGLETON.observerRegister(this);
    }
    /*
    clears view and stops observing ClientModel
    @Pre: none
    @Post: view = null
     */
    public void clearView(){
        view = null;
        ClientModel.SINGLETON.observerRemove(this);
    }

    /*
    draws a card from the deck and adds it to the active player's hand
    @pre: none
    @post: none
     */
    public void drawCard(){
      if(!ClientFacade.getClientFacade().drawTrainCard())
      {
          Toast.makeText(view.getContext(), "Not your turn.", Toast.LENGTH_SHORT).show();
      }
      /*else
      {
          ServerProxy.getServerProxy().drawTrainCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(), ClientModel.SINGLETON.getCurrentGame().getGameID());
      }*/


    }
    public void drawFaceUpCard(int in){

        if(!ClientFacade.getClientFacade().drawFaceUp(in))
        {
            Toast.makeText(view.getContext(), "Can't do that now.", Toast.LENGTH_SHORT).show();
        }
        /*else
        {
            ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(), ClientModel.SINGLETON.getCurrentGame().getGameID(), in);
        }*/
    }
    public void drawDestinationCards()
    {
        if(!ClientFacade.getClientFacade().drawDestinationCards())
        {
            Toast.makeText(view.getContext(), "Not your turn.", Toast.LENGTH_LONG).show();
        }
//        else
//        {
//            ClientModel.SINGLETON.drawDestinationCards();
//        }
    }

    /*
    uses model to determine route pressed and selects
    @pre: in!= null && iv is a car image
    @post:none
     */
    public void selectRoute(Route in){
        ClientModel.SINGLETON.setLastRoute(ClientModel.SINGLETON.getCurrentRoute());
        ClientModel.SINGLETON.setCurrentRoute(in);
        Player player = ClientModel.SINGLETON.getCurrentPlayer();
        if (in.getLength()<=player.cardCount(in.getColor())){
            view.toggleClaim(true);
        }
        else{
            view.toggleClaim(false);
        }
    }
    /*
    Claims designated route for currentPlayer;
     */
    public void claimRoute(Route in){
        if(in == null){return;}
        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
        String id = ClientModel.SINGLETON.getCurrentGame().getGameID();
        if(!ClientFacade.getClientFacade().claimRouteCheck())
        {
            Toast.makeText(view.getContext(), "Not your turn.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(ClientModel.SINGLETON.getCurrentPlayer().getNumTrainPieces() < in.getLength())
        {
            Toast.makeText(view.getContext(), "You don't have enough train pieces to claim that.", Toast.LENGTH_SHORT).show();
            return;
        }
        Route rBefore = null;
        if(in.getID()>0)
        {
           rBefore = ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList().get(in.getID() - 1);
        }

        Route rAfter = null;

        if(in.getID()<99)
        {
            rAfter = ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList().get(in.getID() + 1);
        }


        if(ClientModel.SINGLETON.getCurrentGame().getPlayerList().size() <= 3)
        {
            if(rBefore!=null && in.almostEquals(rBefore))
            {
                if (rBefore.getOwnerID() != null)
                {
                    Toast.makeText(view.getContext(), "Can't claim twin routes in 2 or 3-player games.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else if(rAfter!=null && in.almostEquals(rAfter))
            {
                if(rAfter.getOwnerID() != null)
                {
                    Toast.makeText(view.getContext(), "Can't claim twin routes in 2 or 3-player games.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        else if(rBefore!=null && in.almostEquals(rBefore))
        {
            if(rBefore.getOwnerID().equals(in.getOwnerID()))
            {
                Toast.makeText(view.getContext(), "Can't claim both routes.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if(rAfter!=null && in.almostEquals(rAfter))
        {
            if(rAfter.getOwnerID().equals(in.getOwnerID()))
            {
                Toast.makeText(view.getContext(), "Can't claim both routes.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        view.getGameActivity().claimRouteCardSelect();
//        CommandList temp = ServerProxy.getServerProxy().claimRoute(token, id, in);
//        temp.execute();
    }
    public ArrayList<TrainCard> getHand()
    {
        return ClientModel.SINGLETON.getCurrentPlayer().getHand();
    }
    /*
    counts the given player's pieces
    @pre: player!= null  && player is in game
    @post: 0<=int out<=48 -1 = error
     */
    public int countPieces(Player in){
        int out = in.getNumTrainPieces();
        return out;
    }

    public String getCurrentPlayerColor()
    {
        return ClientModel.SINGLETON.getCurrentPlayer().getColorString();
    }

    public String getPlayerTurn()
    {
        int index =ClientModel.SINGLETON.getCurrentGame().getPlayerTurn();
        ArrayList<PlayerInfo> playerList = (ArrayList)ClientModel.SINGLETON.getCurrentGame().getPlayerList();
        String returnStr =playerList.get(index).getPlayerName()+" - "+playerList.get(index).getColorString();
        if(ClientModel.SINGLETON.getLastRound())
        {
            returnStr += " - last round";
        }
        return returnStr;
    }

    public String getDestinationNum()
    {
        return Integer.toString(ClientModel.SINGLETON.getCurrentGame().getNumDestinationDeck());
    }

    public void ServerDown(){
        //Toast.makeText(view.getContext(), "Server Down.", Toast.LENGTH_LONG).show();
    }



    /*public void incrementTest()
    {
        if(testStep==0)
        {

            UpdateTrainHandCommand uthc = new UpdateTrainHandCommand(new TrainCard("red"), new TrainCard(("wild")));
            uthc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uthc);
            Toast.makeText(view.getContext(), "Drawing Train Cards", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==1)
        {

            UpdateTrainDeckSizeCommand utsdc = new UpdateTrainDeckSizeCommand(ClientModel.SINGLETON.getCurrentGame().getNumTrainDeck()-2);
            utsdc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(utsdc);
            Toast.makeText(view.getContext(), "Updating Deck size", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==2) {

            {
                ClaimRouteCommand crc = new ClaimRouteCommand(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                        ClientModel.SINGLETON.getCurrentGame().getGameID(),
                        ClientModel.SINGLETON.getCurrentPlayer().getPlayerID(),
                        ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList().get(2));
                crc.execute();
                ClientModel.SINGLETON.getCurrentGame().addToGameHistory(crc);
                Toast.makeText(view.getContext(), "Claiming route", Toast.LENGTH_LONG).show();
                testStep++;
            }
        }
         else if(testStep==3)
        {

            RemoveCardsCommand rcc = new RemoveCardsCommand(ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList().get(2));
            rcc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(rcc);
            Toast.makeText(view.getContext(), "Removing Cards", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==4)
        {

            UpdateScoreCommand usc = new UpdateScoreCommand(2);
            usc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(usc);
            Toast.makeText(view.getContext(), "Updating points", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==5)
        {

            UpdateEnemyTrainHandCommand uethc= new UpdateEnemyTrainHandCommand(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(0).getPlayerID(), 6);
            uethc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uethc);
            Toast.makeText(view.getContext(), "Updating Other Player's hand size", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==6)
        {

            UpdateEnemyScoreCommand uesc = new UpdateEnemyScoreCommand(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(0).getPlayerID(), 6);
            uesc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uesc);
            Toast.makeText(view.getContext(), "Updating Other Player's points", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==7)
        {

            Date date = new Date();
            ChatMessage cm = new ChatMessage("hello world", "aaaa",ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(0).getPlayerName(), date);
            ArrayList<ChatMessage> chat = new ArrayList<>();
            chat.add(cm);
            UpdateChatHistoryCommand uchc = new UpdateChatHistoryCommand(new ChatHistory(chat));
            uchc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uchc);
            Toast.makeText(view.getContext(), "Sending chat", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==8)
        {

            ArrayList<TrainCard> faceup = ClientModel.SINGLETON.getCurrentGame().getVisibleCards();
            faceup.add(2, new TrainCard("purple"));
            faceup.add(4, new TrainCard("red"));
            UpdateFaceUpCommand ufuc = new UpdateFaceUpCommand(faceup);
            ufuc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(ufuc);
            Toast.makeText(view.getContext(), "Updating Face Up Cards", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==9)
        {
            testStep++;
        }
        else if(testStep==10)
        {

            UpdatePlayerTurnCommand uptc= new UpdatePlayerTurnCommand(ClientModel.SINGLETON.getCurrentGame().getPlayerTurn()+1);
            uptc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uptc);
            Toast.makeText(view.getContext(), "Next Player's turn", Toast.LENGTH_LONG).show();
            testStep++;
        }


    }*/


    @Override
    public void update(Observable observable, Object o) {
        if(o!=null)
        {
            if (o instanceof Route){
                view.setRouteColor((Route) o);
            }
            else if(o instanceof ArrayList)
            {
                GameActivity ga =(GameActivity)view.getActivity();
                ga.showDestinationCardChoices();
            }
            else if (o instanceof String && o.equals("show endgame")){
                view.getGameActivity().endGame();
            }
            else if (o instanceof String && o.equals("server down")){
                this.ServerDown();
            }
        }

        updateView();
        //todo: add conditions
    }

    private void updateView()
    {
        if(view!=null)
        {
            if(view.getActivity()!=null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(view!=null)
                        {
                            view.refreshView();
                        }
                    }
                });

            }//view.refreshView();
        }

    }


}
