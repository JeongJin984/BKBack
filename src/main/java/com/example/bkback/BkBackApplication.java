package com.example.bkback;

import com.example.bkback.common.SimpleListener;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BkBackApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.addListeners(new SimpleListener());
        application.run(BkBackApplication.class, args);
    }

    @Bean
    protected Hibernate5Module module() {
        return new Hibernate5Module();
    }

}
