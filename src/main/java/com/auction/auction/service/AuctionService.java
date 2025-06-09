package com.auction.auction.service;

import com.auction.auction.entity.Bid;
import com.auction.auction.entity.Item;
import com.auction.auction.enums.AuctionStatus;
import com.auction.auction.repository.BidRepository;
import com.auction.auction.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private final ItemRepository itemRepository;
    private final BidRepository bidRepository;

    @Autowired
    public AuctionService(ItemRepository itemRepository, BidRepository bidRepository) {
        this.itemRepository = itemRepository;
        this.bidRepository = bidRepository;
    }

    public Item createItem(Item item) {
        item.setHighestBid(item.getStartingPrice());
        return itemRepository.save(item);
    }

    public List<Item> getAllActiveItems() {
        return itemRepository.findByStatus(AuctionStatus.OPEN);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Bid placeBid(Long itemId, Bid bid){
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            if (item.getStatus() == AuctionStatus.OPEN && bid.getAmount().compareTo(item.getHighestBid()) > 0) {
                bid.setBidTime(LocalDateTime.now());
                bid.setItem(item);
                item.setHighestBid(bid.getAmount());
                item.getBids().add(bid);
                itemRepository.save(item);
                return bidRepository.save(bid);
            } else {
                throw new IllegalArgumentException("Bid amount must be higher than the current highest bid.");
            }
        } else {
            throw new IllegalArgumentException("Item not found.");
        }
    }

    public Optional<Bid> getWinningBid(Long itemId) {
//        Optional<Item> itemOptional = itemRepository.findById(itemId);
//        if (itemOptional.isPresent()) {
//            Item item = itemOptional.get();
//            List<Bid> bids = bidRepository.findTopByItemOrderByAmountDesc(item);
//            return bids.isEmpty() ? Optional.empty() : Optional.of(bids.get(0));
//        } else {
//            throw new IllegalArgumentException("Item not found.");
//        }
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found."));
        if (item.getStatus() == AuctionStatus.OPEN) return Optional.empty();
        return bidRepository.findTopByItemOrderByAmountDesc(item).stream().findFirst();
    }

    public void closeExpiredAuctions() {
        LocalDateTime now = LocalDateTime.now();
        List<Item> items = itemRepository.findByStatus(AuctionStatus.OPEN);
        for (Item item : items) {
            if (item.getAuctionEndTime().isBefore(now)) {
                item.setStatus(AuctionStatus.CLOSED);
                itemRepository.save(item);
            }
        }
    }


}
