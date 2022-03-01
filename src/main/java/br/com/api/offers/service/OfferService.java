package br.com.api.offers.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.api.offers.config.CustomProperties;
import br.com.api.offers.config.Util;
import br.com.api.offers.exceptionhandling.NotFoundException;
import br.com.api.offers.model.Offer;
import br.com.api.offers.model.vo.Links;
import br.com.api.offers.model.vo.Meta;
import br.com.api.offers.model.vo.OfferResponse;
import br.com.api.offers.model.vo.Pagination;
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

        final String dt = Util.formatSimpleDate(date);
        final String sql  = prop.getQueryOffersQty();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", dt);

        OfferQty qty = Optional.ofNullable(
                            namedParameterJdbcTemplate.queryForObject(sql, params, 
                                    (rs, rowNum) -> new OfferQty(rs.getLong("qty"))
                                )
                        ).filter(obj -> obj.getQuantity() > 0)
                        .orElseThrow(() -> new NotFoundException(String.format(prop.getNodatafoundDate(), dt)));
        
        return new OfferResponse<>(qty, Links.Builder()
                                        .self(prop.getHttpRequest().getRequestURL().toString())
                                        .first("")
                                        .prev("")
                                        .next("")
                                        .last("").build(), 
                                        Meta.Builder()
                                        .totalRecords(Long.valueOf(1))
                                        .totalPages(Long.valueOf(1))
                                        .build());
    }

	@Override
    public OfferResponse<?> getByCpf(String cpf, Date date) {

        final String sql = prop.getQueryOffersByCpf();   
        final String dt = Util.formatSimpleDate(date);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", dt);
        params.addValue("cpf", cpf);

        List<Offer> offers = Optional.ofNullable(
                                namedParameterJdbcTemplate.query(sql, params,
                                        (rs, rowNum) ->
                                            new Offer (
                                                        rs.getLong("offerId"),
                                                        rs.getLong("seqNumber"),
                                                        rs.getString("accountName"),
                                                        rs.getString("cpf"), 
                                                        rs.getString("firstName"), 
                                                        rs.getString("lastName"), 
                                                        rs.getDate("txDate"), 
                                                        rs.getBigDecimal("positionPrice")
                                                    )
                                )
                            ).filter(obj -> !obj.isEmpty())
                            .orElseThrow(() -> new NotFoundException(String.format(prop.getNodatafoundDateCpf(), dt, cpf)));

        return new OfferResponse<>(offers, Links.Builder()
                                            .self(prop.getHttpRequest().getRequestURL().toString())
                                            .first("")
                                            .prev("")
                                            .next("")
                                            .last("").build(),
                                            Meta.Builder()
                                            .totalRecords(Long.valueOf(offers.size()))
                                            .build());
    }

    @Override
    public OfferResponse<?> getByDate(Date date, List<String> cpfs, Pagination p) {

        final String sql = String.format(prop.getQueryOffersByDate(), p.getSize() + 1);
        final String dt = Util.formatSimpleDate(date);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", dt);
        params.addValue("cpf", cpfs);
        params.addValue("seqnumber", p.getPage());

        List<Offer> offers = Optional.ofNullable(
                                namedParameterJdbcTemplate.query(sql, params,
                                        (rs, rowNum) ->
                                            new Offer (
                                                        rs.getLong("offerId"),
                                                        rs.getLong("seqNumber"),
                                                        rs.getString("accountName"),
                                                        rs.getString("cpf"), 
                                                        rs.getString("firstName"), 
                                                        rs.getString("lastName"), 
                                                        rs.getDate("txDate"), 
                                                        rs.getBigDecimal("positionPrice")
                                                    )
                                )
                            ).filter(obj -> !obj.isEmpty())
                            .orElseThrow(() -> new NotFoundException(String.format(prop.getNodatafoundDateCpf(), dt, cpfs)));

        if (offers.size() > p.getSize().intValue()){
            offers.remove(offers.get(p.getSize().intValue()));
            p.setPage(offers.get(p.getSize().intValue()-1).getSeqNumber());
        }
        
        return new OfferResponse<>(offers, Links.Builder()
                                            .self(prop.getHttpRequest().getRequestURL().toString())
                                            .first("")
                                            .prev("")
                                            .next(p.getNextPage())
                                            .last("").build(),
                                            Meta.Builder()
                                            .totalRecords(Long.valueOf(offers.size()))
                                            .build());
    }
}