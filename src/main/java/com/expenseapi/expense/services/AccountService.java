package com.expenseapi.expense.services;

import com.expenseapi.expense.dto.AccountDTO;
import com.expenseapi.expense.dto.ExpenseDTO;
import com.expenseapi.expense.models.Account;
import com.expenseapi.expense.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountDTO(
                        account.getId(),
                        account.getBank(),
                        account.getAccountType(),
                        account.getBalance(),
                        account.getDescription(),
                        account.getCreatedAt(),
                        account.getUpdatedAt(),
                        account.getExpenses().stream()
                                .map(expense -> new ExpenseDTO(
                                        expense.getId(),
                                        expense.getAmount(),
                                        expense.getDescription(),
                                        expense.getIsFixed(),
                                        expense.getCreatedAt(),
                                        expense.getUpdatedAt()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account account) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existingAccount.setBank(account.getBank());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setDescription(account.getDescription());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
