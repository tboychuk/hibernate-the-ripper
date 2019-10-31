package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

public class InitialExample {

    public static void main(String[] args) {
        init();
        try {
            persistEntity();
        } finally {
            close();
        }
    }

    private static void persistEntity() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));
        System.out.printf("Persisted account: %s%n", account);
    }
}
