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

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/30/2017.
 */

public class GameHistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ICommand> commandList;
    private GameHistoryAdapter gameHistoryAdapter;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void close(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void refreshView()
    {
        commandList=GameHistoryPresenter.SINGELTON.getCommandList();

        if(commandList.size()>0)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHistoryAdapter.notifyDataSetChanged();
                    gameHistoryAdapter.swap(commandList);
                }
            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        GameHistoryPresenter.SINGELTON.setView(this);
        commandList=GameHistoryPresenter.SINGELTON.getCommandList();
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