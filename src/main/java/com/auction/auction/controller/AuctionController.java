package com.auction.auction.controller;

import com.auction.auction.entity.Bid;
import com.auction.auction.entity.Item;
import com.auction.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Item CreateItem(@RequestBody Item item) {
        return auctionService.createItem(item);
    }

    @GetMapping
    public List<Item> getAllActiveItems() {
        return auctionService.getAllActiveItems();
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return auctionService.getItemById(itemId).orElseThrow();
    }

    @PostMapping("/{itemId}/bids")
    public Bid placeBid(@PathVariable Long itemId, @RequestBody Bid bid) {
        return auctionService.placeBid(itemId, bid);
    }

    @GetMapping("/{itemId}/winner")
    public ResponseEntity<Bid> getWinner(@PathVariable Long itemId){
        return auctionService.getWinningBid(itemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
