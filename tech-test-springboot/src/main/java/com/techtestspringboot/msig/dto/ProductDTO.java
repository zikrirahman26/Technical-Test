package com.techtestspringboot.msig.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private Long price;
    private String description;
    private String status;
}
