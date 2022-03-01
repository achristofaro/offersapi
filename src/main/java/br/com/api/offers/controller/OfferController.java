package br.com.api.offers.controller;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.offers.model.vo.Pagination;
import br.com.api.offers.service.OfferService;

@CrossOrigin
@RestController
@Validated
@RequestMapping(value = "${api.basepath}")
public class OfferController {
    
    @Autowired
    private OfferService offerService;

     @GetMapping(value = "/offers/qty"
                ,consumes = MediaType.APPLICATION_JSON_VALUE
                ,produces = MediaType.APPLICATION_JSON_VALUE
                ,params = {"txdate"})
    public ResponseEntity<?> getOffersQty(@RequestParam(value = "txdate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date txdate) {
       
        return ResponseEntity.ok().body(offerService.getRecordCount(txdate));
    }

    @GetMapping(value = "/offers"
                ,consumes = MediaType.APPLICATION_JSON_VALUE 
                ,produces = MediaType.APPLICATION_JSON_VALUE
                ,params = {"txdate"})
    public ResponseEntity<?> getOffersByDate(@RequestParam(value = "txdate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                        @RequestParam(value = "page", required = false) Long page,
                                        @RequestParam(value = "page-size", required = false, defaultValue = "200") Long size,  
                                        @RequestHeader(value = "cpf", required = false) List<String> cpf) {

        return ResponseEntity.ok().body(offerService.getByDate(date, cpf, Pagination.Builder().size(size).build()));
    }

    @GetMapping(value = "/offers/{cpf}"
                ,consumes = MediaType.APPLICATION_JSON_VALUE
                ,produces = MediaType.APPLICATION_JSON_VALUE
                ,params="txdate")
    public ResponseEntity<?> getOffersByCpf(@PathVariable(value = "cpf", required = true) @Size(min = 11, max = 11) String cpf,
                                        @RequestParam(value = "txdate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return ResponseEntity.ok().body(offerService.getByCpf(cpf, date));
    }

}