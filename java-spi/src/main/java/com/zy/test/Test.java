package com.zy.test;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.zy.dubbospi.Robot;

public class Test {
    public static void main(String[] args) {
        //测试javaspi
//        ServiceLoader<UploadCDN> load = ServiceLoader.load(UploadCDN.class);
//        for (UploadCDN uploadCDN:load){
//            uploadCDN.upload("filepath");
//        }
        
        //测试dubbospi
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot first = extensionLoader.getAdaptiveExtension();
        first.sayHello();
        Robot second = extensionLoader.getExtension("second");
        second.sayHello();
        
        //测试dubbo自适应
    }
}
