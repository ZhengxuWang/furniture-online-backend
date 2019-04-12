package com.mercury.shop;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Value("${jms.broker-url}")
    private String jmsBrokerUrl;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;
    
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsBrokerUrl);
        connectionFactory.setUserName(jmsUser);
        connectionFactory.setPassword(jmsPassword);
//        connectionFactory.setTrustedPackages(Arrays.asList("com.mercury"));
        connectionFactory.setTrustAllPackages(true);
        return connectionFactory;
    }
    @Bean(name = "storageListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("3-10");
        factory.setRecoveryInterval(1000L);
        return factory;
    }
}
