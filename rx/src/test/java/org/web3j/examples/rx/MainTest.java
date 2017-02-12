package org.web3j.examples.rx;

import org.junit.Before;
import org.junit.Test;

/**
 * Runner for individual examples.
 */
public class MainTest {

    private Main main;

    @Before
    public void setUp() {
        main = new Main();
    }

    @Test
    public void runSimpleFilterExample() throws Exception {
        main.simpleFilterExample();
    }

    @Test
    public void runBlockInfoExample() throws Exception {
        main.blockInfoExample();
    }

    @Test
    public void runCountingEtherExample() throws Exception {
        main.countingEtherExample();
    }

    @Test
    public void runClientVersionExample() throws Exception {
        main.clientVersionExample();
    }
}
