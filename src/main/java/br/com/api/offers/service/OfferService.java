package br.com.api.offers.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.api.offers.config.CustomProperties;
import br.com.api.offers.config.Util;
import br.com.api.offers.exceptionhandling.NotFoundException;
import br.com.api.offers.model.Offer;
import br.com.api.offers.model.vo.Links;
import br.com.api.offers.model.vo.OfferResponse;
import br.com.api.offers.repository.IOfferService;
import br.com.api.offers.model.vo.OfferQty;

@Service
public class OfferService implements IOfferService {
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CustomProperties prop;

    @Override
    public OfferResponse<?> getRecordCount(Date date){

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", Util.formatSimpleDate(date));

        final String sql  = prop.getQueryOffersQty();
        
        OfferQty qty = namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> new OfferQty(rs.getLong("qty")));

        if(qty == null){
            throw new NotFoundException(String.format(prop.getNodatafoundDate(), Util.formatSimpleDate(date)));
        }
        return new OfferResponse<>(qty, Links.Builder()
                                            .self(prop.getHttpRequest().getRequestURL().toString())
                                            .first("")
                                            .prev("")
                                            .next("")
                                            .last("").build());
    }

	@Override
    public OfferResponse<?> getByCpf(String cpf, Date date) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", Util.formatSimpleDate(date));
        params.addValue("cpf", cpf);

        final String sql = prop.getQueryOffersByCpf();   
        final String dt = Util.formatSimpleDate(date);
                
        List<Offer> offers = namedParameterJdbcTemplate.query(sql, params,
                                            (rs, rowNum) ->
                                                new Offer (
                                                        rs.getLong("offerId"),
                                                        rs.getLong("seqNumber"),
                                                        rs.getString("accountName"),
                                                        rs.getString("cpf"), 
                                                        rs.getString("firstName"), 
                                                        rs.getString("lastName"), 
                                                        rs.getDate("txDate"), 
                                                        rs.getBigDecimal("positionPrice"))
                                    );
        if(offers.isEmpty()){
            throw new NotFoundException(String.format(prop.getNodatafoundDateCpf(), dt, cpf));
            
        }
        return new OfferResponse<>(offers, Links.Builder()
                                            .self(prop.getHttpRequest().getRequestURL().toString())
                                            .first("")
                                            .prev("")
                                            .next("")
                                            .last("").build());
    }

    @Override
    public OfferResponse<?> getByDate(Date date, List<String> cpfs) {
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", Util.formatSimpleDate(date));
        params.addValue("cpf", cpfs);

        final String sql = prop.getQueryOffersByDate();
        final String dt = Util.formatSimpleDate(date);
 
        List<Offer> offers = namedParameterJdbcTemplate.query(sql, params,
                                                            (rs, rowNum) ->                                    
                                                                new Offer (
                                                                        rs.getLong("offerId"),
                                                                        rs.getLong("seqNumber"),
                                                                        rs.getString("accountName"),
                                                                        rs.getString("cpf"), 
                                                                        rs.getString("firstName"), 
                                                                        rs.getString("lastName"), 
                                                                        rs.getDate("txDate"), 
                                                                        rs.getBigDecimal("positionPrice"))
                                    );
        if(offers.isEmpty()){
            throw new NotFoundException(String.format(prop.getNodatafoundDateCpf(), dt, cpfs));
        }
        return new OfferResponse<>(offers, Links.Builder()
                                            .self(prop.getHttpRequest().getRequestURL().toString())
                                            .first("")
                                            .prev("")
                                            .next("")
                                            .last("").build());
    }

}