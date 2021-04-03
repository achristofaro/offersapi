package br.com.api.offers.repository;


import br.com.api.offers.model.Offer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    
}