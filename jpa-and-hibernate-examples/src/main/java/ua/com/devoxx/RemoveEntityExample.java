package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performReturningWithinPersistenceContext;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

public class RemoveEntityExample {

    public static void main(String[] args) {
        init();
        try {
            removeEntity();
        } finally {
            close();
        }
    }

    private static void removeEntity() {
        Account account = saveRandomAccount();
        performWithinPersistenceContext(entityManager -> entityManager.remove(account));
    }

    private static Account saveRandomAccount() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        System.out.printf("> Persisted account: %s%n", account);
        return account;
    }
}
