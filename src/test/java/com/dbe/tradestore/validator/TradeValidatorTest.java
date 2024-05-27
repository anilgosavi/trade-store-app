package com.dbe.tradestore.validator;

import com.dbe.tradestore.exception.InvalidTradeException;
import com.dbe.tradestore.model.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TradeValidatorTest {
    private TradeValidator tradeValidator;

    @BeforeEach
    public void setUp(){
        tradeValidator = new TradeValidator();
    }

    @Test
    public void testValidateTradeWithPastMaturityDate(){
        Trade trade = new Trade();
        trade.setMaturityDate(LocalDate.now().minusDays(1));

        assertThrows(InvalidTradeException.class, ()-> {tradeValidator.validateTrade(trade);});
    }

    @Test
    public void testValidateTradeWithFutureMaturityDate(){
        Trade trade = new Trade();
        trade.setMaturityDate(LocalDate.now().plusDays(1));
        tradeValidator.validateTrade(trade);
    }
}
