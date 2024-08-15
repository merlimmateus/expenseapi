package com.expenseapi.expense.repositories;

import com.expenseapi.expense.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}