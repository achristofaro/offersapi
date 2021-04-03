package br.com.api.offers.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.api.offers.model.Offer;
import br.com.api.offers.model.vo.OfferResponse;
import br.com.api.offers.model.vo.OfferQty;

@Repository
public interface IOfferService {

    OfferResponse<OfferQty> getRecordCount(Date date);

    OfferResponse<Offer> getById(String id, Date date);
    
    OfferResponse<Offer> getByDate(Date date, List<String> id);

}