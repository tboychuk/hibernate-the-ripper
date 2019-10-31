package ua.com.devoxx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.devoxx.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Long> {
}

