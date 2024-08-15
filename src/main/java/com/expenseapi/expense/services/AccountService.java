package com.expenseapi.expense.services;

import com.expenseapi.expense.dto.AccountDTO;
import com.expenseapi.expense.models.Account;
import com.expenseapi.expense.models.User;
import com.expenseapi.expense.repositories.AccountRepository;
import com.expenseapi.expense.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public List<AccountDTO> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        User user = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setBank(accountDTO.getBank());
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setDescription(accountDTO.getDescription());

        account = accountRepository.save(account);
        return convertToDTO(account);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existingAccount.setBank(accountDTO.getBank());
        existingAccount.setAccountType(accountDTO.getAccountType());
        existingAccount.setBalance(accountDTO.getBalance());
        existingAccount.setDescription(accountDTO.getDescription());

        return convertToDTO(accountRepository.save(existingAccount));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private AccountDTO convertToDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getBank(),
                account.getAccountType(),
                account.getBalance(),
                account.getDescription(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getUser().getId()
        );
    }
}
