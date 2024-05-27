package com.dbe.tradestore.controller;

import com.dbe.tradestore.model.Trade;
import com.dbe.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping
    public Trade saveTrade(@RequestBody Trade trade){
        return tradeService.saveTrade(trade);
    }
}
