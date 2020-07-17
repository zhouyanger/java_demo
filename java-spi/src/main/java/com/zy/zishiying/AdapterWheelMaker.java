package com.zy.zishiying;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

//模拟生成的适应代理类，根据url进行spi调用，所代理的对象是在 makeWheel 方法中通过 SPI 加载得到的。
public class AdapterWheelMaker implements WheelMaker{
    @Override
    public Wheel makeWheel(URL url) {
        if (null == url){
            throw  new RuntimeException("url错误");
        }
        String parameter = url.getParameter("wheel.maker");
        WheelMaker extension = ExtensionLoader.getExtensionLoader(WheelMaker.class).getExtension(parameter);
        return extension.makeWheel(url);
    }
}
