package edu.byu.cs.team18.tickettoride;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/30/2017.
 */

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryViewHolder> {
    private Context C;
    private ArrayList<ICommand> commandList;

    @Override
    public GameHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(C);
        View view = inflater.inflate(R.layout.history_item, parent, false);
        return new GameHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameHistoryViewHolder holder, int position) {
        holder.getTextView().setText(commandList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return commandList.size();
    }

    public GameHistoryAdapter(Context c, ArrayList<ICommand> commandList)
    {
        C = c;
        this.commandList = commandList;
    }

    public void swap ( ArrayList<ICommand> cl){
        commandList=cl;
        notifyDataSetChanged();
    }
}
