package com.zy.zishiying;

public class Car {
    private String carName;
    private Wheel wheel;

    public Car(String carName,Wheel wheel) {
        this.carName = carName;
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carName='" + carName + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
