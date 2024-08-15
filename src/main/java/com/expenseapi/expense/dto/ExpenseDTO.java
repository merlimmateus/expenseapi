package com.expenseapi.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseDTO {
    private Long id;
    private BigDecimal amount;
    private String description;
    private Boolean isFixed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}