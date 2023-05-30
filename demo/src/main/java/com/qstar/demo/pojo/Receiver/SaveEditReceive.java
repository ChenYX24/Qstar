package com.qstar.demo.pojo.Receiver;

import lombok.Data;

@Data
public class SaveEditReceive {
    private Integer id;
    private String title;
    private String description;
    private QuestionMiniUnit[] questions;  //限制性的应该只允许更改文本类的
}
