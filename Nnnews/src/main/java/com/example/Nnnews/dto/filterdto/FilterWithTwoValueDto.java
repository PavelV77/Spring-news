package com.example.Nnnews.dto.filterdto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("FILTERWITHTWOVALUE")
public class FilterWithTwoValueDto extends BaseFilterDto{
    @JsonAlias("value")
    private String value;
    private String value2;
}
