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

/**
 * Created by dasolomo on 11/3/17.
 */

public class DestinationViewFragment extends Fragment {

    private RecyclerView destinationsRV;
    private DestinationCardAdapter destinationAdapter;
    private RecyclerView.LayoutManager cardSelectLayoutManager;
    private Button backButton;
    private View view;
    private ArrayList<DestinationCard> cards;



    public DestinationViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.destination_card_view, container, false);
        initiateView();


        backButton = (Button) view.findViewById(R.id.destination_back_Button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClicked();
            }
        });

        destinationsRV = (RecyclerView) view.findViewById(R.id.destination_recyclerView);

        cardSelectLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        destinationsRV.setLayoutManager(cardSelectLayoutManager);
        destinationsRV.setHasFixedSize(true);

        destinationAdapter = new DestinationCardAdapter(cards);

        destinationsRV.setAdapter(destinationAdapter);

        DestinationViewPresenter.instance.setView(this);

        return view;
    }

    private void initiateView()
    {
        cards=(ArrayList) DestinationViewPresenter.instance.getDestinationCards();
    }


    private void onBackButtonClicked()
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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

            discardBox.setVisibility(View.INVISIBLE);

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
        public DestinationViewFragment.destinationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view= inflater.inflate(R.layout.destinationcard_view, parent, false);
            return new DestinationViewFragment.destinationsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DestinationViewFragment.destinationsViewHolder holder, int position) {
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

