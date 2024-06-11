package ag.agriconnectactionneurs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AgriconnectActionneursApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgriconnectActionneursApplication.class, args);
    }

}
