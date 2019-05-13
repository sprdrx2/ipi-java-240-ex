package com.ipiecoles.java.java240;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.ipiecoles.java.java240")
@PropertySource("classpath:app.properties")
public class SpringConfig {

        @Bean(name="bitcoinServiceFresh")
        @Scope("singleton")
        public BitcoinService bitcoinServiceFresh() {
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

