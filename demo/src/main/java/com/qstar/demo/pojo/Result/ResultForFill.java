package com.qstar.demo.pojo.Result;

import com.qstar.demo.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultForFill {
    private ResultForCheck check;
    private String[] filled;
    private int id;
}
