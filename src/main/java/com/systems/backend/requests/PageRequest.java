package com.systems.backend.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageRequest {
    private int pageNo = 0;
    private int pageSize = 10;
}
