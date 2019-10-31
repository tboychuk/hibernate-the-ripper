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

//    @Transactional // session started
    public void removeAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow();
        System.out.printf("> Removing account: %s%n", account);
        System.out.println("> Expecting SELECT");
        accountRepository.delete(account);
    }// session is closed
}
