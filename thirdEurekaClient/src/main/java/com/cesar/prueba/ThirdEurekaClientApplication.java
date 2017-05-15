package com.cesar.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@RestController
public class ThirdEurekaClientApplication {

	@Autowired
	private EurekaClient eurekaClient;
	
	 @Autowired
	 private GreetingClient greetingClient;
	 
	public static void main(String[] args) {
		SpringApplication.run(ThirdEurekaClientApplication.class, args);
		
	}
	

    @RequestMapping("/get-greeting")
    public String greeting() {
        return greetingClient.greeting();
    }
    
    @RequestMapping("/otro")
    public String otro() {
    	doRequest();
    	String a = greetingClient.greeting();
        return "hoslaaa";
    }
	 
	private  void doRequest() {
	    Application application = eurekaClient.getApplication("a-bootiful-client");
	    InstanceInfo instanceInfo = application.getInstances().get(0);
	    String hostname = instanceInfo.getHostName();
	    int port = instanceInfo.getPort();
	}
}
