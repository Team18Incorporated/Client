package edu.byu.cs.team18.tickettoride.GameView;

import android.graphics.Point;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
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
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateScoreCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateTrainDeckSizeCommand;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.UpdateTrainHandCommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.Player;
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
        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
        String id = ClientModel.SINGLETON.getCurrentGame().getGameID();
        ServerProxy.getServerProxy().drawTrainCard(token,id);
//        CommandList temp = ServerProxy.getServerProxy().drawTrainCard(token,id);
//        temp.execute();
    }
    public void drawFaceUpCard(int in){
        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
        String id = ClientModel.SINGLETON.getCurrentGame().getGameID();
        TrainCard card = ClientModel.SINGLETON.getCurrentGame().getVisibleCards().get(in);
        ServerProxy.getServerProxy().drawFromFaceUp(token,id,in);
    }
    /*
    adds designated DestinationCard to the user's destinations
    @pre: in!=null
    @post: none
     */
    public void addDestination(DestinationCard in){
        //todo: connect to model
    }
    /*
    uses model to determine route pressed and selects
    @pre: pos !=null
    @post:none
     */
    public void selectRoute(Point pos){
        //Todo: connect to model
    }
    /*
    uses model to determine route pressed and selects
    @pre: iv!= null && iv is a car image
    @post:none
     */
    public void selectRoute(Route in){
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
        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
        String id = ClientModel.SINGLETON.getCurrentGame().getGameID();
        ServerProxy.getServerProxy().claimRoute(token, id, in);
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

    public void incrementTest()
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
            UpdateFaceUpCommand ufuc = new UpdateFaceUpCommand(faceup);
            ufuc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(ufuc);
            Toast.makeText(view.getContext(), "Updating Face Up Cards", Toast.LENGTH_LONG).show();
            testStep++;
        }
        else if(testStep==9)
        {

        }
        else if(testStep==10)
        {

            /*UpdatePlayerTurnCommand uptc= new UpdatePlayerTurnCommand(ClientModel.SINGLETON.getCurrentGame().getPlayerTurn()+1);
            uptc.execute();
            ClientModel.SINGLETON.getCurrentGame().addToGameHistory(uptc);
            Toast.makeText(view.getContext(), "Next Player's turn", Toast.LENGTH_LONG).show();
            testStep++;*/
        }

        
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o!=null && o instanceof Route){
            view.setRouteColor((Route) o);
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
