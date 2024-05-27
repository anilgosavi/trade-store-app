package com.dbe.tradestore.repository;

import com.dbe.tradestore.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade, String> {
}