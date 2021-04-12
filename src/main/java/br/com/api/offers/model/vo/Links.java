package br.com.api.offers.model.vo;

import lombok.NonNull;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = Include.NON_EMPTY)
@Builder(builderMethodName = "Builder")
@Data
public class Links {

    @NonNull private String self;
             private String first;
             private String prev;
             private String next;
             private String last;

}
