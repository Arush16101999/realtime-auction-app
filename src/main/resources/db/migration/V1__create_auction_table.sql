
CREATE TABLE item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    starting_price DECIMAL(10,2),
    highest_bid DECIMAL(10,2),
    auction_end_time DATETIME,
    status VARCHAR(20)
);