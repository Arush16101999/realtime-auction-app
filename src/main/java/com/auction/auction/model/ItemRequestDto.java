package com.auction.auction.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
@Schema(description = "Item request DTO")
public class ItemRequestDto {
    @NotNull(message = "Item name is required")
    private String name;
    @NotNull(message = "Item starting price is required")
    private BigDecimal startingPrice;
    private LocalDateTime auctionEndTime;

}
