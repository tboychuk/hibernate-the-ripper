package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performReturningWithinPersistenceContext;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

public class LoadEntityExample {

    public static void main(String[] args) {
        init();
        try {
            loadEntityById();
        } finally {
            close();
        }
    }

    private static void loadEntityById() {
        Long accountId = saveRandomAccount();
        Account loadedAccount = performReturningWithinPersistenceContext(entityManager -> entityManager.find(Account.class, accountId));
        System.out.printf("Loaded account: %s%n", loadedAccount);
    }

    private static Long saveRandomAccount() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        return account.getId();
    }
}
