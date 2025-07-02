# Real-Time Auction System (Spring Boot & MySQL)
 Organizers can list items, and users can place bids. The system automatically closes auctions and determines the highest bidder as the winner.

## setup & Getting Started
- Java 17
- Maven
- MySQL workbench (change the password and root in application.properties) jdbc:mysql://localhost:3306/auction_db
- Flyway Migration
- lombok

# Spring Boot Components
1. Controller - REST API Auction Controller
2. Service - Auction Service
3. Entity - Bid , Item
4. Enums - Auction Status
5. Model - Request & Response DTOs (Bid & Item)
6. Repository - Bid & Item
7. Scheduler - Auction Scheduler to auction closing and concurrency considerations   

# Features
1. Create a new item for auction
2. List all currently active auction items
3. Get details for a specific auction item, including its current highest bid.
4. Place a bid on an item
5. Get the winning bid details for the item if the auction is closed
6. Implemented Scheduling to automate the auction OPEN & CLOSE time (60 sec)
7. Global exception handlers

# API Documentation
### Swagger
![Screenshot 2025-06-10 202417](https://github.com/user-attachments/assets/8ad162ad-2539-4af5-9cec-4538c2e51bf6)

### Postman Testing

![Screenshot 2025-06-10 202950](https://github.com/user-attachments/assets/9f1d77e2-c57e-470d-8035-7920cda13b4a)

![Screenshot 2025-06-10 220030](https://github.com/user-attachments/assets/68fff39f-bf22-4297-bf8a-a3c8899ae4ce)

![Screenshot 2025-06-10 220312](https://github.com/user-attachments/assets/2f898ff9-b4a5-4320-ba56-f483e409490b)

![Screenshot 2025-06-10 220219](https://github.com/user-attachments/assets/22193a6d-22f7-4daa-a47f-17ee93a3354f)

![Screenshot 2025-06-10 220522](https://github.com/user-attachments/assets/9a8f97e9-8ae9-442d-9d17-47cfd8f59392)


# Database Image

![Screenshot 2025-06-10 202636](https://github.com/user-attachments/assets/3e83593f-d97f-46d4-b595-bedc7b61cbbd)
![Screenshot 2025-06-10 202721](https://github.com/user-attachments/assets/61a9ff48-276e-4b7d-ad84-5ea084089ad6)
