package com.ipiecoles.java.java240;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

        @Bean(name="bitcoinServiceFresh")
        @Scope("singleton")
        public BitcoinService bitcoinServiceFresh() {
            BitcoinService bitcoinService = new BitcoinService();
            bitcoinService.setForceRefresh(true);
            bitcoinService.setWebPageManager(webPageManager());
            return bitcoinService;
        }
        @Bean(name="bitcoinServiceCached")
        @Scope("singleton")
        public BitcoinService bitcoinServiceCached() {
            BitcoinService bitcoinService = new BitcoinService();
            bitcoinService.setForceRefresh(false);
            bitcoinService.setWebPageManager(webPageManager());
            return bitcoinService;
        }

        @Bean(name="webPageManager")
        @Scope("singleton")
        public WebPageManager webPageManager() {
            return new WebPageManager();
        }

        @Bean(name="produitManager")
        @Scope("singleton")
        public ProduitManager produitManager() {
            ProduitManager produitManager = new ProduitManager();
            produitManager.setWebPageManager(webPageManager());
            produitManager.setBitcoinService(bitcoinServiceCached());
            return produitManager;
        }
}

