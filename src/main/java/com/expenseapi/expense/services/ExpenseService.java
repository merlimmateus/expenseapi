package com.expenseapi.expense.services;

import com.expenseapi.expense.dto.ExpenseDTO;
import com.expenseapi.expense.models.Expense;
import com.expenseapi.expense.models.User;
import com.expenseapi.expense.repositories.ExpenseRepository;
import com.expenseapi.expense.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ExpenseDTO> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        User user = userRepository.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setIsFixed(expenseDTO.getIsFixed());
        expense.setDate(expenseDTO.getDate());
        expense = expenseRepository.save(expense);

        return convertToDTO(expense);
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getIsFixed(),
                expense.getDate(),
                expense.getCreatedAt(),
                expense.getUpdatedAt(),
                expense.getUser().getId()
        );
    }
}

