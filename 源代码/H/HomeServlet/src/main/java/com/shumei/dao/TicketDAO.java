package com.shumei.dao;

import com.shumei.pojo.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getticketList();   //获取所有门票列表

    boolean delticket(int ticID);   //根据门票ID删除门票

    void updateTicket(Ticket ticket);   //更新门票信息

    void addTicket(Ticket ticket);  //添加新门票

    Ticket getTicketByTid(Integer ticID);   //根据门票ID获取门票信息

    List<Ticket> getTicketList(String keyword); //根据关键词获取门票列表
}
