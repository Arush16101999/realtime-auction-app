package com.auction.auction.model;


import com.auction.auction.enums.AuctionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Schema(description = "Item Response DTO")
public class ItemResponseDto {
    @Schema(description = "PK of the item")
    private Long itemId;
    private String name;
    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private LocalDateTime auctionEndTime;
    private AuctionStatus status;

}
