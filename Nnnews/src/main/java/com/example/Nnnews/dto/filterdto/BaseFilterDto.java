package com.example.Nnnews.dto.filterdto;

import com.example.Nnnews.types.filtertypes.SimpleFilterType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilterWithOneValueDto.class, names = {"EQUALS", "LIKE"}),
        @JsonSubTypes.Type(value = FilterWithTwoValueDto.class, name = "BEATWEEN")
})

@Data
@NoArgsConstructor
public abstract class BaseFilterDto {
    private SimpleFilterType type;
    private String field;
    public abstract String getValue();
}
