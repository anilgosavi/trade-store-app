package com.dbe.tradestore.controller;

import com.dbe.tradestore.model.Trade;
import com.dbe.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping
    public Trade saveTrade(@RequestBody Trade trade){
        return tradeService.saveTrade(trade);
    }

    @GetMapping
    public List<Trade> getTrades(){
        return tradeService.getTrades();
    }
}
