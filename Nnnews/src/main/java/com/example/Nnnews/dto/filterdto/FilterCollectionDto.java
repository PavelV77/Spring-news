package com.example.Nnnews.dto.filterdto;

import com.example.Nnnews.types.filtertypes.CompositeFilterType;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class FilterCollectionDto {
    @JsonAlias("operation")
    private CompositeFilterType operationBetweenPredicate;
    private List<BaseFilterDto> baseFilterDtoList;
}
