package com.zy.zishiying;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface WheelMaker {
    Wheel makeWheel(URL url);
}
