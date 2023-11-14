package com.example.stock.service;

import com.example.stock.dto.QuantityDTO;
import com.example.stock.dto.SocksDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface StockService {

    void addSocks(SocksDTO socks);

    void sellSocks(SocksDTO socks);

    QuantityDTO getStockInfo(String color, String operation, int cottonPart);

}
