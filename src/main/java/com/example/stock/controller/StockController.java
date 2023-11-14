package com.example.stock.controller;

import com.example.stock.dto.QuantityDTO;
import com.example.stock.dto.SocksDTO;
import com.example.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/income")
    public void addSocks (@RequestBody SocksDTO socks) {
        stockService.addSocks(socks);

           }
    @PostMapping("/outcome")
    public void sellSocks (@RequestBody SocksDTO socks) {
        stockService.sellSocks(socks);
    }

    @GetMapping
    public QuantityDTO getSocksOperation(@RequestParam("color") String color,
                                         @RequestParam("operation") String operation,
                                         @RequestParam("cottonPart") int cottonPart) {
        return stockService.getStockInfo(color,operation,cottonPart);
    }

}
