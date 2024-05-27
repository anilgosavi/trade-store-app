package com.dbe.tradestore.service;

import com.dbe.tradestore.exception.InvalidTradeException;
import com.dbe.tradestore.model.Trade;
import com.dbe.tradestore.repository.TradeRepository;
import com.dbe.tradestore.validator.TradeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeValidator tradeValidator;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTradeWithLowerVersion() {
        Trade trade = new Trade();
        trade.setId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(LocalDate.now().plusDays(1));

        Trade existingTrade = new Trade();
        existingTrade.setId("T1");
        existingTrade.setVersion(2);

        when(tradeRepository.findById(any(String.class))).thenReturn(Optional.of(existingTrade));

        assertThrows(InvalidTradeException.class, () -> {
            tradeService.saveTrade(trade);
        });
    }

    @Test
    public void testSaveTradeWithHigherVersion() {
        Trade trade = new Trade();
        trade.setId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(LocalDate.now().plusDays(1));

        Trade existingTrade = new Trade();
        existingTrade.setId("T1");
        existingTrade.setVersion(1);

        when(tradeRepository.findById(any(String.class))).thenReturn(Optional.of(existingTrade));
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        tradeService.saveTrade(trade);

        verify(tradeRepository, times(1)).save(trade);
    }
}
