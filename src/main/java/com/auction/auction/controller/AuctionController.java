package com.auction.auction.controller;


import com.auction.auction.model.BidRequestDto;
import com.auction.auction.model.BidResponseDto;
import com.auction.auction.model.ItemRequestDto;
import com.auction.auction.model.ItemResponseDto;
import com.auction.auction.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController (AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @Operation(summary = "Create Auction Item", description = "Creates a new item for auction")
    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        ItemResponseDto itemResponseDto = auctionService.createItem(itemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseDto);
    }

    @Operation(summary = "Get Active Auction Items", description = "Retrieves all currently open auction items")
    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllActiveItems() {
        return ResponseEntity.ok(auctionService.getAllActiveItems());
    }

    @Operation(summary = "Get Item Details", description = "Get details of an auction item by ID")
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long itemId) {
        return ResponseEntity.ok(auctionService.getItemById(itemId));
    }

    @Operation(summary = "Place a Bid", description = "Places a bid on an auction item")
    @PostMapping("/{itemId}/bids")
    public ResponseEntity<BidResponseDto> placeBid(@PathVariable Long itemId, @RequestBody BidRequestDto bidRequestDto) {
        BidResponseDto bidResponseDto = auctionService.placeBid(itemId, bidRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bidResponseDto);
    }

    @Operation(summary = "Get Bids", description = "Retrieves all bids")
    @GetMapping("/bids")
    public ResponseEntity<List<BidResponseDto>> getAllBids() {
        return ResponseEntity.ok(auctionService.getAllBids());
    }

    @Operation(summary = "Get Winning Bid", description = "Retrieves the winning bid for a closed auction item")
    @GetMapping("/{itemId}/winner")
    public ResponseEntity<BidResponseDto> getWinner(@PathVariable Long itemId){
        return auctionService.getWinningBid(itemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
