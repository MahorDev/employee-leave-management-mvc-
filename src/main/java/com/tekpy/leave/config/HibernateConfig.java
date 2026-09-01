package com.tekpy.leave.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernatePersistenceProvider provider =
                new HibernatePersistenceProvider();

        EntityManagerFactory factory =
                provider.createEntityManagerFactory(
                        "leavePU",
                        null);

        if (factory == null) {
            throw new IllegalStateException(
                    "Could not create EntityManagerFactory for leavePU");
        }

        return factory;
    }
}
