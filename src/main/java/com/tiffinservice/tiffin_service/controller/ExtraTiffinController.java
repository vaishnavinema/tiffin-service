package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.extra.ExtraTiffin;
import com.tiffinservice.tiffin_service.service.ExtraTiffinService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extra-tiffins")
public class ExtraTiffinController {

    private final ExtraTiffinService service;

    public ExtraTiffinController(ExtraTiffinService service) {
        this.service = service;
    }

    @PostMapping
    public ExtraTiffin addExtraTiffin(@Valid @RequestBody ExtraTiffin extraTiffin) {
        return service.saveExtraTiffin(extraTiffin);
    }

    @GetMapping
    public List<ExtraTiffin> getAllExtraTiffins() {
        return service.getAllExtraTiffins();
    }
}