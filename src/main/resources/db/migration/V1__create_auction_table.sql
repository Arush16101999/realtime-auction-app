
CREATE TABLE IF NOT EXISTS item (
    item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    starting_price DECIMAL(10,2),
    highest_bid DECIMAL(10,2),
    auction_end_time DATETIME,
    status VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS bid (
    bid_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    item_id BIGINT,
    bidder_name VARCHAR(255),
    amount DECIMAL(10,2),
    bid_time DATETIME,
    CONSTRAINT fk_bid_item_id FOREIGN KEY (item_id) REFERENCES item(item_id)
);