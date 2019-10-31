package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

public class ActionQueueOrderExample {

    public static void main(String[] args) {
        init();
        try {
            removeEntityAndSameAnotherWithSameEmail();
        } finally {
            close();
        }
    }

    private static void removeEntityAndSameAnotherWithSameEmail() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));

        performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.merge(account);
            System.out.printf("> Removing entity: %s%n", managedAccount);
            entityManager.remove(managedAccount);
            Account newAccount = TestDataGenerator.generateAccount();
            newAccount.setEmail(account.getEmail());
            System.out.printf("> Persisting entity: %s%n", newAccount);
            entityManager.persist(newAccount);
        });
    }
}
