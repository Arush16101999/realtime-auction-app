package com.auction.auction.repository;

import com.auction.auction.entity.Item;
import com.auction.auction.enums.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(AuctionStatus status);

}
