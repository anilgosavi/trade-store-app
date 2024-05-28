package com.dbe.tradestore;

import com.dbe.tradestore.model.Trade;
import com.dbe.tradestore.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpireTrades {

    @Autowired
    private TradeRepository tradeRepository;

    @Scheduled(cron = "0 0 1 * * *") //runs at 1am daily
    public void updateTradeExpiry(){
        List<Trade> trades = tradeRepository.findAll();

        LocalDate currentDate = LocalDate.now();

        trades.forEach(trade -> {
            if (trade.getMaturityDate().isBefore(currentDate)){
                trade.setExpired(true);
                tradeRepository.save(trade);
            }
        });
    }
}
