package com.expenseapi.expense.repositories;

import com.expenseapi.expense.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}