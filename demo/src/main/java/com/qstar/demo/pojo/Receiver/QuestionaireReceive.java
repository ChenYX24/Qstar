package com.qstar.demo.pojo.Receiver;

import com.qstar.demo.pojo.Question;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class QuestionaireReceive extends FileReceive{//用于问卷的接受对象
    private Integer id;
    private String title;
    private String description;
    private List<Question> list;
    /*private Map<Integer,List<MultipartFile>> files;
    private MultipartFile attach;*/
    private boolean commit;
}
