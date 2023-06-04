package com.qstar.demo.pojo.Result;

import com.qstar.demo.pojo.Question;
import lombok.Data;

import java.util.List;
@Data
public class ResultForCheckWithID {
    private String title;
    private String description;
    private List<Question> content;
    private String attach;
    private int ID;
    public ResultForCheckWithID(ResultForCheck check,int ID){
        this.title=check.getTitle();
        this.description=check.getDescription();
        this.content=check.getContent();
        //this.attach=check.getAttach();
        this.ID=ID;
    }
}
