package br.com.api.offers.model.vo;


import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_EMPTY)
@Data
public class OfferResponse<T> {

    @JsonProperty(value = "data")
    private Object data;

    @JsonProperty(value = "links")
    private Links link;

    @JsonProperty(value = "meta")
    private Meta meta;

    public OfferResponse (List<T> list, Links link, Meta meta)
    {

        this.data = Optional.ofNullable(list)
                            .filter(obj -> !obj.isEmpty())
                            .orElseThrow(IllegalStateException::new);
        this.link = link;
        this.meta = meta;
    }

    public OfferResponse (T t, Links link, Meta meta)
    {
        this.data = Optional.ofNullable(t)
                        .orElseThrow(IllegalStateException::new);
        this.link = link;
        this.meta = meta;
    }

}
