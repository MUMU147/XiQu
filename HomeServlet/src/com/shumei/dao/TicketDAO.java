package com.shumei.dao;

import com.shumei.pojo.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getticketList();

    boolean delticket(int ticID);

    void updateTicket(Ticket ticket);

    void addTicket(Ticket ticket);

    Ticket getTicketByTid(Integer ticID);

    List<Ticket> getTicketList(String keyword);

}
