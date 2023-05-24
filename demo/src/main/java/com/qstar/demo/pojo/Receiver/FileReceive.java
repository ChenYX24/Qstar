package com.qstar.demo.pojo.Receiver;

import com.qstar.demo.pojo.Question;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@Data
public class FileReceive {
    private List<Question> list;
    private Map<Integer, List<MultipartFile>> files;     //前面的key代表题号，后面的代表文件队列
    private MultipartFile attach;
}
