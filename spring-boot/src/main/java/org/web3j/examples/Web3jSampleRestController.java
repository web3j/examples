package org.web3j.examples;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/client")
public class Web3jSampleRestController {

    private final Web3jSampleService web3jSampleService;

    public Web3jSampleRestController(final Web3jSampleService web3jSampleService) {
        this.web3jSampleService = web3jSampleService;
    }

    @GetMapping("/version")
    @ResponseBody
    public String getClientVersion() throws IOException {
        return web3jSampleService.getClientVersion();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class})
    public String ioError() {
        return "Internal I/O error.";
    }

}
