package com.qstar.demo;

//import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import com.qstar.demo.pojo.User;
import com.qstar.demo.IE;
// import java.nio.channels.FileChannel;
// import java.nio.channels.FileLock;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Vector;
public class DataIO {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String path = "id.json";
    private String base = "./userinfo";
    DataIO()
    {

    }
    DataIO(String Path)
    {
        this.path = Path;
    }
    //将对象转为json格式的字符串，然后写入文件中
    public void writeObject(Object object,String Path){
        try {
            String result = " ";
            result = objectMapper.writeValueAsString(object) + "\n";
            FileWriter fileWriter = new FileWriter(base + "/" + Path + ".json");
            fileWriter.write(result);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //获取邮箱对应的id
    public int getIDofObject(String email){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                JsonNode nodekey = node.get("email");
                String temp = " ";
                if(nodekey != null){
                    temp = nodekey.asText();
                    if(temp.hashCode() == email.hashCode()){
                        reader.close();
                        return node.get("id").asInt();
                    }
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    //获取id对应的邮箱
    public String getEmailByID(int id){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                JsonNode nodekey = node.get("id");
                int temp = -1;
                if(nodekey != null){
                    temp = nodekey.asInt();
                    if(temp == id){
                        reader.close();
                        return node.get("email").asText();
                    }
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return " ";
    }
    //将Json转换为对象
    public <T> T switchJsonToObject(String json,Class<T> valuetype){
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
            int temp = -1;
            String email = "";
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                JsonNode nodeid = node.get("id");
                if(nodeid !=  null){
                    temp = nodeid.asInt();
                }
                if(temp == id){
                    reader.close();
                    email = node.get("email").asText();
                    break;
                }
                line = reader.readLine();
            }
            File file = new File(base + "/" + email + ".json");
            if(!file.isFile()){
                return "找不到文件";
            }
            BufferedReader rd = new BufferedReader(new FileReader(file));
            String result = rd.readLine();
            rd.close();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return " ";
    }
    //获取最大id
    public int getMaxId(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = " ";
            int maxid = 0;
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                JsonNode nodeid = node.get("id");
                int temp = 0;
                if(nodeid != null){
                    temp = nodeid.asInt();
                }
                if(temp > maxid){
                    maxid = temp;
                }
                line = reader.readLine();
            }
            reader.close();
            return maxid;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("error");
        return -1;
    }
    //更改密码
    public void changePasswdById(int id,String newPasswd){
       try {
        String email = this.getEmailByID(id);
        if(email != " "){
            File file = new File(base + "/" + email + ".json");
            Scanner sc = new Scanner(file);
            String userinfo = sc.nextLine();
            User user = new User(id,getKeyValueofJson(userinfo,"_name"),
                            email,
                            newPasswd,
                            getKeyValueofJson(userinfo,"_phonenumber"),
                            getKeyValueofJson(userinfo,"_location"));
            writeObject(user, email);
            sc.close();
        }
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
    }
    //更改电话号
    public void changePhoneById(int id,String PhoneNumber){
        try {
         String email = this.getEmailByID(id);
         if(email != " "){
             File file = new File(base + "/" + email + ".json");
             Scanner sc = new Scanner(file);
             String userinfo = sc.nextLine();
             User user = new User(id,getKeyValueofJson(userinfo,"_name"),
                             email,
                             getKeyValueofJson(userinfo,"_passwd"),
                             PhoneNumber,
                             getKeyValueofJson(userinfo,"_location"));
             writeObject(user, email);
             sc.close();
         }
        } catch (Exception e) {
         System.out.println(e.getMessage());
        }
    }
    //更改位置
    public void changeLocationById(int id,String Location){
        try {
         String email = this.getEmailByID(id);
         if(email != " "){
             File file = new File(base + "/" + email + ".json");
             Scanner sc = new Scanner(file);
             String userinfo = sc.nextLine();
             User user = new User(id,getKeyValueofJson(userinfo,"_name"),
                             email,
                             getKeyValueofJson(userinfo,"_passwd"),
                             getKeyValueofJson(userinfo,"_phonenumber"),
                             Location);
             writeObject(user, email);
             sc.close();
         }
        } catch (Exception e) {
         System.out.println(e.getMessage());
        }
    }
    //获取json中某个key的值
    public String getKeyValueofJson(String json,String key){
        String result = "";
        try {
            JsonNode node = objectMapper.readTree(json);
            JsonNode nodekey = node.get(key);
            if(nodekey != null){
                result = nodekey.asText();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public void writeID_Email(int id,String email){
        try {
            FileWriter fileWriter = new FileWriter("id.json",true);
            IE ie = new IE(id,email);
            String result = " ";
            result = objectMapper.writeValueAsString(ie) + "\n";
            fileWriter.write(result);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //判断是否存在文件
    public boolean hasFile(String path){
        File file = new File(path);
        if(file.isFile()){
            return true;
        }
        return false;
    }
    //将IE对象转为Json字符串
    public String getIEByEmail(String Email){
        String result = " ";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = "";
            line = reader.readLine();
            while(line != null){
                JsonNode node = objectMapper.readTree(line);
                JsonNode nodeitem = node.get("email");
                String temp = "";
                if(nodeitem != null){
                    temp = nodeitem.asText();
                    if(temp.hashCode() == Email.hashCode()){
                        return line;
                    }
                }
                
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    //获取文件中所有json信息，返回一个String数组
    public Vector<String> getUsers(){
        Vector<String> users = new Vector<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))){
            String line = "";
            line = reader.readLine();
            while(line != null){
                users.addElement(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    //根据id获取已创建问卷的信息
    //写一个token是否有效的函数，boolean checkToken(String token)
}
