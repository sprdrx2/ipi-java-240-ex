package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class SpringConfig {

        public static void main(String[] args) {
            SpringApplication.run(SpringConfig.class, args);
        }

        @Bean(name="bitcoinServiceNoCache")
        @Scope("singleton")
        public BitcoinService bitcoinServiceNoCache() {
            BitcoinService bitcoinService = new BitcoinService();
            bitcoinService.setForceRefresh(true);
            return bitcoinService;
        }

        @Bean(name="bitcoinServiceCached")
        @Scope("singleton")
        public BitcoinService bitcoinServiceCached() {
            BitcoinService bitcoinService = new BitcoinService();
            bitcoinService.setForceRefresh(false);
            return bitcoinService;
        }
}

