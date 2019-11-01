package ua.com.devoxx;

import ua.com.devoxx.entity.Account;
import ua.com.devoxx.util.data.TestDataGenerator;

import java.util.List;

import static ua.com.devoxx.util.jpa.JpaUtil.close;
import static ua.com.devoxx.util.jpa.JpaUtil.init;
import static ua.com.devoxx.util.jpa.JpaUtil.performWithinPersistenceContext;

// Won't be presented during the talk
public class LoadListOfEntitiesExample {

    public static void main(String[] args) {
        init();
        try {
            loadEntityList();
        } finally {
            close();
        }
    }

    private static void loadEntityList() {
        List<Account> accountList = TestDataGenerator.generateAccountList(10);
        performWithinPersistenceContext(entityManager -> accountList.forEach(entityManager::persist));
        Long targetId = accountList.get(0).getId();

        performWithinPersistenceContext(entityManager -> {
            Account targetAccount = entityManager.find(Account.class, targetId);
            List<Account> foundAccountList = entityManager
                    .createQuery("select a from Account a", Account.class)
                    .getResultList();
            boolean match = foundAccountList.stream().anyMatch(a -> a == targetAccount);
            System.out.printf("> Account match: %s%n", match);
        });

    }
}
