package com.stockflow.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ProductResponse(

        Long id,
        String name,
        String sku,
        BigDecimal price,
        int stock


) {}
