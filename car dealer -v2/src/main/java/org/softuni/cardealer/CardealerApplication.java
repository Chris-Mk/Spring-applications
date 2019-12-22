package org.softuni.cardealer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = "org.softuni.cardealer")
@SpringBootApplication
public class CardealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardealerApplication.class, args);
    }

}
