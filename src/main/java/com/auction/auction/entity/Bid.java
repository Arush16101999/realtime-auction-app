package com.auction.auction.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;
    private String bidderName;
    private BigDecimal amount;
    private LocalDateTime bidTime;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    @JsonIgnore
    private Item item;

}
