package edu.byu.cs.team18.tickettoride;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

public class DestinationSelectFragment extends Fragment {

    private RecyclerView destinationsRV;
    private DestinationCardAdapter destinationAdapter;
    private RecyclerView.LayoutManager cardSelectLayoutManager;
    private TextView numCanDiscard;
    private Button readyButton;
    private View view;
    private ArrayList<DestinationCard> cardsRecieved;
    private  ArrayList<DestinationCard> discard = new ArrayList<>();
    private boolean hasStarted=false;


    public DestinationSelectFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.destination_select_view, container, false);
        initiateView();


        //refreshView();
        numCanDiscard=(TextView) view.findViewById(R.id.numCardText);
        if(hasStarted)
        {
            numCanDiscard.setText("2");
        }
        else
        {
            numCanDiscard.setText("1");
        }
        readyButton = (Button) view.findViewById(R.id.ready_Button);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReadyButtonClicked();
            }
        });

        destinationsRV = (RecyclerView) view.findViewById(R.id.destination_recyclerView);

        cardSelectLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        destinationsRV.setLayoutManager(cardSelectLayoutManager);
        destinationsRV.setHasFixedSize(true);

        destinationAdapter = new DestinationCardAdapter(cardsRecieved);

        destinationsRV.setAdapter(destinationAdapter);

        DestinationSelectPresenter.instance.setView(this);

        return view;
    }

    private void initiateView()
    {

        cardsRecieved=(ArrayList) DSPInterface.instance.getCards();
        hasStarted=DSPInterface.instance.checkStartStatus();

    }


    private void onReadyButtonClicked()
    {
        int numChecked=0;
        for(DestinationCard c: cardsRecieved)
        {
            if(c.isChecked())
            {
                numChecked++;
            }
        }

        if(hasStarted)
        {
            if(numChecked>2)
            {
                Toast.makeText(getActivity(), "You can't discard that many cards.", Toast.LENGTH_LONG).show();
            }
            else
            {
                for(DestinationCard c : cardsRecieved)
                {
                    if(c.isChecked())
                    {
                        discard.add(c);
                    }

                }
                for (DestinationCard c : discard)
                {
                    cardsRecieved.remove(c);
                }
                DSPInterface.instance.readyButtonClicked(cardsRecieved, discard);
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
        }
        else if(numChecked>0)
        {
            if(numChecked>1)
            {
                Toast.makeText(getActivity(), "You can't discard that many cards.", Toast.LENGTH_LONG).show();
            }
            else
            {
                for(DestinationCard c : cardsRecieved)
                {
                    if(c.isChecked())
                    {
                        discard.add(c);
                    }
                }
                for (DestinationCard c : discard)
                {
                    cardsRecieved.remove(c);
                }

                DSPInterface.instance.readyButtonClicked(cardsRecieved, discard);
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
        }
        else {
            ClientFacade.getClientFacade().updateDestinationHand(cardsRecieved);
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }






    //PRIVATE CLASSES
    //----------------------------------------------------------------------------------------------
    private class destinationsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView startCity;
        private TextView endCity;
        private TextView points;
        private CheckBox discardBox;




        public destinationsViewHolder(View itemView) {
            super(itemView);


            startCity=(TextView)itemView.findViewById(R.id.startcityText);
            endCity=(TextView)itemView.findViewById(R.id.endcityText);
            points=(TextView)itemView.findViewById(R.id.numPointsText);
            discardBox=(CheckBox) itemView.findViewById(R.id.discard_checkBox);



        }

        public void bindObject(final DestinationCard card)
        {
            startCity.setText(card.getStartCity().getCityName());
            endCity.setText(card.getEndCity().getCityName());
            points.setText(Integer.toString(card.getPoints()));

            discardBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if(buttonView.isChecked())
                    {
                        card.setChecked(true);
                    }
                    else
                    {
                        card.setChecked(false);
                    }
                }
            }
            );

        }


    }

    //----------------------------------------------------------------------------------------------
    private class DestinationCardAdapter extends RecyclerView.Adapter<destinationsViewHolder>
    {
        private ArrayList<DestinationCard> cardList;

        public DestinationCardAdapter(ArrayList<DestinationCard> cardList)
        {
            this.cardList=cardList;
        }

        @Override
        public DestinationSelectFragment.destinationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view= inflater.inflate(R.layout.destinationcard_view, parent, false);
            return new DestinationSelectFragment.destinationsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DestinationSelectFragment.destinationsViewHolder holder, int position) {
            if(cardList.size()>0) {
                holder.bindObject(cardList.get(position));
            }
        }


        @Override
        public int getItemCount() {
            return cardList.size();
        }
    }
}
