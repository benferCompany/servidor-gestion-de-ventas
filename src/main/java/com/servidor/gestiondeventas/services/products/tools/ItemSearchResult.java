package com.servidor.gestiondeventas.services.products.tools;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemSearchResult<T> {
    private final List<T> resultList;
    private final Long totalElements;

    public ItemSearchResult(List<T> resultList, Long totalElements) {
        this.resultList = resultList;
        this.totalElements = totalElements;
    }
}