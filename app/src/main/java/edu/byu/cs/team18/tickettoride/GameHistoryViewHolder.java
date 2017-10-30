package edu.byu.cs.team18.tickettoride;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by abram on 10/30/2017.
 */

public class GameHistoryViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;


    public GameHistoryViewHolder(View view)
    {
        super(view);
        textView = (TextView) view.findViewById(R.id.history_item_text_view);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
