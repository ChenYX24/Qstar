package com.wenjuan.wenjuandemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilledQuestionaireInfo {
    private int id;
    private String creator;
    private boolean committed;
}
