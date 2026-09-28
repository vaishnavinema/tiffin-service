package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.extra.ExtraTiffin;
import com.tiffinservice.tiffin_service.repository.ExtraTiffinRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExtraTiffinService {

    private final ExtraTiffinRepository repository;

    public ExtraTiffinService(ExtraTiffinRepository repository) {
        this.repository = repository;
    }

    public ExtraTiffin saveExtraTiffin(ExtraTiffin extraTiffin) {

        BigDecimal pricePerTiffin = BigDecimal.valueOf(70);

        BigDecimal totalAmount =
                pricePerTiffin.multiply(
                        BigDecimal.valueOf(extraTiffin.getQuantity())
                );

        extraTiffin.setTotalAmount(totalAmount);

        return repository.save(extraTiffin);
    }

    public List<ExtraTiffin> getAllExtraTiffins() {
        return repository.findAll();
    }
}