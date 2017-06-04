package com.websystique.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by nicu on 6/3/2017.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.websystique.springmvc.configuration"})
@PropertySource(value = {"classpath:email.properties"})
public class EmailConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public Session emailSession() {
        Properties props = new Properties();

        props.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
        props.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        props.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));
        props.put("mail.fromAddress", environment.getRequiredProperty("mail.fromAddress"));
        props.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        String username = environment.getRequiredProperty("mail.username");
        String password = environment.getRequiredProperty("mail.password");

        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
