package com.zy.zishiying;

import java.util.Objects;

public class Wheel {
    private int length;
    private int weight;

    public Wheel(int length, int weight) {
        this.length = length;
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "length=" + length +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return length == wheel.length &&
                weight == wheel.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, weight);
    }
}
