package com.auction.auction.scheduler;

import com.auction.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AuctionScheduler {

    private final AuctionService auctionService;

    @Autowired
    public AuctionScheduler(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void closeExpiredAuctions() {
        auctionService.closeExpiredAuctions();
    }
}
