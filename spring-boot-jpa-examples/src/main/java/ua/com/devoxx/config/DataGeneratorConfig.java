package ua.com.devoxx.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ua.com.devoxx.repository.AccountRepository;
import ua.com.devoxx.util.data.TestDataGenerator;

@Configuration
public class DataGeneratorConfig implements InitializingBean {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void afterPropertiesSet() {
        accountRepository.saveAll(TestDataGenerator.generateAccountList(10));
    }
}
