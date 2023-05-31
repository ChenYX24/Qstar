package com.qstar.demo.pojo.CycleThread;

import com.qstar.demo.Dao.Link;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@WebListener
public class InitializingListener implements ServletContextListener, ApplicationContextAware {//监听器，用于初始化时调用线程的循环
    private ApplicationContext context;
    private CycleStoreThread cycle;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //this.context=applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {//在context初始化时调用
        cycle=(CycleStoreThread)context.getBean("thread");
        cycle.run();
        Link link=(Link)context.getBean("link");
        sce.getServletContext().setAttribute("tokens",link.getMap());   //不清楚能不能获取啊，测试时看看吧
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       cycle.stopThread();
    }
}
