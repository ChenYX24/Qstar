package com.qstar.demo.pojo.Result;

import com.qstar.demo.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StatisticsResult {//用于传输统计结果的类,放在result的data项内
    private String title;
    private String question;
    private Type type;
    private List choices;//储存数字或者字符串的数据
}
