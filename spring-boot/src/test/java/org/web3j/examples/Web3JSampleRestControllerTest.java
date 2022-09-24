package org.web3j.examples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class Web3JSampleRestControllerTest {

    private MockMvc mockMvc;
    private final Web3jSampleService web3jSampleService = Mockito.mock(Web3jSampleService.class);
    private final Web3jSampleRestController web3JSampleRestController = new Web3jSampleRestController(web3jSampleService);

    @Before
    public void initMockMvc() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(web3JSampleRestController)
                .setControllerAdvice(ExceptionHandler.class)
                .build();
    }

    @Test
    public void testReturnClientVersion() throws Exception {
        final String clientVersion = "Ganache/v7.4.3/EthereumJS TestRPC/v7.4.3/ethereum-js";
        Mockito.when(web3jSampleService.getClientVersion()).thenReturn(clientVersion);
        mockMvc.perform(get("/client/version"))
                .andExpect(status().isOk())
                .andExpect(content().string(clientVersion));
    }

    @Test
    public void testHandleIoException() throws Exception {
        Mockito.when(web3jSampleService.getClientVersion()).thenThrow(new IOException());
        mockMvc.perform(get("/client/version"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal I/O error."));
    }

    @Test
    public void testBasePath() {
        final RequestMapping annotation = Web3jSampleRestController.class.getAnnotation(RequestMapping.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).containsExactly("/client");
    }

    @Test
    public void testVersionPath() throws NoSuchMethodException {
        final Class<Web3jSampleRestController> c = Web3jSampleRestController.class;
        final Method getVersionMethod = c.getMethod("getClientVersion");
        assertThat(getVersionMethod).isNotNull();

        final GetMapping annotation = getVersionMethod.getAnnotation(GetMapping.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).containsExactly("/version");
    }

}
