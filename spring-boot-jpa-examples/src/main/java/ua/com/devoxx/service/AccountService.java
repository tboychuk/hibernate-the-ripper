package ua.com.devoxx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.devoxx.entity.Account;
import ua.com.devoxx.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public void removeAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow();
        System.out.printf("> Removing account: %s%n", account);
        accountRepository.delete(account);
    }
}
