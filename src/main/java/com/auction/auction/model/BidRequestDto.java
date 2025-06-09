package com.auction.auction.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@Schema(description = "Bid request DTO")
public class BidRequestDto {
    @NotNull(message = "Bidder Name is required")
    private String bidderName;
    @NotNull(message = "Bid amount is required")
    private BigDecimal amount;
}
