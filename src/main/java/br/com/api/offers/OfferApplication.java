package br.com.api.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableAutoConfiguration
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
public class OfferApplication {

	public static void main(final String[] args) {
		SpringApplication.run(OfferApplication.class, args);
	}
}
