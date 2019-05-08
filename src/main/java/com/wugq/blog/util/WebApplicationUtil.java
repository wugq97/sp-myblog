package com.wugq.blog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class WebApplicationUtil implements ApplicationContextAware {

    private  static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        if(applicationContext.containsBean(beanName)){
            return (T) applicationContext.getBean(beanName);
        }else{
            return null;
        }
    }

    public static <T> T getBean(Class tpye) {
        T bean = null;
        try{
            bean = (T) applicationContext.getBean(tpye);
        }catch (Exception e){
            System.out.println(e);
        }
        return bean;
    }
}
