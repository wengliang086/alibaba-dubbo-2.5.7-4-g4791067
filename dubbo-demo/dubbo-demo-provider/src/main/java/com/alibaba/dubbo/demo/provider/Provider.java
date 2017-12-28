package com.alibaba.dubbo.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ken.lj on 2017/7/31.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
    	/**
    	 * DubboNamespaceHandler 加载 ServiceBean（实现了 ApplicationListener 接口）
    	 * Spring 提供的 ApplicationListener 会在容器初始化完成之后调用 onApplicationEvent 方法
    	 * onApplicationEvent 方法中调用 ServiceConfig 类中的 export() 方法启动开始
    	 */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }

}
