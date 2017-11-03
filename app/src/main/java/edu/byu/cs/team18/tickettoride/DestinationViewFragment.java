package edu.byu.cs.team18.tickettoride;

/**
 * Created by dasolomo on 11/3/17.
 */

public class DestinationViewFragment {//extends Fragment {

   /* private RecyclerView destinationsRV;
    private DestinationCardAdapter destinationAdapter;
    private RecyclerView.LayoutManager cardSelectLayoutManager;
    private TextView numCanDiscard;
    private Button backButton;
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
                        cardsRecieved.remove(c);
                    }
                    DSPInterface.instance.readyButtonClicked(cardsRecieved, discard);
                }
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
                        cardsRecieved.remove(c);
                        DSPInterface.instance.readyButtonClicked(cardsRecieved, discard);
                    }
                }

            }
        }
        else {

            ClientFacade.getClientFacade().updateDestinationHand(cardsRecieved);
        }
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
    }*/
}

