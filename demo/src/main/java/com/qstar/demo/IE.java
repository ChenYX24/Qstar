package com.qstar.demo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@JsonPropertyOrder({"id","email"})//规定转为json的格式
public class IE {
    int id;
    String email;
}
