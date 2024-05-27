package com.dbe.tradestore.validator;

import com.dbe.tradestore.exception.InvalidTradeException;
import com.dbe.tradestore.model.Trade;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeValidator {
    public void validateTrade(Trade trade){
        if (trade.getMaturityDate().isBefore(LocalDate.now())){
            throw new InvalidTradeException("Trade maturity date cannot be earlier");
        }
    }
}
