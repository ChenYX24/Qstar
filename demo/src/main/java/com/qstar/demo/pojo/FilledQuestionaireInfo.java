package com.qstar.demo.pojo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"id", "title", "committed","creator"})//规定转为json的格式
public class FilledQuestionaireInfo {
    private int id;
    private String creator;
    private boolean committed;
    private String title;
    private int committedCount;
}
