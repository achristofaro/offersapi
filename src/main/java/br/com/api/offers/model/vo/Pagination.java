package br.com.api.offers.model.vo;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
public class Pagination {
    
    @NonNull private Long size;
    @Setter(AccessLevel.NONE) private Long page;
    private String nextPage;


    @Builder(builderMethodName = "Builder")
    public Pagination(Long size) {
        this.size = size;
    }

    public void setPage(Long page) {
        this.page = page;
        this.nextPageBuilder(this.size, this.page);
    }

    private void nextPageBuilder(Long size, Long page){
        Optional.ofNullable(page)
            .ifPresent(obj -> this.nextPage = "&"+ page.toString() + size.toString());
    }
}
