package com.profinch.fincluez.fincluezcasatransformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.profinch.fincluez"})
public class FincluezCasaTransformerApplication {


    public static void main(String[] args) {
        SpringApplication.run(FincluezCasaTransformerApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FincluezCasaTransformerApplication.class);
    }

}
