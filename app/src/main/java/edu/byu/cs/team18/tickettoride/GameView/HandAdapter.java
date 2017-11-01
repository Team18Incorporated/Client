package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by Antman 537 on 11/1/2017.
 */

public class HandAdapter extends BaseExpandableListAdapter {


    private Context _context;
    private ArrayList<ArrayList<TrainCard>> mainList;
    private ArrayList<TrainCard> hand;


    public HandAdapter(Context _context, ArrayList<TrainCard> hand) {
        this._context = _context;
        this.hand = hand;
    }

    @Override
    public int getGroupCount() {
        return mainList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mainList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return mainList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mainList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        return null;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        return null;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
