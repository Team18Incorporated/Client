package edu.byu.cs.team18.tickettoride;

import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.ShowDestinationChoicesCommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;
import edu.byu.cs.team18.tickettoride.Common.ClientGame;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;
import edu.byu.cs.team18.tickettoride.Common.IClient;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.Common.User;

/**
 * Created by abram on 10/9/2017.
 */

public class ClientFacade implements IClient {

    private static ClientFacade clientFacade = null;

    private ClientFacade(){}

    public static ClientFacade getClientFacade()
    {
        if(clientFacade == null)
        {
            clientFacade = new ClientFacade();
        }
        return clientFacade;
    }


    /*updateJoinGameList updates the gameList of joinable games
    *
    * @pre gameList cannot be null
    * @post will update the JoinableGames GameList on the Client to match the incoming GameList
    * */
    @Override
    public void updateJoinGameList(GameList gameList){
        ClientModel.SINGLETON.setJoinableGamesList(gameList);
    }

    /*updateCurrentGamesList updates list of game the player is currently in
    *
    * @pre gameList cannot be null
    * @post will update the PlayerGames GameList on the Client to match the incoming GameList
    * */
    @Override
    public void updateCurrentGamesList(GameList gameList)
    {
        ClientModel.SINGLETON.setJoinedGamesList(gameList);
    }

    /*updateUser updates the info stored in the client about the current user

    * @pre user cannot be null
    * @post stored User info will be updated
    * */
    @Override
    public void updateUser(User user)
    {
        ClientModel.SINGLETON.setCurrentUser(user);
    }

    /*updatePlayer updates info about the current Player

    *@pre player cannot be null
    *@post Player in the client will be updated
    * */
    @Override
    public void updatePlayer(Player player)
    {
        ClientModel.SINGLETON.setCurrentPlayer(player);
    }

    /*updateGame updates the current game in the client

    * @pre game cannot be null
    * @post current game will be updated
    *
    * */
    @Override
    public void updateGame(ClientGame game)
    {
        ClientModel.SINGLETON.setCurrentGame(game);
    }

    /*updateGame updates parts of the game without passing over the whole Game object

    * @pre gameInfo cannot be null. CurrentGame on the client cannot be null
    * @post CurrentGame will be updated
    * */
    @Override
    public void updateGame(GameInfo gameInfo)
    {

    }

    @Override
    public void updateTrainDeckSize(int size)
    {
        ClientModel.SINGLETON.getCurrentGame().setNumTrainDeck(size);
    }

    @Override
    public void updateDestinationDeckSize(int size)
    {
        ClientModel.SINGLETON.getCurrentGame().setNumDestinationDeck(size);
    }

    @Override
    public void updateScore(int points)
    {
        ClientModel.SINGLETON.getCurrentPlayer().setPoints(points);
    }

    @Override
    public void claimRoute(String gameID, String playerID, Route route)
    {
        //Add route to player's list
        ClientModel.SINGLETON.getCurrentPlayer().getClaimedRoutes().add(route);
        //Subtract length of route from number of player's train pieces
        ClientModel.SINGLETON.getCurrentPlayer().setNumTrainPieces(ClientModel.SINGLETON.getCurrentPlayer().getNumTrainPieces() - route.getLength());


        //Still needs to find the right game and mark it with tokens of the player's color.
    }

    @Override
    public void updateTrainHand(TrainCard card1, TrainCard card2)
    {
        ClientModel.SINGLETON.getCurrentPlayer().getHand().add(card1);
        ClientModel.SINGLETON.getCurrentPlayer().getHand().add(card2);
    }

    @Override
    public void updateDestinationHand(List<DestinationCard> list)
    {
        ClientModel.SINGLETON.getCurrentPlayer().getDestinationCards().addAll(list);
    }

    @Override
    public void updateEnemyTrainHand(String playerID, int size)
    {
        for(int i = 0; i < ClientModel.SINGLETON.getCurrentGame().getPlayerList().size(); i++)
        {
            if(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).getPlayerID().equals(playerID))
            {
                ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).setNumTrainCards(size);
                return;
            }
        }
    }

    @Override
    public void updateEnemyDestinationHand(String playerID, int size)
    {
        for(int i = 0; i < ClientModel.SINGLETON.getCurrentGame().getPlayerList().size(); i++)
        {
            if(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).getPlayerID().equals(playerID))
            {
                ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).setNumDestinationCards(size);
                return;
            }
        }
    }

    @Override
    public void updateEnemyScore(String playerID, int points)
    {
        for(int i = 0; i < ClientModel.SINGLETON.getCurrentGame().getPlayerList().size(); i++)
        {
            if(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).getPlayerID().equals(playerID))
            {
                ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).setPoints(points);
                return;
            }
        }
    }


    @Override
    public void showDestinationCardChoices(List<DestinationCard> list)
    {
        //We need some way to display these cards in the GUI without adding them to the player's hand.

        ClientModel.SINGLETON.getCurrentPlayer().setDestinationCardChoices(list);
    }

    @Override
    public void updateFaceUp(List<TrainCard> list)
    {
        ClientModel.SINGLETON.getCurrentGame().getVisibleCards().clear();
        ClientModel.SINGLETON.getCurrentGame().getVisibleCards().addAll(list);
    }

    @Override
    public void updateChatHistory(ChatHistory chatHistory)
    {
        if(chatHistory != null)
        {
            ClientModel.SINGLETON.getCurrentGame().getChatHistory().getHistory().addAll(chatHistory.getHistory());
        }
    }











    public String findPlayerName(String playerID)
    {
        for(int i = 0; i < ClientModel.SINGLETON.getCurrentGame().getPlayerList().size(); i++)
        {
            if(playerID.equals(ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).getPlayerID()))
            {
                return ClientModel.SINGLETON.getCurrentGame().getPlayerList().get(i).getPlayerName();
            }
        }
        return "";
    }

}
