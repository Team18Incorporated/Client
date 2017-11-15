package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by dasolomo on 11/1/17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**class TrainDeck
 *
 * This class acts as the deck for the Train Cards in a Ticket to Ride game.
 *
 * @field ArrayList<TrainCard> deckList
 * @field ArrayList<TrainCard> discardList
 */

public class TrainDeck {

    ArrayList<TrainCard> deckList;
    ArrayList<TrainCard> discardList;


    /**TrainDeck Constructor
     *
     * instantiates the deckList and discardList and automatically fills the deck with cards and shuffles them.
     */
    public TrainDeck()
    {
        deckList= new ArrayList<>();
        discardList= new ArrayList<>();
        createTrainDeck();
        shuffle();
    }

    /**createTrainDeck() creates all the train cards of various colors and adds them to deckList
     *
     * @pre none
     * @post fills deckList with TrainCard objects
     */
    private void createTrainDeck()
    {
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("red"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("blue"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("green"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("yellow"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("orange"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("white"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("black"));
        }
        for(int i=0; i<12; i++)
        {
            deckList.add(new TrainCard("purple"));
        }

        for(int i=0; i<14; i++)
        {
            deckList.add(new TrainCard("wild"));
        }
    }


    /**shuffle()  shuffles the deck using the Collections.shuffle method
     *
     * @pre none
     * @post shuffles cards in deck
     *
     */
    public void shuffle()
    {
        Collections.shuffle(deckList);
    }

    /**discard() takes cards returned from the Player and adds them to the discardList
     *
     * @param list
     * @pre list != null
     * @post adds TrainCards in list to discardList
     */
    public void discard(List<TrainCard> list)
    {
        discardList.addAll(list);
    }


    /**reshuffle() adds the discardList to deckList and reshuffles the deck
     *
     * @pre none
     * @post deckList contains all the TrainCards it started with and they are shuffled
     */
    public void reshuffle()
    {
        deckList.addAll(discardList);
        discardList.clear();
        Collections.shuffle(deckList);
    }

    /**drawCards() draws cards from the deck and returns them to the player
     *
     * @param num
     * @pre num >0
     * @post returns ArrayList of TrainCards to the player
     * @return ArrayList<TrainCards>
     */
    public ArrayList<TrainCard> drawCards(int num)
    {
        ArrayList<TrainCard> cards = new ArrayList<>();
        for(int i=0; i<num; i++)
        {
            cards.add(deckList.get(0));
            deckList.remove(0);
        }
        return cards;
    }


    /**getSize()
     *
     * @return int
     */
    public int getSize()
    {
        return deckList.size();
    }
}
