package com.auction.auction.repository;

import com.auction.auction.entity.Bid;
import com.auction.auction.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    // find top bid for an item
    List<Bid> findTopByItemOrderByAmountDesc(Item item);


}
