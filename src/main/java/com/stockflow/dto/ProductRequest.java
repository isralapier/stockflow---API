package com.stockflow.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre superar los 100 caracteres")
        String name,

        @NotBlank(message = "El SKU es obligatorio")
        @Size(max = 30, message = "El SKU no puede superar los 30 caracteres")
        String sku,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero")
        BigDecimal price,

        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock

) {
}
