package com.uic.po;

/**
 * 收费策略类
 * id: 序列号
 * speed:宽带速率
 * price:每个月多少钱
 * flag:flag:判断是否已逻辑删除
 */
public class ChargeStrategy {
    private int id;
    private int speed;
    private double price;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ChargeStrategy{" +
                "id=" + id +
                ", speed=" + speed +
                ", price=" + price +
                ", flag=" + flag +
                '}';
    }
}
