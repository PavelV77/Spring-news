package com.example.Nnnews.dto.filterdto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterWithOneValueDto extends BaseFilterDto {
    @JsonAlias("value")
    private String value;
}
