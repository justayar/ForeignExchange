package com.canemreayar.foreignexchange;

import com.canemreayar.foreignexchange.config.ApplicationProperties;
import com.canemreayar.foreignexchange.service.RatesApiService;
import com.canemreayar.foreignexchange.service.RatesApiServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ForeignExchangeApplication {

	@Bean
	public RestTemplate restTemplate(){ return new RestTemplate();}

	@Bean
	@ConfigurationProperties
	public ApplicationProperties applicationProperties(){ return new ApplicationProperties();}

	@Bean
	public RatesApiService ratesApiService() { return new RatesApiServiceImpl();}


	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeApplication.class, args);
	}

}
