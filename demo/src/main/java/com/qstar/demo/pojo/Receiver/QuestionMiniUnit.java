package com.qstar.demo.pojo.Receiver;

import lombok.Data;

@Data
public class QuestionMiniUnit {
    private String question;        //问题的描述
    private String[] choices;        //问题的选项，为空则表示这为填空
}
