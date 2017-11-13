package edu.byu.cs.team18.tickettoride;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public void close(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void refreshView(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.history_recycler_view);
        gameHistoryAdapter = new GameHistoryAdapter(getContext(), commandList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gameHistoryAdapter);

        Button exit = (Button) view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

        return view;
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public CommandList getCommandList() {
        return commandList;
    }

    public void setCommandList(CommandList commandList) {
        this.commandList = commandList;
    }

    public GameHistoryAdapter getGameHistoryAdapter() {
        return gameHistoryAdapter;
    }

    public void setGameHistoryAdapter(GameHistoryAdapter gameHistoryAdapter) {
        this.gameHistoryAdapter = gameHistoryAdapter;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}