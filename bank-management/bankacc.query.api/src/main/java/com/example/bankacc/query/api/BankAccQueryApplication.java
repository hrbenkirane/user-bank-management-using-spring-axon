package com.example.bankacc.query.api;

import com.example.bankacc.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
public class BankAccQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccQueryApplication.class, args);
    }

}
