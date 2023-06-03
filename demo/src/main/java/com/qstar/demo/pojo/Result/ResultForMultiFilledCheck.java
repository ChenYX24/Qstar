package com.qstar.demo.pojo.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultForMultiFilledCheck {//用于一份份地查看统计结果
    private ResultForCheck check;
    private List<String[]> datas;
}
