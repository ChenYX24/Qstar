package com.qstar.demo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import com.fasterxml.jackson.databind.JsonNode;
// import java.nio.channels.FileChannel;
// import java.nio.channels.FileLock;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
import java.io.RandomAccessFile;
import java.util.Vector;
public class DataIO {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String path = "user.json";
    DataIO()
    {

    }
    DataIO(String Path)
    {
        this.path = Path;
    }
    //将对象转为json格式的字符串，然后写入文件中
    public void writeObject(Object object){
        // try (FileChannel channel = FileChannel.open(Paths.get("user.json"), StandardOpenOption.READ, StandardOpenOption.WRITE)) {
        //     FileLock lock = channel.lock();//获取文件锁定
        //     // 在锁定期间进行文件读写操作
            try {
                String result = " ";
                result = objectMapper.writeValueAsString(object) + "\n";
                System.out.println(result);
                FileWriter fileWriter = new FileWriter(this.path,true);
                fileWriter.write(result);
                fileWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        //     lock.release(); // 释放文件锁定
        // } catch (Exception e) {
        //     // 处理异常
        //     System.out.println(e.getMessage());
        // }
    }
    //获取某个key的值为value的对象的id
    public int getIDofObject(String key,String value){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                String temp = node.get(key).asText();
                if(temp == value){
                    reader.close();
                    return node.get("_id").asInt();
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    //将Json转换为对象
    public Object switchJsonToObject(String json,JavaType valuetype){
        try {
            return objectMapper.readValue(json,valuetype);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    //获取特定id的json
    public String getJsonById(int id){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                int temp = node.get("_id").asInt();
                if(temp == id){
                    reader.close();
                    return line;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "null";
    }
    //获取文件中的最大id
    public int getMaxId(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            int maxid = 0;
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                int temp = node.get("_id").asInt();
                if(temp > maxid){
                    maxid = temp;
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println("maxid= " + maxid);
            return maxid;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    //更改密码
    public void changePasswdById(int id,String newPasswd){
        try (RandomAccessFile file = new RandomAccessFile("user.json", "rw")){
            String line = file.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                int temp = node.get("_id").asInt();
                Vector<String> buffer = new Vector<String>();
                System.out.println(line);
                if(temp == id){
                    System.out.println(id);
                    long pos = file.getFilePointer();
                    int length = 0;
                    while(line != null){
                        line = file.readLine();
                        buffer.addElement(line);
                    }
                    
                    if(pos > 0){
                        pos--;
                    }
                    while (pos >= 0) {
                        file.seek(pos); // 移动文件指针到上一个字符位置
                        char ch = (char) file.readByte(); // 读取上一个字符
                        if (ch == ':') {
                            break; // 如果找到":"字符，跳出循环
                        }
                        pos--; // 如果不是":"字符，将指针向前移动
                        length++;
                    }
                    pos++;
                    length--;
                    file.seek(pos);
                    for(int i = 0;i < length;i++){
                        file.writeByte(' ');
                    }
                    file.seek(pos);
                    char chars[] = newPasswd.toCharArray();
                    file.writeByte('"');
                    for(int i = 0;i < chars.length;i++)
                    {
                        file.writeByte(chars[i]);
                    }
                    file.writeByte('"'); file.writeByte('}'); file.writeByte('\n');
                    System.out.println(buffer.size());
                    for(int i = 0;i < buffer.size() - 1;i++){
                        String data = buffer.elementAt(i);
                        char Chars[] = data.toCharArray();
                        for(int j = 0;j < Chars.length;j++)
                        {
                            file.writeByte(Chars[j]);
                        }
                        file.writeByte('\n');
                    }
                    file.setLength(file.getFilePointer());
                    break;
                }
                line = file.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
