package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 传输的分页数据
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;
}
