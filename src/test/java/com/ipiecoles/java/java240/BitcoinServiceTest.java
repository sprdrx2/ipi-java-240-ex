package com.ipiecoles.java.java240;

import com.ipiecoles.java.java240.BitcoinService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class BitcoinServiceTest {

    @Value("${ipi.bitcoin_api_url}")
    private String bitcoinRateApiUrl;

    @Mock
    private WebPageManager webPageManager;

    @Resource(name = "bitcoinServiceCached")
    private BitcoinService bitcoinServiceCached;

    @InjectMocks
    @Resource(name = "bitcoinServiceNoCache")
    private BitcoinService bitcoinServiceFresh;

    @Test
    public void testGetBitcoinRateSecondCallUseCache() throws IOException {
        Double premierResultat  = bitcoinServiceCached.getBitcoinRate();
        Double deuxiemeResultat = bitcoinServiceCached.getBitcoinRate();

        Assertions.assertThat(deuxiemeResultat).isEqualTo(premierResultat);
    }

    @Test
    public void testBitcoinRateIsCorrect() throws IOException {
        // given
        Mockito.when(webPageManager.getPageContents(bitcoinRateApiUrl)).thenReturn("{\"EUR\":1664.33}");
        // when

        // then
        Assertions.assertThat(bitcoinServiceFresh.getBitcoinRate()).isEqualTo(1664.33);
    }

}