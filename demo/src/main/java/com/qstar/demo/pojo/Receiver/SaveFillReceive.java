package com.qstar.demo.pojo.Receiver;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class SaveFillReceive{
    private Integer filledID;       //填写记录的ID
    private Integer id;
    private String[] data;
    /*private Map<Integer, MultipartFile> files;  //对应于第几题的上传文件
    private MultipartFile attach;       //附件*/
    private boolean commit;
}
