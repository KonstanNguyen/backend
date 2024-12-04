package com.systems.backend.requests;

import lombok.Data;

@Data
public class PaginationRequest {
    private int page = 0;    // Trang, mặc định là 0
    private int size = 10;   // Kích thước trang, mặc định là 10
    private String sortBy = "id"; // Sắp xếp theo trường "id", mặc định

    // Getter và Setter
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
