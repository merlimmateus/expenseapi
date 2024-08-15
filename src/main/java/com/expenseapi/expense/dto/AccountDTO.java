package com.expenseapi.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    private String bank;
    private String accountType;
    private BigDecimal balance;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ExpenseDTO> expenses;
}