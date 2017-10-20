package edu.byu.cs.team18.tickettoride.Common.Commands;

import java.util.List;

/**
 * Created by abram on 10/20/2017.
 */

public class CommandList {
    private List<ICommand> list;

    public List<ICommand> getList() {
        return list;
    }

    public void setList(List<ICommand> list) {
        this.list = list;
    }

    public CommandList(List<ICommand> list) {
        this.list = list;
    }
}
