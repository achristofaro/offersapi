package br.com.api.offers.model.vo;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@JsonRootName(value = "data")
@Data
public class OfferResponse<T> {

    @JsonProperty(value = "offers")
    private List<T> offers;

    @JsonProperty(value = "links")
    private Links link;

    private T offerQty;

    public OfferResponse (List<T> list, Links link)
    {
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.offers = list;
        this.link = link;
    }

    public OfferResponse (T t, Links link)
    {
        if(t == null){
            throw new IllegalArgumentException();
        }
        this.offerQty = t;
        this.link = link;
    }

}
