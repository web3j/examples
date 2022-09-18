package org.web3j.examples;

import java.io.IOException;

import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

/**
 * Sample service to demonstrate web3j bean being correctly injected.
 */
@Service
public class Web3jSampleService {

    private final Web3j web3j;

    public Web3jSampleService(final Web3j web3j) {
        this.web3j = web3j;
    }

    public String getClientVersion() throws IOException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        return web3ClientVersion.getWeb3ClientVersion();
    }
}
