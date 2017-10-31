package edu.byu.cs.team18.tickettoride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;

/**
 * Created by abram on 10/30/2017.
 */

public class GameHistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private CommandList commandList;
    private GameHistoryAdapter gameHistoryAdapter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.history_recycler_view);
        gameHistoryAdapter = new GameHistoryAdapter(getContext(), commandList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gameHistoryAdapter);


        return view;
    }
}