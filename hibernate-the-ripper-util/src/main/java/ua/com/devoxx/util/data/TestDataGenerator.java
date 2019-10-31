package ua.com.devoxx.util.data;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import ua.com.devoxx.entity.Account;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDataGenerator {

    public static List<Account> generateAccountList(int size) {
        return Stream.generate(TestDataGenerator::generateAccount)
                .limit(size)
                .collect(Collectors.toList());
    }

    public static Account generateAccount() {
        Fairy fairy = Fairy.create();
        Person randomPerson = fairy.person();
        return convertPersonToAccount(randomPerson);
    }

    private static Account convertPersonToAccount(Person person) {
        Account account = new Account();
        account.setFirstName(person.getFirstName());
        account.setLastName(person.getLastName());
        account.setEmail(person.getEmail());
        return account;
    }
}