package com.qstar.demo.pojo.Receiver;

import com.qstar.demo.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"title","description","content","commit"})
public class CreatedReceive extends FileReceive{//用于创建的接受对象
    @JsonPropertyOrder("title")
    private String title;
    @JsonPropertyOrder("description")
    private String description;
    @JsonPropertyOrder("content")
    private List<Question> content;
    @JsonPropertyOrder("commit")
    private boolean commit;
    /*private Map<Integer,List<MultipartFile>> files;     //前面的key代表题号，后面的代表文件队列
    private MultipartFile attach;*/
}
