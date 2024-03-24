package com.sky.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderCountDTO {
    private LocalDate createTime;
    private Integer total;
}
