package com.example.Nnnews.dto.filterdto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageSortFilterDto {
    private PageDto page;
    private SortDto sort;
    private FilterCollectionDto filterCollection;

}
