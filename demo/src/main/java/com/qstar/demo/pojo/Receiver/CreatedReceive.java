package com.qstar.demo.pojo.Receiver;

import com.qstar.demo.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CreatedReceive extends FileReceive{//用于创建的接受对象
    private String title;
    private String description;
    private List<Question> list;
    private boolean commit;
    /*private Map<Integer,List<MultipartFile>> files;     //前面的key代表题号，后面的代表文件队列
    private MultipartFile attach;*/
}
