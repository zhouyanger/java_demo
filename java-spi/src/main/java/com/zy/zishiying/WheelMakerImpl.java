package com.zy.zishiying;

import com.alibaba.dubbo.common.URL;

public class WheelMakerImpl implements WheelMaker{
    @Override
    public Wheel makeWheel(URL url) {
        return new Wheel(20,30);
    }
}
