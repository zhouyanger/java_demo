package com.zy.zishiying;

import com.alibaba.dubbo.common.URL;

public class CarMakerImpl implements CarMaker {
    private WheelMaker wheelMaker;

    public CarMakerImpl(WheelMaker wheelMaker) {
        this.wheelMaker = wheelMaker;
    }

    @Override
    public Car makeCar(URL url) {
        Wheel wheel = wheelMaker.makeWheel(url);
        return new Car("大众",wheel);
    }
}
