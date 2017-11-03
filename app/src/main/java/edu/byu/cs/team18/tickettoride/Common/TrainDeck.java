package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by dasolomo on 11/1/17.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class TrainDeck {

    ArrayList<TrainCard> deckList;
    ArrayList<TrainCard> discardList;

    public TrainDeck()
    {
        createTrainDeck();
        shuffle();
    }

    private void createTrainDeck()
    {
        deckList=new ArrayList<TrainCard>();
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




    public void shuffle()
    {
        Collections.shuffle(deckList);
    }

    public void discard(List<TrainCard> list)
    {
        discardList.addAll(list);
    }

    public void reshuffle()
    {
        deckList.addAll(discardList);
        discardList.clear();
        Collections.shuffle(deckList);
    }

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

    public int getSize()
    {
        return deckList.size();
    }
}