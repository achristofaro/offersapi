package br.com.api.offers.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class Offer{

    @Id         private final Long offerId;
    @JsonIgnore private Long seqNumber;
    @NonNull    private String accountName;
    @NonNull    private String cpf;
    @NonNull    private String firstName;
    @NonNull    private String lastName;
    @NonNull    private Date txDate;
    @NonNull    private BigDecimal positionPrice;
    
}