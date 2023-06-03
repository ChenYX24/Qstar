package com.qstar.demo.pojo.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceResult {//储存统计结果
    private int value;
    private String name;

    public ChoiceResult(String name) {
        this.name = name;
    }
}
