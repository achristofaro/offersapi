package br.com.api.offers.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.Getter;

@Configuration
@Getter
public class CustomProperties {

    @Autowired
    private Environment env;

    @Autowired
    private HttpServletRequest httpRequest;

    @Value("${query.offersqty}")
    private String queryOffersQty;

    @Value("${query.offersbycpf}")
    private String queryOffersByCpf;

    @Value("${query.offersbydate}")
    private String queryOffersByDate;

    @Value("${msg.no_data_found_date}")
    private String nodatafoundDate;

    @Value("${msg.no_data_found_date_cpf}")
    private String nodatafoundDateCpf;

}