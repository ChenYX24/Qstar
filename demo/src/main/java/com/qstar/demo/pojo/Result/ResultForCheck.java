package com.qstar.demo.pojo.Result;

import com.qstar.demo.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultForCheck {//查询时使用
    private String description;
    private List<Question> list;
    private String attach;
}
