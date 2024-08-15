package com.expenseapi.expense.controllers;

import com.expenseapi.expense.dto.ExpenseDTO;
import com.expenseapi.expense.models.Expense;
import com.expenseapi.expense.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.createExpense(expenseDTO));
    }
}