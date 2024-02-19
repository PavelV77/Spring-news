package com.example.Nnnews.dto.filterdto;

public class SortDto {
    private String sortField;
    private String sortDirection = "ASC";//ASC, DESC

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
