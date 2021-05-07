package com.atguigu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SourceTest {
   private static ApplicationContext ioc;
    public static void main(String[] args) {
        // 1、ioc容器的创建
        ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Object person01 = ioc.getBean("person03");
        System.out.println(person01);
    }
}
