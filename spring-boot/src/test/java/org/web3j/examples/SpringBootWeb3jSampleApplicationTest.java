package org.web3j.examples;

import org.apache.http.conn.HttpHostConnectException;
import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWeb3jSampleApplicationTest {

    @Autowired
    private Web3jSampleRestController web3JSampleRestController;

    @Autowired
    private Web3jSampleService web3jSampleService;

    @Test
    public void contextLoads() {
        assertThat(web3JSampleRestController).isNotNull();
    }

    // This test will only run if you provide a real Ethereum client for web3j to connect to
    @Test(expected = HttpHostConnectException.class)
    public void testGetClientVersion() throws IOException {
        assertThat(web3jSampleService.getClientVersion()).startsWith("Geth/");
    }
}
