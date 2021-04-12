package br.com.api.offers.model.vo;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
public class OfferResponse<T> {

    private Object data;

    @JsonProperty(value = "links")
    private Links link;

    public OfferResponse (List<T> list, Links link)
    {
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        
        this.data = list;
        this.link = link;
    }

    public OfferResponse (T t, Links link)
    {
        if(t == null){
            throw new IllegalArgumentException();
        }
        
        this.data = t;
        this.link = link;
    }

}
