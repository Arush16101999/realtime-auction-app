package com.auction.auction.service;

import com.auction.auction.entity.Bid;
import com.auction.auction.entity.Item;
import com.auction.auction.enums.AuctionStatus;
import com.auction.auction.model.BidRequestDto;
import com.auction.auction.model.BidResponseDto;
import com.auction.auction.model.ItemRequestDto;
import com.auction.auction.model.ItemResponseDto;
import com.auction.auction.repository.BidRepository;
import com.auction.auction.repository.ItemRepository;
import jakarta.validation.Valid;
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


    public ItemResponseDto createItem(@Valid ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setStartingPrice(itemRequestDto.getStartingPrice());
        item.setHighestBid(itemRequestDto.getStartingPrice());
        item.setAuctionEndTime(itemRequestDto.getAuctionEndTime());
        item.setStatus(AuctionStatus.OPEN);

//        Item savedItem = itemRepository.save(item);
        return mapToItemResponseDto(itemRepository.save(item));
    }


    public List<ItemResponseDto> getAllActiveItems() {
        List<Item> items = itemRepository.findByStatus(AuctionStatus.OPEN);
        return items.stream()
                .map(this::mapToItemResponseDto)
                .toList();
    }


    public ItemResponseDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found."));
        return mapToItemResponseDto(item);
    }



    public BidResponseDto placeBid(Long itemId, @Valid BidRequestDto bidRequestDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found."));
        if (item.getStatus() != AuctionStatus.OPEN) {
            throw new IllegalArgumentException("Auction is not open.");
        }
        if (bidRequestDto.getAmount().compareTo(item.getHighestBid()) <= 0) {
            throw new IllegalArgumentException("Bid amount must be higher than the current highest bid.");
        }

        // Create a new Bid entity from the request DTO
        Bid bid = new Bid();
        bid.setBidderName(bidRequestDto.getBidderName());
        bid.setAmount(bidRequestDto.getAmount());
        bid.setBidTime(LocalDateTime.now());
        bid.setItem(item);

        // Update the item with the new bid
        item.setHighestBid(bidRequestDto.getAmount());
        item.getBids().add(bid);

        itemRepository.save(item); // Save the item to update the highest bid and bids list
        return mapToBidResponseDto(bid); // Save the bid and return the response DTO
    }


    public Optional<BidResponseDto> getWinningBid(Long itemId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found."));
        if (item.getStatus() == AuctionStatus.OPEN) return Optional.empty();

        return bidRepository.findTopByItemOrderByAmountDesc(item)
                .stream()
                .findFirst()
                .map(this::mapToBidResponseDto);

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

    private ItemResponseDto mapToItemResponseDto(Item item) {
        ItemResponseDto dto = new ItemResponseDto();
        dto.setItemId(item.getItemId());
        dto.setName(item.getName());
        dto.setStartingPrice(item.getStartingPrice());
        dto.setHighestBid(item.getHighestBid());
        dto.setAuctionEndTime(item.getAuctionEndTime());
        dto.setStatus(item.getStatus());
        return dto;
    }

    private BidResponseDto mapToBidResponseDto(Bid bid) {
        BidResponseDto dto = new BidResponseDto();
        dto.setBidId(bid.getBidId());
        dto.setBidderName(bid.getBidderName());
        dto.setAmount(bid.getAmount());
        dto.setBidTime(bid.getBidTime());
        dto.setItemId(bid.getItem().getItemId()); //
        return dto;
    }


}
