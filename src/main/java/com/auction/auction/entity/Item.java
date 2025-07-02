package com.auction.auction.entity;


import com.auction.auction.enums.AuctionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId; // BIGINT supports Long
    private String name;
    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private LocalDateTime auctionEndTime;
    @Enumerated(EnumType.STRING)
    private AuctionStatus status = AuctionStatus.OPEN;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true) // cascade all operations to bids to prevent double persistence
    private List<Bid> bids = new ArrayList<>();


}
