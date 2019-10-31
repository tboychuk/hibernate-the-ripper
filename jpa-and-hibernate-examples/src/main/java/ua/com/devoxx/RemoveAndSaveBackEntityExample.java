package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

public class RemoveAndSaveBackEntityExample {

    public static void main(String[] args) {
        init();
        try {
            removeEntityAndSaveItBack();
        } finally {
            close();
        }
    }

    private static void removeEntityAndSaveItBack() {
        Account account = TestDataGenerator.generateAccount();
        performWithinPersistenceContext(entityManager -> entityManager.persist(account));

        performWithinPersistenceContext(entityManager -> {
            Account managedAccount = entityManager.find(Account.class, account.getId());
            System.out.println("Removing entity");
            entityManager.remove(managedAccount);
            System.out.println("Persisting entity again");
            entityManager.persist(managedAccount);
        });
    }
}
