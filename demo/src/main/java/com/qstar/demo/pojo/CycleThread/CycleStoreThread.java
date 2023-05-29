package com.qstar.demo.pojo.CycleThread;

import com.qstar.demo.Dao.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("thread")
public class CycleStoreThread extends Thread{//放在监听器的初始方法会好点
    @Autowired
    private Link link;
    @Value("${thread.cycle}")
    private int cycle;      //计时，从配置文件中读取
    private boolean start;
    public CycleStoreThread(){
        start=true;
    }
    @Override
    public void run(){
        while(start) {   //周期性地检测写回
            try {
                Thread.sleep(cycle);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                link.storeMap();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void stopThread(){
        start=false;
    }
}
