package br.com.api.offers.repository;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import br.com.api.offers.model.vo.OfferResponse;

@Repository
public interface IOfferService {

    OfferResponse<?> getRecordCount(Date date);

    OfferResponse<?> getByCpf(String cpf, Date date);
    
    OfferResponse<?> getByDate(Date date, List<String> cpfs);

}