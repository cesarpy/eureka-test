package com.cesar.prueba;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("a-bootiful-client")
public interface GreetingClient {
    @RequestMapping("/user")
    String greeting();
}
