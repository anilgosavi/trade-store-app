package com.dbe.tradestore.service;

import com.dbe.tradestore.exception.InvalidTradeException;
import com.dbe.tradestore.model.Trade;
import com.dbe.tradestore.repository.TradeRepository;
import com.dbe.tradestore.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeValidator tradeValidator;

    public Trade saveTrade(Trade trade){
        tradeValidator.validateTrade(trade);
        Optional<Trade> existingTrade = tradeRepository.findById(trade.getId());

        if (existingTrade.isPresent()){
            if (existingTrade.get().getVersion() > trade.getVersion())
                throw new InvalidTradeException("cannot update trade with lower version");
        }
        trade.setCreatedDate(LocalDate.now());
        return tradeRepository.save(trade);
    }

}
