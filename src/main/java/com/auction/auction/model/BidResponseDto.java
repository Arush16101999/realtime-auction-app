package com.auction.auction.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Schema(description = "Bid response DTO")
public class BidResponseDto {
    private Long bidId;
    private String bidderName;
    private BigDecimal amount;
    private LocalDateTime bidTime;
    private Long itemId; //

}
