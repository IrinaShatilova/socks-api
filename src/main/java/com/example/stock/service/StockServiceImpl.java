package com.example.stock.service;

import com.example.stock.dto.QuantityDTO;
import com.example.stock.dto.SocksDTO;
import com.example.stock.exception.ApiException;
import com.example.stock.model.Socks;
import com.example.stock.repository.ColorRepository;
import com.example.stock.repository.CottonPartRepository;
import com.example.stock.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final SocksRepository socksRepository;
    private final ColorRepository colorRepository;
    private final CottonPartRepository cottonPartRepository;

    @Autowired
    public StockServiceImpl(SocksRepository socksRepository,
                            ColorRepository colorRepository,
                            CottonPartRepository cottonPartRepository) {
        this.socksRepository = socksRepository;
        this.colorRepository = colorRepository;
        this.cottonPartRepository = cottonPartRepository;
    }

    @Override
    public void addSocks(SocksDTO socks) {
        if (socks.getQuantity() < 0) {
            throw new ApiException(400, "Параметры запроса отсутствуют или имеют некорректный формат");
        }
        updatedSocks(socks);

    }

    @Override
    public void sellSocks(SocksDTO socks) {
        if (socks.getQuantity() > 0) {
            throw new ApiException(400, "Параметры запроса отсутствуют или имеют некорректный формат");
        }
        updatedSocks(socks);
    }

    @Override
    public QuantityDTO getStockInfo(String color, String operation, int cottonPart) {

        switch (operation) {
            case ("moreThan"):
                return getStockInfoMoreThan(color, cottonPart);
            case ("lessThan"):
                return getStockInfoLessThan(color, cottonPart);
            case ("equal"):
                return getStockInfoEqual(color, cottonPart);
            default:
                throw new ApiException(400, "Параметры запроса отсутствуют или имеют некорректный формат");
        }
    }


    private void updatedSocks(SocksDTO socks) {
        Integer colorId = colorRepository.findColorByName(socks.getColor()).getColorId();
        Integer cottonPartId = cottonPartRepository.findCottonPartByName(socks.getCottonPart()).getCottonPartId();

        Socks existingSocks = socksRepository.findSocksByColorIdAndCottonPartId(colorId, cottonPartId);

        if (existingSocks != null) {
            // Если сущность уже существует, обновляем количество
            existingSocks.setQuantity(existingSocks.getQuantity() + socks.getQuantity());
            socksRepository.save(existingSocks);
        } else {
            // Иначе создаем новую сущность
            Socks newSocks = new Socks();
            newSocks.setColor(colorRepository.findColorByName(socks.getColor()));
            newSocks.setCottonPart(cottonPartRepository.findCottonPartByName(socks.getCottonPart()));
            newSocks.setQuantity(socks.getQuantity());
            socksRepository.save(newSocks);
        }
    }


    private QuantityDTO getStockInfoEqual(String color, int cottonPart) {
        try {
            Integer colorId = colorRepository.findColorByName(color).getColorId();
            Integer cottonPartId = cottonPartRepository.findCottonPartByName(cottonPart).getCottonPartId();

            Socks socks = socksRepository.findSocksByColorIdAndCottonPartId(colorId, cottonPartId);
            return new QuantityDTO(socks.getQuantity());
        } catch (RuntimeException e) {
            return new QuantityDTO(0);
        }
    }

    private QuantityDTO getStockInfoMoreThan(String color, int cottonPart) {
        try {
            Integer colorId = colorRepository.findColorByName(color).getColorId();
            Socks socks = socksRepository.getStockInfoMoreThan(colorId, cottonPart);
            return new QuantityDTO(socks.getQuantity());
        } catch (RuntimeException e) {
            return new QuantityDTO(0);
        }

    }

    private QuantityDTO getStockInfoLessThan(String color, int cottonPart) {
        try {
            Integer colorId = colorRepository.findColorByName(color).getColorId();
            Socks socks = socksRepository.getStockInfoLessThan(colorId, cottonPart);
            return new QuantityDTO(socks.getQuantity());
        } catch (RuntimeException e) {
            return new QuantityDTO(0);
        }

    }


}
