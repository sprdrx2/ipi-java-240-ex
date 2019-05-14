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
public class SpringConfig implements CommandLineRunner {

        @Autowired
        private ProduitManager pm;

        @Resource(name = "bitcoinServiceFresh")
        private BitcoinService bitcoinServiceFresh;

        public static void main(String[] args) {
            SpringApplication.run(SpringConfig.class, args);
        }

        public void run(String... args) throws IOException {
            //ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

            //ProduitManager pm = context.getBean("produitManager", ProduitManager.class);
            //BitcoinService bitcoinServiceCached = context.getBean("bitcoinServiceCached", BitcoinService.class);
            //BitcoinService bitcoinServiceFresh  = context.getBean("bitcoinServiceFresh", BitcoinService.class);
            //WebPageManager webPageManager = context.getBean("webPageManager", WebPageManager.class);


            System.out.println("Bienvenue !");
            while(true){
                System.out.println("Vous souhaitez : ");
                System.out.println("1 - Connaître le cours du bitcoin");
                System.out.println("2 - Ajouter un produit au catalogue");
                System.out.println("3 - Voir tous les produits du catalogue");
                System.out.println("4 - Voir les détails d'un produit");
                System.out.println("5 - Initialiser le catalogue");
                System.out.println("0 - Quitter");

                Scanner scanner = new Scanner(System.in);
                int saisie = scanner.nextInt();
                switch (saisie){
                    case 1:
                        System.out.println("1 BTC = " + bitcoinServiceFresh.getBitcoinRate() + " €");
                        break;
                    case 2:
                        pm.ajouterProduit();
                        break;
                    case 3:
                        pm.afficherTousLesProduits();
                        break;
                    case 4:
                        System.out.println("Quel numéro de produit ?");
                        pm.afficherDetailProduit(scanner.nextInt());
                        break;
                    case 5:
                        pm.initialiserCatalogue();
                        break;
                    case 0:
                        System.out.println("Au revoir !");
                        return;
                }
            }
        }

        @Value("${ipi.bitcoin_service.cache}")
        private Boolean bitcoinForceRefresh;

        @Bean(name="bitcoinServiceFresh")
        @Scope("singleton")
        public BitcoinService bitcoinServiceFresh() {
            BitcoinService bitcoinService = new BitcoinService();
            bitcoinService.setForceRefresh(bitcoinForceRefresh);
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

